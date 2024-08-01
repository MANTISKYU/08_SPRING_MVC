package com.ohgiraffers.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/image/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((registry) ->{
           registry.requestMatchers("/", "/index.html").permitAll() // 누구나 허용
                   .requestMatchers("/board/**").authenticated() // 인증된 사용자만 허용
                   .requestMatchers("/admin/**").hasRole("ADMIN")
                   .anyRequest().authenticated();
        });

        http.formLogin(configurer -> {configurer.loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
        });

        http.logout(configurer -> {
            configurer.logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/auth/login");
        });


        return http.build();


        /*
         * - permitAll() : 모두허용
         * - authenticated() : 인증된 사용자만 허용
         * - anonymous() : 인증하지 않은 사용자만 허용
         * - hasRole(), hasAnyRole() : 특정 권한이 있는 사용자만 허용
         * */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


    @Bean
    public UserDetailsService userDetailsService() {

    UserDetails user = User.builder()
            .username("user01")
            .password(passwordEncoder().encode("1234")) // 1234를 암호화해서 저장
            .roles("USER")
            .build();

    UserDetails admin = User.builder()
            .username("hihi")
            .password(passwordEncoder().encode("1234"))
            .roles("USER", "ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user, admin);

    }

}
