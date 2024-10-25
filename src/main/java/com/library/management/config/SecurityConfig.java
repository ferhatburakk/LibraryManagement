package com.library.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(a -> a
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }
}











//    private final AppAuthenticationProvider appAuthenticationProvider;
//    public SecurityConfig(AppAuthenticationProvider appAuthenticationProvider) {
//        this.appAuthenticationProvider = appAuthenticationProvider;
//    }
//    @Bean
//    SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http.httpBasic(Customizer.withDefaults());
//        http.authorizeHttpRequests(request -> request.requestMatchers(toH2Console()).permitAll());
//        return http.build();
//        http.httpBasic(Customizer.withDefaults());
//        http.authenticationProvider(appAuthenticationProvider);
//        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());
//        http.authorizeHttpRequests(request -> request.requestMatchers(request1 -> request1.))
//        return http.build();
//        http.csrf(csrf -> csrf.
//                ignoringRequestMatchers(toH2Console()).disable())
//                .authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .authenticationProvider(appAuthenticationProvider);
//        return http.build();
//    }


//    @Bean
//    UserDetailsService userDetailsService(){
//        UserDetails user = User.withUsername("ferhat").password("123").authorities("read").build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

