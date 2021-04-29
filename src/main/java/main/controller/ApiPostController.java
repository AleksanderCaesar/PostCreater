package main.controller;

import main.api.response.PostListResponse;
import main.api.response.TagResponse;
import main.service.PostService;
import main.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiPostController {
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    private PostListResponse getPosts(@RequestParam("mode") String mode,
                                      @RequestParam("offset") int offset,
                                      @RequestParam("limit") int limit) {
        return postService.getPostListResponse(mode, offset, limit);
    }
    @GetMapping("/tag")
    private TagResponse getTags(){
        return tagService.getTagResponse();
    }

    @GetMapping("/post/search")
    private PostListResponse getSearchPost(@RequestParam("query") String query,
                                             @RequestParam("offset") int offset,
                                             @RequestParam("limit") int limit){

        if(query.isEmpty() || query.replace(" ","").isEmpty() ){
            return postService.getPostListResponse("recent", offset, limit);
        }
        PostListResponse searchPostResponse = new PostListResponse();
        searchPostResponse = postService.findByQuery(query, offset, limit);
        return searchPostResponse;
    }

}
