package com.leedae.post.repository;

import com.leedae.post.domain.Post;
import com.leedae.post.interfaces.PostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {

    private final Map<Long,Post> store = new HashMap<>();

    @Override
    public Post save(Post pos) {
        if(pos.getId() != null){
            // 이미 존재하는
            store.put(pos.getId(),pos);
            return pos;

        }
        long id = store.size() + 1;
        Post newPost = new Post(id,pos.getAuthor(),pos.getContentObject());
        store.put(id,newPost);
        return newPost;




    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}