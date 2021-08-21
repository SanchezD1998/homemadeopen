package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getAllCommentByPublicationId(Long publicationId, Pageable pageable);

    Page<Comment> getAllCommentByUserId (Long userId, Pageable  pageable);

    Comment createComment(Long publicationId, Long userId ,Comment comment);

    Comment updateComment(Long publicationId, Long userId , Long commentId,Comment comment);

    ResponseEntity<?> deleteComment(Long publicationId, Long userId , Long commentId);
}
