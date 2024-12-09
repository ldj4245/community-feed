package com.leedae.post.repository;

import com.leedae.post.domain.Post;
import com.leedae.post.domain.comment.Comment;
import com.leedae.post.interfaces.LikeRepository;
import com.leedae.post.repository.entity.jpa.JpaCommentRepository;
import com.leedae.post.repository.entity.jpa.JpaLikeRepository;
import com.leedae.post.repository.entity.jpa.JpaPostRepository;
import com.leedae.post.repository.entity.like.LikeEntity;
import com.leedae.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post,user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(Post post, User user) {

    }

    @Override
    public void unlike(Post post, User user) {

    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        return false;
    }

    @Override
    public void like(Comment comment, User user) {

    }

    @Override
    public void unlike(Comment comment, User user) {

    }
}
