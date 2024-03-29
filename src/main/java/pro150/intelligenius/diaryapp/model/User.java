package pro150.intelligenius.diaryapp.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    @Id
    private String username;

    @Column(nullable = false)
    @Size(min = 4)
    private String password;

    @Column(nullable = false)
    private boolean active = true;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> rawAuthorities = new ArrayList<>();

    public User() {
        rawAuthorities.add("User");
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getRawAuthorities() {
        return rawAuthorities;
    }

    public void setRawAuthorities(List<String> rawAuthorities) {
        this.rawAuthorities = rawAuthorities;
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
        return this.active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRawAuthorities().stream()
                .map(raw -> (GrantedAuthority) () -> raw)
                .collect(Collectors.toList());
    }


}
