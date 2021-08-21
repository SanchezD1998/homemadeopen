package com.acme.homemade.demo.resource;

import com.sun.istack.NotNull;

import javax.persistence.Lob;
import java.util.Date;

public class SaveUserChefResource {

    @NotNull
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

    @Lob
    private String profilePicture;

    private boolean connected;

    @Lob
    private String certificate;

    public String getName() {
        return name;
    }

    public SaveUserChefResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveUserChefResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public SaveUserChefResource setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveUserChefResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserChefResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return Gender;
    }

    public SaveUserChefResource setGender(String gender) {
        Gender = gender;
        return this;
    }

    public boolean isConnected() {
        return connected;
    }

    public SaveUserChefResource setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public SaveUserChefResource setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public String getCertificate() {
        return certificate;
    }

    public SaveUserChefResource setCertificate(String certificate) {
        this.certificate = certificate;
        return this;
    }
}
