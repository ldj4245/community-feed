package com.leedae.user.ui;


import com.leedae.common.ui.Response;
import com.leedae.user.application.UserRelationService;
import com.leedae.user.application.dto.FollowUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService relationService;


    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto){
        relationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto){
        relationService.unfollow(dto);
        return Response.ok(null);
    }
}
