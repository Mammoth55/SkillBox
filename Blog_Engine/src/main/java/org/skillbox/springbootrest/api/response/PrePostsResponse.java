package org.skillbox.springbootrest.api.response;

import java.util.ArrayList;
import java.util.List;

public class PrePostsResponse {

    private int count;
    private List<PrePostResponse> posts = new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PrePostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PrePostResponse> posts) {
        this.posts = posts;
    }
}