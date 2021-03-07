package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostListResponse {

    @JsonProperty
    private Integer count;
    @JsonProperty("posts")
    private List<PostResponse> postsList;

    public PostListResponse() {
        postsList = new ArrayList<>();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PostResponse> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<PostResponse> postsList) {
        this.postsList = postsList;
    }
}
