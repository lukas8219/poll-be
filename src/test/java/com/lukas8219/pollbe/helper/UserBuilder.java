package com.lukas8219.pollbe.helper;

import com.lukas8219.pollbe.data.domain.User;

import java.time.LocalDateTime;

public class UserBuilder {

    private final User user = new User();

    private UserBuilder(Long id) {
        this();
        this.user.setId(id);
    }

    private UserBuilder(){
        this.user.setEmail("email@email.com");
        this.user.setCreatedAt(LocalDateTime.now());
        this.user.setName("Dev");
        this.user.setPhoneNumber("111111");
        this.user.setPassword("123");
    }

    public static UserBuilder newBuilder(Long id) {
        return new UserBuilder(id);
    }

    public static UserBuilder newBuilder(){
        return new UserBuilder();
    }

    public UserBuilder email(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder phoneNumber(String number) {
        this.user.setPhoneNumber(number);
        return this;
    }

    public UserBuilder createdAt(LocalDateTime createdAt) {
        this.user.setCreatedAt(createdAt);
        return this;
    }

    public UserBuilder name(String name) {
        this.user.setName(name);
        return this;
    }

    public User build(){
        return this.user;
    }
}
