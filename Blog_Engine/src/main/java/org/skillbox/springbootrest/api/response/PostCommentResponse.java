package org.skillbox.springbootrest.api.response;

import org.skillbox.springbootrest.model.SimpleUserWithPhoto;
import java.sql.Timestamp;

public class PostCommentResponse {

    private int id;
    private Timestamp timestamp;
    private String text;
    private SimpleUserWithPhoto user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SimpleUserWithPhoto getUser() {
        return user;
    }

    public void setUser(SimpleUserWithPhoto user) {
        this.user = user;
    }
}