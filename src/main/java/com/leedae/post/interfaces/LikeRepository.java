package com.leedae.post.interfaces;

import com.leedae.post.domain.Post;
import com.leedae.post.domain.comment.Comment;
import com.leedae.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);
    boolean checkLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unlike(Comment comment, User user);

}
