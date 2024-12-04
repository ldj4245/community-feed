package com.leedae.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveIntegerCounterTest {


    @Test
    void givenCreate_whenIncrease_thenCountIsOne(){
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();


        //then

        assertEquals(1,counter.getCount());
    }


    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();


        // when
        counter.decrease();

        // Then
        assertEquals(counter.getCount(),0);
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero(){

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.decrease();


        //when
        counter.decrease();


        //then
        assertEquals(counter.getCount(), 0);


    }


}