package tech.zhaixinyu.starter.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.security
 * ClassName: CustomTokenFilter
 * Description: Description
 * Created by @author Xinyu on 1/18/20 21:32
 */
@Component
public class CustomTokenFilter extends OncePerRequestFilter {

    @Resource
    private CustomTokenProvider customTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = customTokenProvider.resolveToken(request);
        if (!StringUtils.hasText(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Token");
            // return代表提交后不再执行后续代码
            // After using this method, the response should be considered to be committed and should not be written to.
            return;
        }
        try {
            customTokenProvider.validateToken(token);
            Authentication authentication = customTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Error");
            //this.authenticationEntryPoint.commence(request, response, e);
        }
    }
}
