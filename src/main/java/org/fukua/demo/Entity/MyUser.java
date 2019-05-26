package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fukua.demo.Entity.Enum.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class MyUser extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Size(min=5, max=20)
    @NotNull
    private String username;

    @JsonIgnore
    @Size(min=5, max=40)
    @NotNull
    private String password;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    public long getId() { return id; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }
}
