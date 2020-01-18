package tech.zhaixinyu.starter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.annotation.Resource;

import static tech.zhaixinyu.starter.utils.Constants.ERROR_ENDPOINT;
import static tech.zhaixinyu.starter.utils.Constants.LOGIN_ENDPOINT;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.security
 * ClassName: WebSecurityConfig
 * Description: Description
 * Created by @author Xinyu on 1/18/20 20:09
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Resource
    private CustomTokenFilter customTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new CustomLoginFilter(LOGIN_ENDPOINT, this.authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(customTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 异常处理映射为“/error”
        http.authorizeRequests().antMatchers(HttpMethod.POST, LOGIN_ENDPOINT).permitAll().antMatchers(ERROR_ENDPOINT).permitAll().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(new BasicAuthenticationEntryPoint());

    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
