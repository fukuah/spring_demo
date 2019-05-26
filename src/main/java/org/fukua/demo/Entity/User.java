package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/*
 *
 *  This entity is made for basic authentication,
 *  it's structure and name are predefined by spring security
 */

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Size(min=5, max=50)
    @NotNull
    private String username;

    @JsonIgnore
    @Size(min=4, max=50)
    @NotNull
    private String password;

    private boolean enabled;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Authorities> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getRoles() { return roles; }

    public void setRoles(List<Authorities> roles) { this.roles = roles; }

    public long getId() { return id; }
}
