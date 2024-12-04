package com.leedae.user.domain;

import com.leedae.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCounter;


    public User(Long id, UserInfo userInfo) {
        if(userInfo == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    public void increaseFollowerCount(){
        followerCounter.increase();
    }

    public void decreaseFollowerCount(){
        followerCounter.decrease();
    }

    public Long getId() {
        return id;
    }

    public int followerCount(){
        return followerCounter.getCount();
    }

    public int followingCount(){
        return followingCount.getCount();
    }

    public UserInfo getInfo() {
            return info;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
