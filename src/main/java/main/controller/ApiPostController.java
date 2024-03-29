package main.controller;

import main.api.response.CalendarResponse;
import main.api.response.PostListResponse;
import main.api.response.TagResponse;
import main.service.PostService;
import main.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;


@RestController
@RequestMapping("/api")
public class ApiPostController {
    private final PostService postService;
    private final TagService tagService;

    public ApiPostController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @GetMapping("/post")
    private PostListResponse getPosts(@RequestParam(value = "mode", required = false, defaultValue = "recent") String mode,
                                      @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
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
        PostListResponse searchPostResponse = new PostListResponse();
        searchPostResponse = postService.findByQuery(query,"time", offset, limit);
        return searchPostResponse;
    }

    @GetMapping("/calendar")
    private CalendarResponse getCountPostsByYear(){
        return postService.getPostsByYears();
    }

    @GetMapping("/post/byDate")
    private PostListResponse getPostsByDate(@RequestParam("date") String time,
                                           @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                           @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) throws ParseException {

        PostListResponse searchPostByDate = new PostListResponse();
        searchPostByDate = postService.findByDate(time, offset, limit);
        return searchPostByDate;
    }

    @GetMapping("/post/byTag")
    private PostListResponse getPostsByTag(@RequestParam("tag") String tag,
                                            @RequestParam("offset") int offset,
                                            @RequestParam("limit") int limit) throws ParseException {

        PostListResponse searchPostByTag = new PostListResponse();
        searchPostByTag = postService.findByTag(tag, offset, limit);
        return searchPostByTag;
    }

}
