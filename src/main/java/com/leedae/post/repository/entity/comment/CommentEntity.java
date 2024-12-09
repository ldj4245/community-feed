package com.leedae.post.repository.entity.comment;


import com.leedae.common.domain.PositiveIntegerCounter;
import com.leedae.common.repository.entity.TimeBaseEntity;
import com.leedae.post.domain.comment.Comment;
import com.leedae.post.domain.content.CommentContent;
import com.leedae.post.repository.entity.post.PostEntity;
import com.leedae.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //여러개의 댓글은 하나의 유저에 의해 작성됨
    @ManyToOne
    @JoinColumn(name = "authorid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    //여러개의 댓글은 하나의 게시물에 작성 될 수 있음
    @ManyToOne
    @JoinColumn(name = "postId",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCount;


    public CommentEntity(Comment comment){
        this.id = comment.getId();
        this.author = new UserEntity(comment.getAuthor());
        this.post = new PostEntity(comment.getPost());
        this.content = comment.getContextText();
        this.likeCount = comment.getLikeCount();

    }

    public Comment toComment(){
        return Comment.builder()
                .id(id)
                .author(author.toUser())
                .post(post.toPost())
                .content(new CommentContent(content))
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();
    }






}
