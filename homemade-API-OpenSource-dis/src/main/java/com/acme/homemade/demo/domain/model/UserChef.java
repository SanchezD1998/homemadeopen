package com.acme.homemade.demo.domain.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_chef")
@PrimaryKeyJoinColumn(name = "users")
public class UserChef extends User {

    private String certificate;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "userChef_userNoChefs",
            joinColumns = {@JoinColumn(name = "userChef_id")},
            inverseJoinColumns = {@JoinColumn(name = "userNoChef_id")})
    private List<UserNoChef> userNoChefs;


    public boolean isUserNoChefWith(UserNoChef userNoChef) {
        return this.getUserNoChefs().contains(userNoChef);
    }

    public UserChef userNoChefWith(UserNoChef userNoChef) {
        if (!this.isUserNoChefWith(userNoChef)) {
            this.getUserNoChefs().add(userNoChef);
        }
        return this;
    }

    public UserChef unUserNoChefWith(UserNoChef userNoChef) {
        if (this.isUserNoChefWith(userNoChef)) {
            this.getUserNoChefs().remove(userNoChef);
        }
        return this;
    }

    public List<UserNoChef> getUserNoChefs() {
        return userNoChefs;
    }

    public UserChef setUserNoChefs(List<UserNoChef> userNoChefs) {
        this.userNoChefs = userNoChefs;
        return this;
    }

    public String getCertificate() {
        return certificate;
    }

    public UserChef setCertificate(String certificate) {
        this.certificate = certificate;
        return this;
    }
}
