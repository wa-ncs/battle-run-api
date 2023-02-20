package com.wancs.battle_run.global.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class DefaultSecurityConfig {

    // @formatter:off
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/api/**").hasAuthority("SCOPE_message.read")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
        http.csrf().disable();
        return http.build();
    }
    // @formatter:on

//    // @formatter:off
//    @Bean
//    UserDetailsService users() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user1")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//    // @formatter:on
//
//    @Bean
//    SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//
//    @Bean
//    HttpSessionEventPublisher httpSessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }

}
