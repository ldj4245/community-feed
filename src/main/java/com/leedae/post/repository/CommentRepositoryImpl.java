package com.leedae.post.repository;

import com.leedae.post.domain.comment.Comment;
import com.leedae.post.interfaces.CommentRepository;
import com.leedae.post.repository.entity.comment.CommentEntity;
import com.leedae.post.repository.entity.jpa.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if(comment.getId() != null){
            //수정작업
            //merge시 select쿼리를 한방 더 날리므로 쿼리 최적화 목적으로
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return comment;
        }
        //저장작업
        commentEntity  = jpaCommentRepository.save(commentEntity);
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();

    }
}
