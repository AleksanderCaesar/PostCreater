package main.service;

import main.api.response.PostListResponse;
import main.api.response.PostResponse;
import main.model.Post;
import main.model.PostVotes;
import main.api.response.UserIdNameResponse;
import main.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostListResponse getPostResponse(){
        PostListResponse postListResponse = new PostListResponse();
        Pageable page = PageRequest.of(0, 10, Sort.by("title"));
        Page<Post> posts = postRepo.getPosts(page);
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

    public PostListResponse getPostListResponse(String mode, int offset, int limit) {
    PostListResponse postListResponse = getPostResponse();


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
        return postRepo.getPosts(PageRequest.of(0, 2)).getSize();
    }
}
