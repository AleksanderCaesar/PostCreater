package main.service;

import main.api.response.CalendarResponse;
import main.api.response.PostListResponse;
import main.api.response.PostResponse;
import main.api.response.UserIdNameResponse;
import main.model.Post;
import main.model.PostVotes;
import main.model.TagToPost;
import main.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public PostListResponse getPostListResponse(String mode, int offset, int limit) {
        PostListResponse postListResponse = new PostListResponse();

        Pageable page = PageRequest.of(offset/limit, limit);
        Page<Post> posts = postRepo.findAllPostRecent(page);
        if(mode.equals("popular")) {
            posts = postRepo.findAllSortedByComments(page);
        }
        if(mode.equals("best")) {
            posts = postRepo.findAllSortedByLikes(page);
        }
        if(mode.equals("recent")) {
            posts = postRepo.findAllPostRecent(page);
        }
        if(mode.equals("early")) {
            posts = postRepo.findAllPostEarly(page);
        }

        for(Post pst : posts){
            postListResponse.getPostsList().add(fillPostResponse(pst));
        }
        postListResponse.setCount(posts.getTotalElements());

        return postListResponse;
    }

    public PostResponse fillPostResponse(Post pst) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(pst.getId());
        postResponse.setTimestamp(pst.getTime().getTime()/1000);
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

    public PostListResponse findByQuery(String query, String mode, int offset, int limit){
        PostListResponse response = new PostListResponse();
        Pageable page = PageRequest.of(offset/limit, limit, Sort.by(mode));
        Page<Post> pagedPosts = postRepo.findByTextContaining(query, page);
        response.setPostsList(pagedPosts
                        .stream()
                        .map(this::fillPostResponse)
                        .collect(Collectors.toList()));
        response.setCount(pagedPosts.getTotalElements());
        return response;
    }

    public PostListResponse findByDate(String time, int offset, int limit) throws ParseException {
        Date onlyDate = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        PostListResponse response = new PostListResponse();
        Pageable page = PageRequest.of(offset/limit, limit);
        Page<Post> pagedPosts = postRepo.findPostsByDate(onlyDate.toInstant(), page);
        response.setPostsList(pagedPosts
                .stream()
                .map(this::fillPostResponse)
                .collect(Collectors.toList()));
        response.setCount(pagedPosts.getTotalElements());
        return response;
    }

    public PostListResponse findByTag(String tag, int offset, int limit) throws ParseException {
        PostListResponse response = new PostListResponse();
        Pageable page = PageRequest.of(offset/limit, limit);
        //TODO имя тэга довльно глубоко зарыто в классах, наверно тут  лучше  написать метод поиска на стороне sql запроса

//        Page<Post> pagedPosts = postRepo.findPostByTagToPosts(tag, page);
//        response.setPostsList(pagedPosts
//                .stream()
//                .map(this::fillPostResponse)
//                .collect(Collectors.toList()));
//        response.setCount(pagedPosts.getTotalElements());
        return response;
    }

    public CalendarResponse getPostsByYears() {
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
