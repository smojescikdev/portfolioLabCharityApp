package pl.coderslab.charity.config;

//konfiguruje zabezpieczenia aplikacji Spring, określając zasady autoryzacji, logowania i wylogowywania
// użytkowników oraz definiując dostawcę uwierzytelniania opartego na niestandardowym serwisie
// CustomUserDetailsService i enkoderze haseł BCryptPasswordEncoder


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.coderslab.charity.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    private final String[] publicUrl = {"/",
            "/global-search/**",
            "/register",
            "/login",
            "/contact",
            "/about",
            "/template/",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/resources/static/assets/**",
            "/fonts**", "/favicon.ico", "/resources/**", "/error",
    };


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers("/admin/**").hasAuthority("Admin") // hasAuthority zamiast hasRole
                    .requestMatchers(publicUrl).permitAll()
                    .anyRequest().authenticated();
        });

        // Konfiguracja strony logowania
        http.formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler(customAuthenticationSuccessHandler)) // Użyj CustomAuthenticationSuccessHandler


                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
                }).cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return authenticationProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
