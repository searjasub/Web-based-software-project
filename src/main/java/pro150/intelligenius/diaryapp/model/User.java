package pro150.intelligenius.diaryapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Entry> entries = new ArrayList<>();

    @ElementCollection
    private List<String> rawAuthorities = new ArrayList<>();

    public User(String username, String password, int id) {
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
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
                .map(raw -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return raw;
                    }
                })
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "Username: " + getUsername() + " Password: " + getPassword();
    }

}
