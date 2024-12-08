package com.leedae.user.repository;

import com.leedae.user.application.interfaces.UserRelationRepository;
import com.leedae.user.domain.User;
import com.leedae.user.repository.entity.UserEntity;
import com.leedae.user.repository.entity.UserRelationEntity;
import com.leedae.user.repository.entity.UserRelationIdEntity;
import com.leedae.user.repository.entity.jpa.JpaUserRelationRepository;
import com.leedae.user.repository.entity.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;


    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);

    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));

    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id= new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user),new UserEntity(targetUser)));


    }
}
