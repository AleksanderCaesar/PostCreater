package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.model.Posts;

import java.util.ArrayList;
import java.util.List;

public class PostsResponse {

    private Integer count;
    @JsonProperty("posts")
    private List<Posts> postsList;

    public PostsResponse(Integer count) {
        this.count = count;
        postsList = new ArrayList<>();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
    }

    @Override
    public String toString() {
        return "PostsResponse{" +
                "count=" + count +
                ", postsList=" + postsList.toString() +
                '}';
    }
}
