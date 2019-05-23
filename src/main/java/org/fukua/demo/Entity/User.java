package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fukua.demo.Entity.Enum.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Size(min=5, max=20)
    @NotNull
    private String username;
    @Size(min=4, max=50)
    @NotNull
    private String firstName;
    @Size(min=4, max=50)
    @NotNull
    private String lastName;
    @Size(min=4, max=50)
    private String middleName;
    @Email
    @NotNull
    private String email;

    @OneToMany
    private List<Job> jobs = new ArrayList<>();

    private Boolean isAdmin = false;
    private UserStatus status = UserStatus.ACTIVATION_NEEDED;

    @JsonIgnore
    private String passwordHash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
