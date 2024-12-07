package com.leedae.post.repository.entity.like;


import com.leedae.common.repository.entity.TimeBaseEntity;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.comment.Comment;
import com.leedae.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeUser){
        this.id = new LikeIdEntity(post.getId(), likeUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser){
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }



}
