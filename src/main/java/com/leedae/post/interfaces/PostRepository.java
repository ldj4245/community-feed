package com.leedae.post.interfaces;

import com.leedae.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
