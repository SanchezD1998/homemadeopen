package com.acme.homemade.demo.domain.model;


import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_no_chef")
@PrimaryKeyJoinColumn(name = "users")
public class UserNoChef extends User {

    @NotNull
    private boolean membership;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "userNoChefs")
    private List<UserChef> userChefs;

    public Boolean getMembership() {
        return membership;
    }

    public UserNoChef setMembership(Boolean membership) {
        this.membership = membership;
        return this;
    }

    public List<UserChef> getUserChefs() {
        return userChefs;
    }

    public UserNoChef setUserChefs(List<UserChef> userChefs) {
        this.userChefs = userChefs;
        return this;
    }
}
