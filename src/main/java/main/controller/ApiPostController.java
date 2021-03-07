package main.controller;

import main.api.response.PostListResponse;
import main.api.response.PostResponse;
import main.api.response.TagResponse;
import main.service.PostService;
import main.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
                                      @RequestParam("limit") int limit){
        PostListResponse postListResponse = postService.getPostResponse();


        if(postListResponse.getPostsList().isEmpty()){
            return new PostListResponse();
        }
        else return sortByParam(postListResponse, mode, offset, limit) ;
    }

    public PostListResponse sortByParam(PostListResponse postListResponse,
                                        String mode, int offset, int limit){
        if(mode.equals("popular")){
            Collections.sort(postListResponse.getPostsList(), new Comparator<PostResponse>() {
                @Override
                public int compare(PostResponse o1, PostResponse o2) {
                  if(o1.getCommentCount() <= o2.getCommentCount()) return    1;
                  else  return -1;
                }
            });
        }
        if(mode.equals("best")){
            Collections.sort(postListResponse.getPostsList(), new Comparator<PostResponse>() {
                @Override
                public int compare(PostResponse o1, PostResponse o2) {
                    if(o1.getLikeCount() <= o2.getLikeCount()) return    1;
                    else  return -1;
                }
            });
        }
        if(mode.equals("recent")){
            postListResponse.getPostsList().sort(new Comparator<PostResponse>() {
                @Override
                public int compare(PostResponse o1, PostResponse o2) {
                    if(o1.getTimestamp() <= o2.getTimestamp()) return    1;
                    else  return -1;
                }
            });
        }
        if(mode.equals("early")){
            postListResponse.getPostsList().sort(new Comparator<PostResponse>() {
                @Override
                public int compare(PostResponse o1, PostResponse o2) {
                    if(o1.getTimestamp() >= o2.getTimestamp()) return    1;
                    else  return -1;
                }
            });
        }
        postListResponse.setPostsList(postListResponse.getPostsList()
                .stream().limit(limit)
                .collect(Collectors.toList()));
        return postListResponse;
    }

    @GetMapping("/tag")
    private TagResponse getTags(){
        return tagService.getTagResponse();
    }
}
