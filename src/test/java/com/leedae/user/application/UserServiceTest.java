package com.leedae.user.application;

import com.leedae.fake.FakeObjectFactory;
import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.application.interfaces.UserRepository;
import com.leedae.user.domain.User;
import com.leedae.user.domain.UserInfo;
import com.leedae.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfo_whenCreateUser_thenCanFindUser(){
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test","");


        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getUserInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test",userInfo.getName());


    }



}