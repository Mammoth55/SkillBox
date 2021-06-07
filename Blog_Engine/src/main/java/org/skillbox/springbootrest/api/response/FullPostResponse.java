package org.skillbox.springbootrest.api.response;

import org.skillbox.springbootrest.model.SimpleUser;
import java.util.HashSet;
import java.util.Set;

public class FullPostResponse {

    private int id;

    private long timestamp;

    private boolean active;

    private SimpleUser user;

    private String title;

    private String text;

    private int likeCount;

    private int dislikeCount;

    private int viewCount;

    private Set<PostCommentResponse> comments = new HashSet<>();

    private Set<String> tags = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Set<PostCommentResponse> getComments() {
        return comments;
    }

    public void setComments(Set<PostCommentResponse> comments) {
        this.comments = comments;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}