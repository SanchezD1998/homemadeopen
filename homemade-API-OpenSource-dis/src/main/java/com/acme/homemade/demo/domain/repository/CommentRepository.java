package com.acme.homemade.demo.domain.repository;

import com.acme.homemade.demo.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPublicationId (Long publicationId, Pageable pageable);

    Page<Comment> findByUserId (Long userId, Pageable pageable);

    Page<Comment> findByRecipeId (Long recipeId, Pageable pageable);

    Optional<Comment> findByIdAndPublicationId (Long id, Long publicationId);

    Optional<Comment> findByIdAndUserId (Long id, Long  userId );

    Optional<Comment> findByIdAndRecipeId (Long id, Long  recipeId );
}
