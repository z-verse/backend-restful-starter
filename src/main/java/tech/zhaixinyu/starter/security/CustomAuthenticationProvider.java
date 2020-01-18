package tech.zhaixinyu.starter.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.zhaixinyu.starter.entity.User;
import tech.zhaixinyu.starter.service.UserService;

import javax.annotation.Resource;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.security
 * ClassName: CustomAuthenticationProvider
 * Description: Description
 * Created by @author Xinyu on 1/18/20 22:14
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private PasswordEncoder encoder;
    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.findByUsername(username).orElseThrow(() -> new BadCredentialsException("Bad Credentials-Username"));

        if (!encoder.matches(password, user.getPassword())) {
            SecurityContextHolder.clearContext();
            throw new BadCredentialsException("Bad Credentials-Password");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
