package main.controller;

import main.api.response.InitResponse;
import main.api.response.PostResponse;
import main.api.response.SettingsResponse;

import main.api.response.TagResponse;
import main.model.Tags;
import main.service.PostService;
import main.service.SettingsService;
import main.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiGeneralController
{
    private final InitResponse initResponse;
    private final SettingsService settingsService;
    private TagService tagService;
    private PostService postService;

    public ApiGeneralController(InitResponse initResponse, SettingsService settingsService, TagService tagService, PostService postService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
        this.tagService = tagService;
        this.postService = postService;
    }

    @GetMapping("/settings")
    private SettingsResponse settings(){

        return settingsService.getGlobalSettings();
    }

    @GetMapping("/init")
    private InitResponse init(){

        return initResponse;
    }

    @GetMapping("/tag")
    private TagResponse getTags(){
        return tagService.getTagList();
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
