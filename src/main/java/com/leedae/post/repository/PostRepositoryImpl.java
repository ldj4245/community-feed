package com.leedae.post.repository;

import com.leedae.post.domain.Post;
import com.leedae.post.interfaces.PostRepository;
import com.leedae.post.repository.entity.jpa.JpaPostRepository;
import com.leedae.post.repository.entity.post.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if(postEntity.getId() != null){
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();

        }
        postEntity = jpaPostRepository.save(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
