package com.leedae.user.repository;

import com.leedae.user.application.interfaces.UserRepository;
import com.leedae.user.domain.User;
import com.leedae.user.repository.entity.UserEntity;
import com.leedae.user.repository.entity.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository.findById(id) //jpaRepository를 통해 조회해서 엔티티가 없으면 IllegalException
                .orElseThrow(IllegalArgumentException::new);


        return entity.toUser();

    }
}
