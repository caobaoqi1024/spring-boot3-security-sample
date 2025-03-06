package dev.mcdd.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.*;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RequestMatcher publicEndPointMatcher() {
        return new OrRequestMatcher(
                PathRequest.toH2Console(),
                PathRequest.toStaticResources().atCommonLocations(),
                new AntPathRequestMatcher("/users/**"),
                new AntPathRequestMatcher("/auth/sign-in", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/auth/sign-up", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/v3/api-docs/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/swagger-ui.html", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/error"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RestfulAuthenticationEntryPointHandler restfulAuthenticationEntryPointHandler = new RestfulAuthenticationEntryPointHandler();
        /*
        <Stateless API CSRF protection>
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        */
        http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource));
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(publicEndPointMatcher())
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .accessDeniedHandler(restfulAuthenticationEntryPointHandler)
                        .authenticationEntryPoint(restfulAuthenticationEntryPointHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // https://docs.spring.io/spring-boot/reference/data/sql.html#data.sql.h2-web-console.spring-security
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }
}
