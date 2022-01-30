package com.quicknotes.service;

import com.quicknotes.entity.Comment;
import com.quicknotes.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {


    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }


    public Page<Comment> getComments(final int pageNumber, final int size) {
        return repository.findAll(PageRequest.of(pageNumber, size));
    }


    public Optional<Comment> getComment(final int id) {

        return repository.findById(id);
    }

    public Comment save(final Comment comment) {
        return repository.save(comment);
    }

    public void delete(final int id) {
        repository.deleteById(id);
    }


}
