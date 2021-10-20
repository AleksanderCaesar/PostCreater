package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PostListResponse {

    @JsonProperty
    private Long count;
    @JsonProperty("posts")
    private List<PostResponse> postsList;

    public PostListResponse() {
        postsList = new ArrayList<>();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<PostResponse> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<PostResponse> postsList) {
        this.postsList = postsList;
    }
}
