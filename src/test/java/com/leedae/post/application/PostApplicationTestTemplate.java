package com.leedae.post.application;

import com.leedae.fake.FakeObjectFactory;
import com.leedae.post.application.dto.CreateCommentRequest;
import com.leedae.post.application.dto.CreatePostRequestDto;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.content.PostPublicationState;
import com.leedae.user.application.UserService;
import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.domain.User;

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", null));
    final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(dto);
    final CreateCommentRequest commentDto = new CreateCommentRequest(post.getId(), user.getId(), "This is a comment");
}
