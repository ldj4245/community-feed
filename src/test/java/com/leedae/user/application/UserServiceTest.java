package com.leedae.user.application;

import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.application.interfaces.UserRepository;
import com.leedae.user.domain.User;
import com.leedae.user.domain.UserInfo;
import com.leedae.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfo_whenCreateUser_thenCanFindUser(){
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test","");


        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getInfo(), savedUser.getId());
        assertEquals("test",userInfo.getName());


    }



}