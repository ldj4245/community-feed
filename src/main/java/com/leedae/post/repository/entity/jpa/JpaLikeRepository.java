package com.leedae.post.repository.entity.jpa;

import com.leedae.post.repository.entity.like.LikeEntity;
import com.leedae.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
