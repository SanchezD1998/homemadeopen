package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.Comment;
import com.acme.homemade.demo.domain.repository.CommentRepository;
import com.acme.homemade.demo.domain.repository.PublicationRepository;
import com.acme.homemade.demo.domain.repository.UserNoChefRepository;
import com.acme.homemade.demo.domain.service.CommentService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserNoChefRepository userNoChefRepository;


    @Override
    public Page<Comment> getAllCommentByPublicationId(Long publicationId, Pageable pageable) {
        return commentRepository.findByPublicationId(publicationId, pageable);
    }

    @Override
    public Page<Comment> getAllCommentByUserId(Long userId, Pageable pageable) {
        return commentRepository.findByUserId(userId, pageable);
    }

    @Override
    public Comment createComment(Long publicationId,Long userId, Comment comment) {
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", "Id", publicationId);
        if(!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return publicationRepository.findById(publicationId).map(publication -> {
            comment.setPublication(publication);
            return commentRepository.save(comment);
        }).orElseThrow(()->new ResourceNotFoundException("Publication", "Id", publicationId));
    }

    @Override
    public Comment updateComment(Long publicationId, Long userId, Long commentId, Comment comment) {
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", "Id", publicationId);
        if(!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return commentRepository.findById(commentId).map(comment1 ->{
            comment1.setText(comment.getText());
            return commentRepository.save(comment1);
        } ).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long publicationId, Long userId, Long commentId) {
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", "Id", publicationId);
        if(!commentRepository.existsById(commentId))
            throw new ResourceNotFoundException("Comment", "Id", publicationId);
        if(!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

    }
}
