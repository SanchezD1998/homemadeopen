package com.acme.homemade.demo.resource;

import java.util.Date;

public class UserNoChefResource {

    private Long id;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String Gender;
    private String profilePicture;
    private boolean connected;
    private Boolean membership;

    public Long getId() {
        return id;
    }

    public UserNoChefResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserNoChefResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserNoChefResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public UserNoChefResource setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserNoChefResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserNoChefResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return Gender;
    }

    public UserNoChefResource setGender(String gender) {
        Gender = gender;
        return this;
    }

    public boolean isConnected() {
        return connected;
    }

    public UserNoChefResource setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }

    public Boolean getMembership() {
        return membership;
    }

    public UserNoChefResource setMembership(Boolean membership) {
        this.membership = membership;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserNoChefResource setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
