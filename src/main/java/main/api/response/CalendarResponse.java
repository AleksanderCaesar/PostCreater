package main.api.response;

import java.util.Map;

public class CalendarResponse {
    private int[] years;
    private Map<String, Integer> posts;

    public int[] getYears() {
        return years;
    }

    public void setYears(int[] years) {
        this.years = years;
    }

    public Map<String, Integer> getPosts() {
        return posts;
    }

    public void setPosts(Map<String, Integer> posts) {
        this.posts = posts;
    }
}
