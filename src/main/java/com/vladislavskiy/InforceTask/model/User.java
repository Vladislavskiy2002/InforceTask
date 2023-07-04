package com.vladislavskiy.InforceTask.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity(name = "user_table")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must be only alph symb")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "surname must be only alph symb")
    private String surname;
    @NotBlank(message = "email is mandatory")
    @Pattern(regexp = "[a-zA-Z0-9]{4,15}@[a-zA-Z]{2,10}.[a-zA-Z]{2,5}", message = "email isn't correct! Example of email: name@gmail.com")
    private String email;
    @Transient
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Column(name = "password")
    private String hashPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id")
    )
    private Collection<Role> roles;
    public User() {
    }

    public User(Long id, String name, String surname, String email, String password, String hashPassword) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.hashPassword = hashPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                '}';
    }
}
