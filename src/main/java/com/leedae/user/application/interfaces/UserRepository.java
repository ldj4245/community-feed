package com.leedae.user.application.interfaces;

import com.leedae.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository{

    User save(User user);

    User findById(Long id);
}
