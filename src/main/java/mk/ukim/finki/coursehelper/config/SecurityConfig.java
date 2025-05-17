package mk.ukim.finki.coursehelper.config;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;


    @Configuration
    @EnableWebSecurity
    @AllArgsConstructor
    public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

//        @Bean
//        public DefaultSecurityFilterChain securityFilterChain(
//                HttpSecurity httpSecurity) throws Exception {
//            return httpSecurity.csrf(csrf -> csrf.disable())
//                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                    .csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests(it -> it.requestMatchers("/rest/**", "/rest/**").permitAll()
//                            .anyRequest().authenticated())
//                    .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .build();
//        }
//
//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//            return configuration.getAuthenticationManager();
//        }
//
//        @Bean
//        public CorsFilter corsFilter() {
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowCredentials(true);
//            config.setAllowedOrigins(List.of("http://localhost:5173")); // Allow frontend
//            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//            config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//            source.registerCorsConfiguration("/**", config);
//            return new CorsFilter(source);
//        }
//
//        private UrlBasedCorsConfigurationSource corsConfigurationSource() {
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowCredentials(true);
//            config.setAllowedOrigins(List.of("http://localhost:5173")); // Frontend URL
//            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//            config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//            source.registerCorsConfiguration("/**", config);
//            return source;
//        }

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers ->
                        // keep H2 console working if you like
                        headers.frameOptions(frameOptions -> frameOptions.sameOrigin())
                )
                .build();
    }
    }
