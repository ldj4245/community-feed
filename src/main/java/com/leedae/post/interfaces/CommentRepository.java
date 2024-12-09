package com.leedae.post.interfaces;

import com.leedae.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);
}
