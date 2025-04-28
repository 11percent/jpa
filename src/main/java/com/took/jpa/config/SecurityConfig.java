package com.took.jpa.config;

import com.took.jpa.service.Oauth2DetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //이러면 bean으로 등록되고 container에 등록된다. DI할 수 있다.

    private final Oauth2DetailsService oauth2DetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                        (auth) ->
                                auth.requestMatchers("/", "/member/signup", "/member/login", "/member/find-password", "/index/index", "/mail/email-confirm", "mail/find-password", "/css/**", "/images/**")
                                        .permitAll()
                                        .requestMatchers("/admin/**").hasRole("ADMIN")
                                        .anyRequest()
                                        .authenticated()
                )
                .csrf((auth) -> auth.disable())
                .formLogin((auth) ->
                        auth.loginPage("/member/login")
                                .usernameParameter("userID") //default username login.html에 있는 input name="userID"
                                .passwordParameter("userPW") //default password
                                .loginProcessingUrl("/member/login")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/member/login-fail")
                                .permitAll()

                )
                .logout(auth ->
                        auth.logoutUrl("/member/logout")
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                );

                httpSecurity.oauth2Login((auth) ->
                        auth.loginPage("/member/login")
                                .defaultSuccessUrl("/")
                                .userInfoEndpoint(userInfo -> userInfo.userService(oauth2DetailsService))
                 );

        return httpSecurity.build();
    }
}
