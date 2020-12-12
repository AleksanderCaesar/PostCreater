package main.service;

import main.api.response.PostResponse;
import main.model.Posts;
import main.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostResponse getPostResponse(){
        List<Posts> postsList = new ArrayList<>();
        postsList.addAll(postRepo.getPosts());
        PostResponse postResponse = new PostResponse(0);
        postResponse.setPostsList(postsList);
        return postResponse;
    }
}
