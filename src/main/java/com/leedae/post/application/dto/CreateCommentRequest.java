package com.leedae.post.application.dto;

public record CreateCommentRequest(Long postId, Long userId, String content) {
}
