package ma.enset.springmvcspringdatajpathymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//
@Configuration @EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("pass1")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("pass2")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("pass3")).roles("USER","ADMIN").build()
                );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        https.authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
             .authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**").permitAll())
             .authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
             .authorizeHttpRequests(ar->ar.anyRequest().authenticated());
        return https.build();
    }
}
