package com.leedae.post.repository.entity.like;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LikeIdEntity {
    private Long targetId; //post냐 comment의 ID
    private Long userId; //좋아요를 누른 당사자의 ID
    private String targetType; //게시물이냐 댓글이냐의 구분자


}
