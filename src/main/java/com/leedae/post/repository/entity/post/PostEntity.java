package com.leedae.post.repository.entity.post;


import com.leedae.common.domain.PositiveIntegerCounter;
import com.leedae.common.repository.entity.TimeBaseEntity;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.content.PostContent;
import com.leedae.post.domain.content.PostPublicationState;
import com.leedae.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;


    @Convert(converter = PostPublicationStateConverter.class )
    private PostPublicationState state;


    private Integer likeCount;

    public PostEntity(Post post){
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();

    }

    public Post toPost(){
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();

    }



}
