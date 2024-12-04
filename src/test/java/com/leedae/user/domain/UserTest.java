package com.leedae.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {

        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);

    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        //when
        boolean isSame = user1.equals(user2);

        //then
        assertFalse(isSame);
    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
        //given
        User sameUser = new User(1L, userInfo);

        // when
        boolean isSame = user1.equals(sameUser);

        // Then
        assertEquals(isSame, true);
    }

    @Test
    void givenTwoUser_whenHashCode_thenNotEqual() {
        // when
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();

        // then
        assertNotEquals(hashcode1, hashcode2);

    }

    @Test
    void givenTwoSameIdUser_whenHashCode_thenEqual() {
        //given
        User sameUser = new User(1L, userInfo);

        // when
        int hashcode1 = user1.hashCode();
        int hashcode2 = sameUser.hashCode();


        assertEquals(hashcode1, hashcode2);

    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        // when
        user1.follow(user2);

        // then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(1, user2.followerCount());
        assertEquals(0, user2.followingCount());

    }

    @Test
    void givenTwoUser_whenUser1UnFollowUser2_thenDecreaseUserCount() {
        // when
        user1.follow(user2);
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followerCount());
        assertEquals(0, user2.followingCount());

    }

    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount() {
        //when
        user1.unfollow(user2);

        // when
        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followerCount());
        assertEquals(0, user2.followingCount());

    }
}





