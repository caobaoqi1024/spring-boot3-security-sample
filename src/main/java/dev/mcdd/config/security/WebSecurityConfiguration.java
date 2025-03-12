package dev.mcdd.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final CorsConfigurationSource corsConfigurationSource;
    private final FormLoginHandler formLoginHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RequestMatcher publicEndPointMatcher() {
        return new OrRequestMatcher(
                PathRequest.toH2Console(),
                PathRequest.toStaticResources().atCommonLocations(),
                new AntPathRequestMatcher("/error"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource));
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.sessionManagement(Customizer.withDefaults());
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(publicEndPointMatcher()).permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(login -> login
                .loginProcessingUrl("/auth/sign-in")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(formLoginHandler)
                .failureHandler(formLoginHandler)
        );

        return http.build();
    }
}