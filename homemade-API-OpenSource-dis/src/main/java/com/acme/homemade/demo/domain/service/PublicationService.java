package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PublicationService {

    Publication getPublicationById (Long publicationId);

    Page<Publication> getAllPublicationByUserId(Long userId, Pageable pageable);

    Page<Publication> getAllPublication (Pageable pageable);

    Publication getPublicationByIdAndUserId (Long userId, Long publicationId);

    Publication createPublication(Long userId, Publication publication);

    Publication updatePublication(Long userId, Long publicationId ,Publication publication);

    ResponseEntity<?>  deletePublication (Long userId, Long publicationId);

}
