package com.leedae.post.application;

import com.leedae.post.application.dto.CreatePostRequestDto;
import com.leedae.post.application.dto.LikeRequestDto;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.content.Content;
import com.leedae.post.domain.content.PostContent;
import com.leedae.post.interfaces.LikeRepository;
import com.leedae.post.interfaces.PostRepository;
import com.leedae.user.application.UserService;
import com.leedae.user.domain.User;

public class PostService {

    private final UserService userService; //유저의 정보없이 글 작성이 안되기때문에
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not Found"));
    }

    public Post createPost(CreatePostRequestDto dto){

        User author = userService.getUser(dto.userId());
        Content content = new PostContent(dto.content());
        Post post = Post.createPost(null,author,dto.content(),dto.state());
        return postRepository.save(post);

    }

    public Post updatePost(Long id, CreatePostRequestDto dto){
        Post post = getPost(id);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(),dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = postRepository.findById(dto.targetId()).orElseThrow();
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }
        post.like(user);
        likeRepository.like(post, user);

    }

    public void unlikePost(LikeRequestDto dto){
        Post post = postRepository.findById(dto.targetId()).orElseThrow();
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post,user)){
            post.unlike();
            likeRepository.unlike(post,user);
        }

    }


}
