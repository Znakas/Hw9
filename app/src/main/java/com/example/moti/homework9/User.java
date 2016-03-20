package com.example.moti.homework9;

import java.io.Serializable;

/**
 * Created by moti on 3/13/16.
 */
public final class User implements Serializable {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;

    public User(String login, String password, String firstName, String lastName, String gender) {
        setLogin(login);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equals("male") || gender.equals("female"))
            this.gender = gender;
        else throw new IllegalArgumentException();
    }
}