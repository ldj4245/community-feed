package com.leedae.user.application.interfaces;

import com.leedae.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
