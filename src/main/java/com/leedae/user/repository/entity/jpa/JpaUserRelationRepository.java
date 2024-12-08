package com.leedae.user.repository.entity.jpa;

import com.leedae.user.repository.entity.UserRelationEntity;
import com.leedae.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
