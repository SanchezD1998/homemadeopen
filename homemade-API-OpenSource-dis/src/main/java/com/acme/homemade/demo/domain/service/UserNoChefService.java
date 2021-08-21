package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.UserNoChef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserNoChefService {
    Page<UserNoChef> getAllUserNoChef(Pageable pageable);

    UserNoChef getUserNoChefById(Long userNoChefId);

    UserNoChef createUserNoChef(UserNoChef userNoChef);

    UserNoChef updateUserNoChef(Long userNoChefId, UserNoChef userNoChefRequest);

    ResponseEntity<?> deleteUserNoChef(Long userNoChefId);

}
