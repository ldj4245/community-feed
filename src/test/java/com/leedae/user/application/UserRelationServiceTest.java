package com.leedae.user.application;

import com.leedae.fake.FakeObjectFactory;
import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.application.dto.FollowUserRequestDto;
import com.leedae.user.application.interfaces.UserRelationRepository;
import com.leedae.user.application.interfaces.UserRepository;
import com.leedae.user.domain.User;
import com.leedae.user.repository.FakeUserRelationRepository;
import com.leedae.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();


    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;


    @BeforeEach
    void init(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test","");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(),user2.getId());

        System.out.println(user1.getId());
        System.out.println(user2.getId());

    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){
        //when
        userRelationService.follow(requestDto);


        //Then
        assertEquals(1,user1.followingCount());
        assertEquals(1,user2.followerCount());


    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError(){
        //given
        userRelationService.follow(requestDto);


        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));


    }

    @Test
    void givenCreateTwoUserUnFollowed_whenUnFollow_thenReturnsZero(){
        //given
        userRelationService.follow(requestDto);
        userRelationService.unfollow(requestDto);


        //when, then
        assertEquals(0,user1.followerCount());
        assertEquals(0,user1.followingCount());
        assertEquals(0,user2.followerCount());
        assertEquals(0,user2.followingCount());



    }

    @Test
    void givenCreateTwoUserUnFollowed_whenUnFollow_thenUserThrowError(){
        //given
        userRelationService.follow(requestDto);  // 0 ->
        userRelationService.unfollow(requestDto); // 0 0


        //when, then
        assertThrows(IllegalArgumentException.class,() -> userRelationService.unfollow(requestDto));



    }

}
