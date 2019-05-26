package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/*
 *
 *  This entity is made for basic authentication,
 *  it's structure and name are predefined by spring security
 */

@Entity
public class Users {
    @Id
    @Size(min=5, max=50)
    @NotNull
    private String username;

    @JsonIgnore
    @Size(min=5, max=50)
    @NotNull
    private String password;

    private boolean enabled;

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
}
