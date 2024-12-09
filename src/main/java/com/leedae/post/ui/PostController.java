package com.leedae.post.ui;


import com.leedae.common.ui.Response;
import com.leedae.post.application.PostService;
import com.leedae.post.application.dto.CreatePostRequestDto;
import com.leedae.post.application.dto.LikeRequestDto;
import com.leedae.post.application.dto.UpdatePostRequestDto;
import com.leedae.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto){
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody UpdatePostRequestDto dto){
        Post post = postService.updatePost(postId,dto);
        return Response.ok(post.getId());

    }

    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto){
        postService.likePost(dto);
        return Response.ok(null);

    }

    @PostMapping("/unlike")
    public Response<Void> unLikePost(@RequestBody LikeRequestDto dto){
        postService.unlikePost(dto);
        return Response.ok(null);
    }





}
