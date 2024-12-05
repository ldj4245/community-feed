package com.leedae.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        // given
        String text = "this is a test";

        // When
        PostContent postContent = new PostContent(text);

        // Then
        assertEquals(text,postContent.contentText);


    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowsError(){
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class,() -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 숢"})
    void givenContentLengthIsOverAndKorea_whenCreated_thenThrowError(String korean){
        //given
        String content = korean.repeat(501);

        // when & Then

        assertThrows(IllegalArgumentException.class,() -> new PostContent(content));

    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError(){
        // given
        String content = "a".repeat(4);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value){
        //when & then
        assertThrows(IllegalArgumentException.class,() -> new PostContent(value));

    }

    @Test
    void given_when_then(){
        //given
        String beforeUpdatedContent = "before";
        String afterUpdatedContent = "after";


        //when & then
        PostContent postContent = new PostContent(beforeUpdatedContent);

        postContent.updateContent(afterUpdatedContent);


        assertEquals(afterUpdatedContent,postContent.contentText);
    }




}