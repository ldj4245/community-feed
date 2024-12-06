package com.leedae.post.domain;

import com.leedae.post.domain.content.PostContent;
import com.leedae.post.domain.content.PostPublicationState;
import com.leedae.user.domain.User;
import com.leedae.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    private final UserInfo info = new UserInfo("name","url");
    private final User user = new User(1L,info);
    private final User otherUser = new User(2L,info);

    private final Post post = new Post(1L,user,new PostContent("Paradise"));



    @Test
    void givenPostCreated_whenLike_ThenLikeCountShouldBe1(){
        //when
        post.like(otherUser);

        //then
        assertEquals(1,post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenDisLike_ThenLikeCountShouldBe0(){
        //when
        post.like(otherUser);

        post.unlike();

        //then
        assertEquals(0,post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenSameUserLike_thenReturnsException(){

        //when & Then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0(){
        // when
        post.unlike();


        // when & then
        assertEquals(0,post.getLikeCount());
    }


    @Test
    void givenExistingPost_whenUpdatePost_thenPostIsUpdated(){

        // Given
        String newContent = "new content";


        // when
        post.updatePost(user,newContent, PostPublicationState.PUBLIC);


        assertEquals(newContent,post.getContent());

    }

    @Test
    void givenPostNotOwnedByUser_whenUpdatePost_thenThrowsException(){

        // given
        String newContent = "new";


        // when & then
        assertThrows(IllegalArgumentException.class,() -> post.updatePost(otherUser,newContent,PostPublicationState.PUBLIC));

    }





}