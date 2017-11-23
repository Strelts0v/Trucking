package com.itechart.trucking.config;

import com.itechart.trucking.auth.encoder.SHA256PasswordEncoder;
import com.itechart.trucking.filter.StatelessAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTHORIZED_ROLE_SYSADMIN = "SYSADMIN";
    private static final String AUTHORIZED_ROLE_ADMIN = "ADMIN";
    private static final String AUTHORIZED_ROLE_MANAGER = "MANAGER";
    private static final String AUTHORIZED_ROLE_DISPATCHER = "DISPATCHER";
    private static final String AUTHORIZED_ROLE_DRIVER = "DRIVER";
    private static final String AUTHORIZED_ROLE_OWNER = "OWNER";

    private final UserDetailsService userService;
    private final StatelessAuthenticationFilter statelessAuthenticationFilter;

    private SHA256PasswordEncoder sha256PasswordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userService,
                          StatelessAuthenticationFilter statelessAuthenticationFilter,
                          SHA256PasswordEncoder sha256PasswordEncoder) {
        super(true);
        this.userService = userService;
        this.statelessAuthenticationFilter = statelessAuthenticationFilter;
        this.sha256PasswordEncoder = sha256PasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we use jwt so that we can disable csrf protection
        http.csrf().disable().cors().disable();

        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl()
        ;

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/*").hasAnyRole(
                    AUTHORIZED_ROLE_SYSADMIN,
                    AUTHORIZED_ROLE_ADMIN,
                    AUTHORIZED_ROLE_MANAGER,
                    AUTHORIZED_ROLE_DISPATCHER,
                    AUTHORIZED_ROLE_DRIVER,
                    AUTHORIZED_ROLE_OWNER
                )
                // TODO test and enable .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("'Bearer token_type=\"JWT\"'"));

        http.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Prevent StatelessAuthenticationFilter will be added to Spring Boot filter chain.
     * Only Spring Security must use it.
     */
    @Bean
    public FilterRegistrationBean registration(StatelessAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(sha256PasswordEncoder);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

}
