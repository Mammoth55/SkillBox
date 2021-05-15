package org.skillbox.springbootrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonProperty("is_active")
    private int isActive;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('NEW', 'ACCEPTED', 'DECLINED')")
    @JsonProperty("moderation_status")
    private ModerationStatus moderationStatus;

    @JsonProperty("moderator_id")
    private int moderatorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @JsonProperty("view_count")
    private int viewCount;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "tag2post",
            joinColumns = @JoinColumn(name = "postId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Set<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<PostComment> postComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<PostVote> postVotes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public ModerationStatus getModerationStatus() {
        return moderationStatus;
    }

    public void setModerationStatus(ModerationStatus moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Set<PostVote> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(Set<PostVote> postVotes) {
        this.postVotes = postVotes;
    }

    public int getLikes() {
        int result = 0;
        for (PostVote postVote : postVotes) {
            if (postVote.getValue() > 0) {
                result++;
            }
        }
        return result;
    }

    public int getDislikes() {
        int result = 0;
        for (PostVote postVote : postVotes) {
            if (postVote.getValue() < 0) {
                result++;
            }
        }
        return result;
    }

    public int getCommentCount() {
        return postComments.size();
    }
}