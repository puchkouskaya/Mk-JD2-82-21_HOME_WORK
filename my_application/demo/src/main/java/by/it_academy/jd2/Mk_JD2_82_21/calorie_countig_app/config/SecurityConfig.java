package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.config;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.filter.JWTAuthorizationFilter;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter ;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/product/**","/api/recipe/**","/api/profile/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/api/product/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/product/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/product/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/recipe/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.PUT,"/api/recipe/**","/api/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.DELETE,"/api/recipe/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/register", "/auth","/activate/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
