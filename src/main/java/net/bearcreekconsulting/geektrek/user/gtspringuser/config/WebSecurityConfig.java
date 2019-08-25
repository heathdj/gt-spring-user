package net.bearcreekconsulting.geektrek.user.gtspringuser.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by David on 8/25/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses should not invoke this method by
     * calling super as it may override their configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cross origin resource sharing
        http.cors().and()
                // start by authorizing configurations
                .authorizeRequests()
                .antMatchers("/resources/**",
                        "/error",
                        "service/**").permitAll()
                // Authenticate all remaining urls
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/service/logout", "POST"))
                .and()
                .formLogin()
                .loginPage("/service/user").and()
                // Enable basic http header authentication
                .httpBasic().and()
                // Cross site request forgery
                .csrf().disable();
    }

    /**
     * Used by the default implementation of {@link #authenticationManager()} to attempt to obtain an {@link
     * AuthenticationManager}. If overridden, the {@link AuthenticationManagerBuilder} should be used to specify the
     * {@link AuthenticationManager}.
     *
     * <p>
     * The {@link #authenticationManagerBean()} method can be used to expose the resulting {@link AuthenticationManager}
     * as a Bean. The {@link #userDetailsServiceBean()} can be used to expose the last populated {@link
     * UserDetailsService} that is created with the {@link AuthenticationManagerBuilder} as a Bean. The {@link
     * UserDetailsService} will also automatically be populated on {@link HttpSecurity#getSharedObject(Class)} for use
     * with other {@link SecurityContextConfigurer} (i.e. RememberMeConfigurer )
     * </p>
     *
     * <p>
     * For example, the following configuration could be used to register in memory authentication that exposes an in
     * memory {@link UserDetailsService}:
     * </p>
     *
     * <pre>
     * &#064;Override
     * protected void configure(AuthenticationManagerBuilder auth) {
     * 	auth
     * 	// enable in memory based authentication with a user named
     * 	// &quot;user&quot; and &quot;admin&quot;
     * 	.inMemoryAuthentication().withUser(&quot;user&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;).and()
     * 			.withUser(&quot;admin&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;, &quot;ADMIN&quot;);
     * }
     *
     * // Expose the UserDetailsService as a Bean
     * &#064;Bean
     * &#064;Override
     * public UserDetailsService userDetailsServiceBean() throws Exception {
     * 	return super.userDetailsServiceBean();
     * }
     *
     * </pre>
     *
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Configure cross origin requests processing.
             *
             * @param registry
             * @since 4.2
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
