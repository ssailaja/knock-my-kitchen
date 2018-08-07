package com.recipeworld.knockmykitchen.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Recipe {

    private static final String FORMAT = "MM/dd/yyyy";

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Either name must not be empty or at least contains 3 to 15 characters")
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    @Size(min=1, message = "Created By Field must not be empty")
    private String createdBy;

    @NotNull
    @Size(min=1, message = "Created On Field must not be empty")
    private String createdOn;

    @NotNull
    @Size(min=1, message = "updated By Field must not be empty")
    private String updatedBy;

    @NotNull
    @Size(min=1, message = "updated On Field must not be empty")
    private String updatedOn;

    @ManyToOne
    private Country country;

    public Recipe(String name, String description, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdOn = dateConversion(createdOn);
        this.updatedBy = updatedBy;
        this.updatedOn = dateConversion(updatedOn);
    }

    public Recipe() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", country=" + country +
                '}';
    }

    private String dateConversion(Date date) {
        DateFormat format = new SimpleDateFormat(FORMAT);
        return null != date ? format.format(date) : null;
    }
}