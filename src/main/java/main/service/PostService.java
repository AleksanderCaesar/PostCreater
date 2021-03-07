package main.service;

import main.api.response.PostListResponse;
import main.api.response.PostResponse;
import main.model.Post;
import main.model.PostVotes;
import main.model.UserIdNameResponse;
import main.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostListResponse getPostResponse(){
        PostListResponse postListResponse = new PostListResponse();
        List<Post> posts = postRepo.getPosts();
        for(Post pst : posts){
            PostResponse postResponse = new PostResponse();
            postResponse.setId(pst.getId());
            postResponse.setTimestamp(pst.getTime().getTime());
            UserIdNameResponse user = new UserIdNameResponse();
            user.setId(pst.getUser().getId());
            user.setName(pst.getUser().getName());
            postResponse.setUser(user);
            postResponse.setTitle(pst.getTitle());
            postResponse.setAnnounce(pst.getText());
            postResponse.setLikeCount(getLikes(pst.getId()));
            postResponse.setDislikeCount(getDislikes(pst.getId()));
            postResponse.setViewCount(pst.getViewCount());
            postListResponse.getPostsList().add(postResponse);
        }
        postListResponse.setCount(postListResponse.getPostsList().size());

        return postListResponse;
    }

    public Integer getLikes(Integer id) {
        Post post = postRepo.getById(id);
        int count = 0;
        for(PostVotes pv : post.getPostVotes()){
            if(pv.getValue() == 1) count++;
        }
        return count;
    }

    public Integer getDislikes(Integer id) {
        Post post = postRepo.getById(id);
        int count = 0;
        for(PostVotes pv : post.getPostVotes()){
            if(pv.getValue() == -1) count++;
        }
        return count;
    }

    public int getPostsCount(){
        return postRepo.getPosts().size();
    }
}
