package com.leedae.fake;

import com.leedae.post.application.CommentService;
import com.leedae.post.application.PostService;
import com.leedae.post.domain.Post;
import com.leedae.post.interfaces.CommentRepository;
import com.leedae.post.interfaces.LikeRepository;
import com.leedae.post.interfaces.PostRepository;
import com.leedae.post.repository.FakeCommentRepository;
import com.leedae.post.repository.FakeLikeRepository;
import com.leedae.post.repository.FakePostRepository;
import com.leedae.user.application.UserRelationService;
import com.leedae.user.application.UserService;
import com.leedae.user.application.interfaces.UserRelationRepository;
import com.leedae.user.application.interfaces.UserRepository;
import com.leedae.user.repository.FakeUserRelationRepository;
import com.leedae.user.repository.FakeUserRepository;

public class FakeObjectFactory {


    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(fakeUserRelationRepository,userService);
    private static final PostService postService = new PostService(userService,fakePostRepository,fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService,postService,fakeCommentRepository,fakeLikeRepository);




    private FakeObjectFactory(){}

    public static UserService getUserService(){
        return userService;
    }

    public static UserRelationService getUserRelationService(){
        return userRelationService;
    }

    public static PostService getPostService(){
        return postService;
    }

    public static CommentService getCommentService(){
        return commentService;
    }
}
