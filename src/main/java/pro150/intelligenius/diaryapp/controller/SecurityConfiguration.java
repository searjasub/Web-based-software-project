package pro150.intelligenius.diaryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userJpaRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http)  {
        try {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/profiles/").permitAll()
                    .antMatchers(HttpMethod.POST, "/profiles/create-profile").permitAll()
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
