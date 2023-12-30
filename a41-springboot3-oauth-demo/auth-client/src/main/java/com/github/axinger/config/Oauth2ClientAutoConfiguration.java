package com.github.axinger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// 使用@EnableWebSecurity注解开启Spring Security功能
@EnableWebSecurity
public class Oauth2ClientAutoConfiguration {

    @Bean
    public SecurityFilterChain authorizationClientSecurityFilterChain(HttpSecurity http) throws Exception {

//
//            http
//                    .authorizeHttpRequests((authorizeHttpRequests) ->
//                            authorizeHttpRequests
//                                    .requestMatchers("/**").hasRole("USER")
//                                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    )
//                    .oauth2Client()
//
//        ;
//            return http.build();
//        }


        http
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and().logout()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().oauth2Client()
                .and().oauth2Login();

        return http.build();
    }
}
