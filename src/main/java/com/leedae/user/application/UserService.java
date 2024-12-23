package com.leedae.user.application;

import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.application.dto.GetUserResponseDto;
import com.leedae.user.application.interfaces.UserRepository;
import com.leedae.user.domain.User;
import com.leedae.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(CreateUserRequestDto dto){
        UserInfo info  = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null,info);
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id); //db에서 조회
    }

    public GetUserResponseDto getUserProfile(Long id){

        User user = getUser(id);
        return new GetUserResponseDto(user);

    }


}
