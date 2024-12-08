package com.leedae.user.repository.entity.jpa;

import com.leedae.user.application.dto.GetUserListResponseDto;
import com.leedae.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity,Long> {

//    - 팔로잉 : 내가 팔로우 하는 사용자 리스트를 뜻하는 용어입니다.
//
//- 팔로워 : 나를 팔로우 하는 사용자 리스트를 뜻하는 용어입니다.




    @Query(value = "SELECT new com.leedae.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);
    //내가 따르는 사람

    @Query(value = "SELECT new com.leedae.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
    //나를 따르는 사람
}


