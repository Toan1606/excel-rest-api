package com.code.export.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 16, nullable = false)
    private String password;

    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 32)
    private String middleName;

    @Column(name = "last_name", length = 32, nullable = false)
    private String lastName;

    @Column(name = "isEnable", nullable = false)
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
        super();
    }

    public User(Long id, String email, String password, String firstName, String middleName, String lastName, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public User(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public static class Builder {

        private String email;

        private String password;

        private String firstName;

        private String middleName;

        private String lastName;

        private boolean enabled;

        public Builder email(@Email(message = "It has be an email") String email) {
            this.email = this.email;
            return this;
        }

        public Builder password(@NotEmpty(message = "Password must have length between 8 and 16 characters") @Length(min = 8, max = 16) String password) {
            this.password = this.password;
            return this;
        }

        public Builder firstName(@NotEmpty(message = "Firstname can not be empty") String firstName) {
            this.firstName = this.firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = this.middleName;
            return this;
        }

        public Builder lastName(@NotEmpty(message = "Firstname can not be empty") String lastName) {
            this.lastName = this.lastName;
            return this;
        }

        public Builder enabled(@NotNull(message = "User must have status") boolean enabled) {
            this.enabled = this.enabled;
            return this;
        }

        public User build() {
            User user = new User(this);
            return user;
        }

    }
}
