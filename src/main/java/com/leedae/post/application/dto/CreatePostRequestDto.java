package com.leedae.post.application.dto;

import com.leedae.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
