package org.fukua.demo.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Size(min=5, max=50)
    @NotNull
    private String username;

    @Size(max=50)
    @NotNull
    private String authority;

    @ManyToOne(targetEntity = Users.class)
    private Users user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
