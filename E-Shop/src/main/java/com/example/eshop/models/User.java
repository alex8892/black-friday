package com.example.eshop.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    public static final int MIN_USERNAME_LENGTH = 2;
    public static final int MAX_USERNAME_LENGTH = 10;
    public static final String NAME_MESSAGE_ERROR = "Name must be between 2 and 10!";
    public static final int MIN_PASSWORD_LENGTH = 2;
    public static final int MAX_PASSWORD_LENGTH = 16;
    public static final String PASSWORD_MESSAGE_ERROR = "Password must be between 2 and 16!";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = MIN_USERNAME_LENGTH, max = MAX_USERNAME_LENGTH, message = NAME_MESSAGE_ERROR)
    @Column(name = "username")
    private String username;

    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH, message = PASSWORD_MESSAGE_ERROR)
    @Column(name = "password")
    private String password;


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
