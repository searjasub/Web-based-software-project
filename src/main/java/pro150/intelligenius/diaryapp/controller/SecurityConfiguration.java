package pro150.intelligenius.diaryapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Map<String, UserDetails> users = new HashMap<>();

    @PostConstruct
    private void initUsers(){
        users.put("sear", new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "USER";
                    }
                });
            }

            @Override
            public String getPassword() {
                return "{noop}test";
            }

            @Override
            public String getUsername() {
                return "sear";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
}

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> users.get(username);
    }

    @Override
    protected void configure(HttpSecurity http)  {
        try {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/users/**").permitAll()
//                    .antMatchers(HttpMethod.POST, "/courses").hasAnyAuthority("Faculty")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic()
                    .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
