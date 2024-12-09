package com.leedae.post.application;

import com.leedae.post.application.dto.CreatePostRequestDto;
import com.leedae.post.application.dto.LikeRequestDto;
import com.leedae.post.application.dto.UpdatePostRequestDto;
import com.leedae.post.domain.Post;
import com.leedae.post.domain.content.Content;
import com.leedae.post.domain.content.PostContent;
import com.leedae.post.interfaces.LikeRepository;
import com.leedae.post.interfaces.PostRepository;
import com.leedae.user.application.UserService;
import com.leedae.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService; //유저의 정보없이 글 작성이 안되기때문에
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id){
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto){

        User author = userService.getUser(dto.userId()); //유저 여부 확인
        Content content = new PostContent(dto.content()); //컨텐츠 검증 5자 이상이면 return 이거나 이러한 조건
        Post post = Post.createPost(null,author,dto.content(),dto.state()); //게시글 생성
        return postRepository.save(post); //db에 저장 이때 id가 저장됨

    }

    //id랑 글 쓴 유저와 내용,공개범위 받기
    public Post updatePost(Long id, UpdatePostRequestDto dto){
        Post post = getPost(id); //게시글 가져오기
        User user = userService.getUser(dto.userId()); //유저 가져오기

        post.updatePost(user, dto.content(),dto.state()); //업데이트 실행
        return postRepository.save(post); //DB에 저장
    }

    public void likePost(LikeRequestDto dto) {
        Post post = postRepository.findById(dto.targetId());//게시글 존재 여부 확인
        User user = userService.getUser(dto.userId()); //유저 존재 여부 확인

        if (likeRepository.checkLike(post, user)) { //이미 좋아요가 눌렸다면?
            return; //종료
        }
        post.like(user); //좋아요가 눌리지 않았다면 Like 버튼 활성화
        likeRepository.like(post, user); // 특정 게시글의 리스트에 추가 Ex)1번 게시글 - 1번유저 Map구조로 저장

    }

    public void unlikePost(LikeRequestDto dto){
        Post post = postRepository.findById(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post,user)){
            post.unlike();
            likeRepository.unlike(post,user);
        }

    }


}
