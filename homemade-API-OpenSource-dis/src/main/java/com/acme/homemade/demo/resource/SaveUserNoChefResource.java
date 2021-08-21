package com.acme.homemade.demo.resource;

import com.sun.istack.NotNull;

import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveUserNoChefResource {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String Gender;

    private String profilePicture;

    private boolean connected;

    @NotNull
    private  boolean membership;

    public String getName() {
        return name;
    }

    public SaveUserNoChefResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveUserNoChefResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public SaveUserNoChefResource setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveUserNoChefResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserNoChefResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return Gender;
    }

    public SaveUserNoChefResource setGender(String gender) {
        Gender = gender;
        return this;
    }

    public boolean isConnected() {
        return connected;
    }

    public SaveUserNoChefResource setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public SaveUserNoChefResource setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public boolean isMembership() {
        return membership;
    }

    public SaveUserNoChefResource setMembership(boolean membership) {
        this.membership = membership;
        return this;
    }
}
