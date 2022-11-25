package dio.projeto.polesca.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] SWAGGER_WHITLIST = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/api-docs"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Create two users in memory
        auth.inMemoryAuthentication()
                .withUser("tester")
                .password(passwordEncoder().encode("tester123"))
                .roles("TESTER")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USERS")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .and()
                .passwordEncoder(passwordEncoder());
            }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(SWAGGER_WHITLIST).permitAll()
                .antMatchers(HttpMethod.POST, "/parking/**").hasAnyRole("ADMIN", "USERS", "TESTER")
                .antMatchers(HttpMethod.PUT, "/parking/**").hasAnyRole("ADMIN", "USERS", "TESTER")
                .antMatchers(HttpMethod.GET, "/parking/**").hasAnyRole("ADMIN", "USERS", "TESTER")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
