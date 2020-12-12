package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.utils.TagUtil;

import java.util.List;

public class TagResponse {
    @JsonProperty("tags")
    private List<TagUtil> tagUtilList;

    public List<TagUtil> getTagUtilList() {
        return tagUtilList;
    }

    public void setTagUtilList(List<TagUtil> tagUtilList) {
        this.tagUtilList = tagUtilList;
    }
}
