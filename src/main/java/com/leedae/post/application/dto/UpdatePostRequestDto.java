package com.leedae.post.application.dto;

import com.leedae.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {


}