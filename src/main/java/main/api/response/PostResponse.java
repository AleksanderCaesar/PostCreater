package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.model.Posts;

import java.util.List;

public class PostResponse {
    @JsonProperty
    private Integer count;
    @JsonProperty("posts")
    private List<Posts> postsList;

    public PostResponse(Integer count) {
        this.count = count;
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
}
