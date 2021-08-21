package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.UserChef;
import com.acme.homemade.demo.domain.model.UserNoChef;
import com.acme.homemade.demo.domain.repository.UserChefRepository;
import com.acme.homemade.demo.domain.repository.UserNoChefRepository;
import com.acme.homemade.demo.domain.service.UserChefService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserChefServiceImpl implements UserChefService {

    @Autowired
    private UserChefRepository userChefRepository;

    @Autowired
    private UserNoChefRepository userNoChefRepository;

    @Override
    public Page<UserChef> getAllUserChef(Pageable pageable) {
        return userChefRepository.findAll(pageable);
    }

    @Override
    public UserChef getUserChefById(Long userChefId) {
        return userChefRepository.findById(userChefId).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
    }

    @Override
    public UserChef createUserChef(UserChef userChef) {
        return userChefRepository.save(userChef);
    }

    @Override
    public UserChef updateUserChef(Long userChefId, UserChef userChefRequest) {
        return userChefRepository.findById(userChefId).map(userChef -> {
            userChef.setName(userChefRequest.getName());
            userChef.setLastName(userChefRequest.getLastName());
            userChef.setEmail(userChefRequest.getEmail());
            userChef.setPassword(userChefRequest.getPassword());
            userChef.setDateOfBirth(userChefRequest.getDateOfBirth());
            userChef.setGender(userChefRequest.getGender());
            userChef.setProfilePicture(userChefRequest.getProfilePicture());
            userChef.setCertificate(userChefRequest.getCertificate());
            return userChefRepository.save(userChef);
        }).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
    }

    @Override
    public ResponseEntity<?> deleteUserChef(Long userChefId) {
        return userChefRepository.findById(userChefId).map(userChef -> {
            userChefRepository.delete(userChef);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
    }

    @Override
    public UserChef assignUserChefRecipe(Long userChefId, Long userNoChefId) {
        UserNoChef userNoChef = userNoChefRepository.findById(userNoChefId).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
        return userChefRepository.findById(userNoChefId).map(userChef ->
                userChefRepository.save(userChef.userNoChefWith(userNoChef))
        ).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
    }

    @Override
    public UserChef unassignUserChefRecipe(Long userChefId, Long userNoChefId) {
        UserNoChef userNoChef = userNoChefRepository.findById(userNoChefId).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
        return userChefRepository.findById(userNoChefId).map(userChef ->
                userChefRepository.save(userChef.unUserNoChefWith(userNoChef))
        ).orElseThrow(() -> new ResourceNotFoundException("UserChef", "Id", userChefId));
    }
}
