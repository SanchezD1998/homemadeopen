package com.acme.homemade.demo.domain.service;


import com.acme.homemade.demo.domain.model.User;

import java.util.List;

public interface DefaultUserDetailsService{
    public List<User> getAll();
}
