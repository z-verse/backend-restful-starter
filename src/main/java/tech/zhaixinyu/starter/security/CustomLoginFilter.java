package tech.zhaixinyu.starter.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tech.zhaixinyu.starter.dto.LoginDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static tech.zhaixinyu.starter.utils.Constants.TOKEN_HEADER;
import static tech.zhaixinyu.starter.utils.Constants.TOKEN_PREFIX;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.security
 * ClassName: CustomLoginFilter
 * Description: Description
 * Created by @author Xinyu on 1/18/20 20:18
 */
public class CustomLoginFilter extends AbstractAuthenticationProcessingFilter {

    public CustomLoginFilter(String url, AuthenticationManager manager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(manager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("===attempt");
        LoginDto loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword(),
                        Collections.emptyList())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("===successful");
        CustomTokenProvider customTokenProvider = new CustomTokenProvider();
        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + customTokenProvider.createAccessToken(authResult));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("===unsuccessful");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "login failed");
    }
}
