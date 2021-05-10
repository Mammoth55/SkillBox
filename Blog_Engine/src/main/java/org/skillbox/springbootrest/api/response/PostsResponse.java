package org.skillbox.springbootrest.api.response;

import java.util.ArrayList;
import java.util.List;

public class PostsResponse {

    private int count;

    private List<PostResponse> posts = new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }
}