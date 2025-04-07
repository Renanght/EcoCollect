package fr.rgohaut.ecocollect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                       // .requestMatchers("/swagger-ui/**").permitAll()
                       // .requestMatchers("/api/public/**").permitAll()
                        //.requestMatchers("/admin/**").hasRole("admin")
                      //  .requestMatchers("/user/**").hasRole("user")
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri("http://localhost:8082/realms/EcoCollect/protocol/openid-connect/certs")
                        )
                );
        return http.build();
    }
}