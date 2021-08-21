package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.Publication;
import com.acme.homemade.demo.domain.repository.PublicationRepository;
import com.acme.homemade.demo.domain.repository.UserNoChefRepository;
import com.acme.homemade.demo.domain.service.PublicationService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserNoChefRepository userNoChefRepository;

    @Override
    public Publication getPublicationById(Long publicationId) {
        return publicationRepository.findById(publicationId).orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
    }

    @Override
    public Page<Publication> getAllPublicationByUserId(Long userId, Pageable pageable) {
        return publicationRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<Publication> getAllPublication(Pageable pageable) {
        return publicationRepository.findAll(pageable);
    }

    @Override
    public Publication getPublicationByIdAndUserId(Long userId, Long publicationId) {
        return publicationRepository.findByIdAndUserId(publicationId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id " + publicationId +
                        " and PostId " + userId));
    }

    @Override
    public Publication createPublication(Long userId, Publication publication) {
        return userNoChefRepository.findById(userId).map( userNoChef -> {
            publication.setUser(userNoChef);
            return publicationRepository.save(publication);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "UserId", "Id", userId));
    }

    @Override
    public Publication updatePublication(Long userId, Long publicationId, Publication publication) {
        if (!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return publicationRepository.findById(publicationId).map(publication1 -> {
            publication1.setDate(publication.getDate());
            publication1.setText(publication.getText());
            return publicationRepository.save(publication1);
        }).orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
    }

    @Override
    public ResponseEntity<?> deletePublication(Long userId, Long publicationId) {
        if (!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return publicationRepository.findById(publicationId).map(publication -> {
            publicationRepository.delete(publication);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
    }
}
