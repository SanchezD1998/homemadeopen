package com.acme.homemade.demo.resource;

import java.util.Date;

public class UserChefResource {
    private Long id;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String Gender;
    private String profilePicture;
    private boolean connected;
    private String certificate;

    public Long getId() {
        return id;
    }

    public UserChefResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserChefResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserChefResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public UserChefResource setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserChefResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserChefResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return Gender;
    }

    public UserChefResource setGender(String gender) {
        Gender = gender;
        return this;
    }


    public boolean isConnected() {
        return connected;
    }

    public UserChefResource setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserChefResource setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public String getCertificate() {
        return certificate;
    }

    public UserChefResource setCertificate(String certificate) {
        this.certificate = certificate;
        return this;
    }
}
