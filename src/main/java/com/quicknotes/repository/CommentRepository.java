package com.quicknotes.repository;

import com.quicknotes.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {

    List<Comment> findAllByLikes(double likes, Pageable pageable);

    List<Comment> findTenLikesProducts(double likes, Pageable pageable);

    Page<Comment> getAllComments();



}
