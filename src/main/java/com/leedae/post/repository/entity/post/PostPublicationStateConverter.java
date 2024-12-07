package com.leedae.post.repository.entity.post;

import com.leedae.post.domain.content.PostPublicationState;
import jakarta.persistence.AttributeConverter;

public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {
    @Override
    public String convertToDatabaseColumn(PostPublicationState state) {
        return state.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        return PostPublicationState.valueOf(s);
    }
}
