package com.leedae.user.ui;

import com.leedae.common.ui.Response;
import com.leedae.user.application.UserService;
import com.leedae.user.application.dto.CreateUserRequestDto;
import com.leedae.user.application.dto.GetUserListResponseDto;
import com.leedae.user.application.dto.GetUserResponseDto;
import com.leedae.user.domain.User;
import com.leedae.user.repository.entity.jpa.JpaUserListQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository userListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto){
        User user = userService.createUser(dto);
        return Response.ok(user.getId());

    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId){
        return Response.ok(userService.getUserProfile(userId));
    }


    //내가 따르는 사람 목록조회
    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId")Long userId){

        //userId를 따르는 사람 팔로워 목록
        //ex 1번이면 3번
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId")Long userId){

        //userId를 따르는 사람 팔로잉 목록
        //ex 3번이면 1번의 Following userId에 있는 목록이 반환되겠지
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowerUserList(userId);

        return Response.ok(result);
    }

}
