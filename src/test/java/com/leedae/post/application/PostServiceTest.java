package com.leedae.post.application;

import com.leedae.fake.FakeObjectFactory;
import com.leedae.post.application.dto.CreatePostRequestDto;
import com.leedae.post.application.dto.LikeRequestDto;
import com.leedae.post.application.dto.UpdatePostRequestDto;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.content.PostPublicationState;
import com.leedae.user.application.UserService;
import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends PostApplicationTestTemplate {



    @Test
    void givenPostRequestDto_whenCreate_thenReturnsPost() {
        // when
        Post savePost = postService.createPost(dto);


        // then
        Post post = postService.getPost(savePost.getId());
        assertEquals(savePost, post);

    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnsUpdatePost() {
        // given
        Post post = postService.createPost(dto);

        // when
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(user.getId(),"this is test content",PostPublicationState.PUBLIC);
        Post updatePost = postService.updatePost(post.getId(), updatePostRequestDto);

        // Then
        assertEquals(post.getId(),updatePost.getId());
        assertEquals(post.getAuthor(),updatePost.getAuthor());
        assertEquals(post.getContent(),updatePost.getContent());
    }

    @Test
    void givenPostCreated_whenLikePost_thenReturnsLikeCount(){

        // given
        Post post = postService.createPost(dto); //어떤 유저가 어떤 컨텐츠를 공개 여부까지 포함해서 Dto로 넘긴후


        // when
        //어느 게시글에 어떤 유저가
        LikeRequestDto likeRequestDto = new LikeRequestDto(post.getId(),otherUser.getId());
        postService.likePost(likeRequestDto); //1번 게시글을 1번 유저가 좋아요를 누른다
        //성공적으로 눌렸으면 게시글의 카운트는 올라가야함


        // Then
        assertEquals(1,post.getLikeCount());


    }

    @Test
    void givenPostCreated_whenLikeTwicePost_thenReturnsLikeCount(){

        // given
        Post post = postService.createPost(dto); //어떤 유저가 어떤 컨텐츠를 공개 여부까지 포함해서 Dto로 넘긴후


        // when
        //어느 게시글에 어떤 유저가
        LikeRequestDto likeRequestDto = new LikeRequestDto(post.getId(),otherUser.getId());
        postService.likePost(likeRequestDto); //1번 게시글을 1번 유저가 좋아요를 누른다
        postService.likePost(likeRequestDto); //1번 게시글을 1번 유저가 좋아요를 누른다


        // Then
        assertEquals(1,post.getLikeCount());


    }

    @Test
    void givenPostCreated_whenLikeAndUnlikePost_thenReturnsLikeCount(){

        // given
        Post post = postService.createPost(dto); //어떤 유저가 어떤 컨텐츠를 공개 여부까지 포함해서 Dto로 넘긴후


        // when
        //어느 게시글에 어떤 유저가 1번 게시글의 1번은 내가 만들어서 안됨. 즉 1번 게시물의 2번 유저가
        LikeRequestDto likeRequestDto = new LikeRequestDto(post.getId(),otherUser.getId());
        postService.likePost(likeRequestDto); //1번 게시글을 1번 유저가 좋아요를 누른다
        postService.unlikePost(likeRequestDto); //1번 게시글을 1번 유저가 좋아요를 누른다


        // Then
        assertEquals(0,post.getLikeCount());
    }

    @DisplayName("게시글 좋아요 0인 상태에서 unLike를 클릭했을 경우 기대값은 0이어야 한다.")
    @Test
    void givenNoLikes_thenUnlike_thenLikeCountRemainsZero(){
        // given
        Post savePost = postService.createPost(dto);


        // when & then
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);
        assertEquals(0,savePost.getLikeCount());
    }



}