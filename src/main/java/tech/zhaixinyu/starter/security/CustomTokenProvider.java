package tech.zhaixinyu.starter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tech.zhaixinyu.starter.entity.User;
import tech.zhaixinyu.starter.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static tech.zhaixinyu.starter.utils.Constants.*;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.security
 * ClassName: CustomTokenProvider
 * Description: Description
 * Created by @author Xinyu on 1/18/20 21:33
 */
@Component
public class CustomTokenProvider {

    @Resource
    private UserService userService;

    public String createAccessToken(Authentication authentication) {
        return generateToken(authentication, EXPIRED_ACCESS);
    }

    public String createRefreshToken(Authentication authentication) {
        return generateToken(authentication, EXPIRED_REFRESH);
    }

    private String generateToken(Authentication authentication, Long expired) {
        String username = authentication.getPrincipal().toString();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = LocalDateTime.ofInstant(Instant.ofEpochMilli(Timestamp.valueOf(now).getTime()
                + expired), ZoneId.systemDefault());
        return JWT.create()
                .withIssuer(TOKEN_ISSUER)
                .withSubject(username)
                .withIssuedAt(Timestamp.valueOf(now))
                .withExpiresAt(Timestamp.valueOf(validity))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void validateToken(String token) throws JWTVerificationException {
        JWT.require(Algorithm.HMAC256(SECRET_KEY)).withIssuer(TOKEN_ISSUER).build().verify(token);
    }

    public String resolveUsernameFromToken(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return decoded.getSubject();
    }

    public Authentication getAuthentication(String token) throws UsernameNotFoundException {
        String username = this.resolveUsernameFromToken(token);
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad Token-Username"));
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
    }
}
