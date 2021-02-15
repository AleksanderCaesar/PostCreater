package main.controller;

import main.api.response.PostsResponse;
import main.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiPostController {
    private PostService postService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    private PostsResponse getPosts(){
        PostsResponse postsResponse = postService.getPostResponse();
        System.out.println(postsResponse.toString());
        if(postsResponse.getPostsList().isEmpty()){
            return new PostsResponse(0);
        }
        else return postsResponse;
    }
}
