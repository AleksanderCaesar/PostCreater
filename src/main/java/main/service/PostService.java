package main.service;

import main.api.response.PostsResponse;
import main.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostsResponse getPostResponse(){
        PostsResponse postsResponse = new PostsResponse(0);
        postsResponse.setPostsList(postRepo.getPosts());
        return postsResponse;
    }
}
