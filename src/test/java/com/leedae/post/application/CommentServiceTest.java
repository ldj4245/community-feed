package com.leedae.post.application;

import com.leedae.post.application.dto.UpdateCommentRequestDto;
import com.leedae.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest extends PostApplicationTestTemplate {


    @Test
    void givenCommentRequestDto_whenCreate_thenReturnsComment() {
        // when
        Comment saveComment = commentService.createComment(commentDto);
        System.out.println("saveComment.getId() = " + saveComment.getId());

        // then
        Comment comment = commentService.getComment(saveComment.getId());


        System.out.println("comment.getId() = " + comment.getId());


    }

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnsComment(){
        // when
        Comment comment = commentService.createComment(commentDto);

        //  then
        String content = comment.getContextText();

        assertEquals(comment.getContextText(), content);
    }

    @Test
    void givenCreateComment_whenUpdate_thenReturnsUpdateComment() {
        // given
        Comment comment = commentService.createComment(commentDto);
        UpdateCommentRequestDto updateDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "update content");

        // when
        Comment updateComment = commentService.updateComment(updateDto);

        // Then
        assertEquals(comment.getId(), updateComment.getId());
        assertEquals(comment.getAuthor(), updateComment.getAuthor());
        assertEquals(comment.getContent(), updateComment.getContent());
    }


}