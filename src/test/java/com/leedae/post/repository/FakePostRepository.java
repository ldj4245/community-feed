package com.leedae.post.repository;

import com.leedae.post.domain.Post;
import com.leedae.post.interfaces.PostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {

    private final Map<Long,Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null){
            // 이미 존재하는
            store.put(post.getId(), post);
            return post;

        }
        long id = store.size() + 1;
        Post newPost = new Post(id, post.getAuthor(), post.getContentObject());
        store.put(id,newPost);
        return newPost;




    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
