package main.service;

import main.api.response.CalendarResponse;
import main.api.response.PostListResponse;
import main.api.response.PostResponse;
import main.api.response.UserIdNameResponse;
import main.model.Post;
import main.model.PostVotes;
import main.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostListResponse getPostResponse(String mode, int offset, int limit){
        PostListResponse postListResponse = new PostListResponse();
        Page<Post> posts = getPagedPosts(offset, limit);
        for(Post pst : posts){
            postListResponse.getPostsList().add(fillPostResponse(pst));
        }
        postListResponse.setCount((int) posts.getTotalElements());

        return postListResponse;
    }

    public PostResponse fillPostResponse(Post pst) {
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
        return postResponse;
    }

    public PostListResponse getPostListResponse(String mode, int offset, int limit) {
    PostListResponse postListResponse = getPostResponse(mode, offset, limit);

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

    public Page<Post> getPagedPosts(int offset, int limit) {
        Pageable page = PageRequest.of(offset/limit, limit);
        Page<Post> posts = postRepo.findAll(page);
        return posts;
    }

    public PostListResponse findByQuery(String query, String mode, int offset, int limit){
        PostListResponse response = new PostListResponse();
        //TODO решить проблему с пагинацией
        Page<Post> pagedPosts = getPagedPosts(offset, limit);
        for(Post pst : pagedPosts){
            if(pst.getText().toLowerCase().contains(query.trim().toLowerCase()) ||
                    pst.getTitle().toLowerCase().contains(query.trim().toLowerCase())) {
                response.getPostsList().add(fillPostResponse(pst));
            }
        }
        response.setCount(response.getPostsList().size());
        return response;
    }

    public CalendarResponse getPostsByYears(){
        CalendarResponse response = new CalendarResponse();
        Map<String, Integer> postMap = new HashMap<>();
        Iterable<Post> posts = postRepo.findAll();
       posts.forEach(it -> {
           if(postMap.containsKey(it.getTime().toString().substring(0,10))) {
            postMap.put(it.getTime().toString().substring(0, 10),
                    postMap.get(it.getTime().toString().substring(0, 10)) +1);
           } else postMap.put(it.getTime().toString().substring(0, 10), 1);
       });
       int[] year = new int [1];
       year[0] = 2021;
       response.setYears(year);
       response.setPosts(postMap);
       return response;
    }

}
