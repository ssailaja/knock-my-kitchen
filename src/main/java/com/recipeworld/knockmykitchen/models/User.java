package com.recipeworld.knockmykitchen.models;

import com.recipeworld.knockmykitchen.annotation.PasswordMatches;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@PasswordMatches(message = "Passwords should be matched!")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Either first name must not be empty or at least contains 3 to 15 characters")
    private String firstName;

    @NotNull
    @Size(min=3, max=15, message = "Either last name must not be empty or at least contains 3 to 15 characters")
    private String lastName;

    @Size(min=1, max=5)
    private String middleName;

    @NotNull
    @Size(min=6, message = "Either user name must not be empty or at least contains 6 characters")
    private String userName;

    @NotNull
    @Size(min=6, message = "Either password must not be empty or at least contains 6 characters")
    private String password;

    @Size(min=6, message = "Confirm Password must not be empty")
    private String confirmPassword;

    @NotNull
    @Email
    private String email;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<Recipe> recipes = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "country_id")
    private List<Country> countries = new ArrayList<>();

    private int active;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String firstName, String lastName,  String userName, String password, String confirmPassword, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public int getId() {
        return id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", recipes=" + recipes +
                ", countries=" + countries +
                ", active=" + active +
                '}';
    }
}