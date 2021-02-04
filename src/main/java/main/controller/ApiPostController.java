package main.controller;

import main.api.response.PostResponse;
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
    private PostResponse getPosts(){
        PostResponse postResponse = postService.getPostResponse();
        if(postResponse.getPostsList().isEmpty()){
            return new PostResponse(0);
        }
        else return postResponse;
    }
}
