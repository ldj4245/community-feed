package com.leedae.post.domain.comment;


import com.leedae.common.domain.PositiveIntegerCounter;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.content.Content;
import com.leedae.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;


    public Comment(Long id, Post post, User author, Content content, PositiveIntegerCounter likeCount) {
        if(author == null){
            throw new IllegalArgumentException();
        }

        if(post == null){
            throw new IllegalArgumentException();
        }

        if(content == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike(){
        this.likeCount.decrease();
    }

    public void updateComment(User user, String updateComment){
        if(!this.equals(user)){
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateComment);
    }
}
