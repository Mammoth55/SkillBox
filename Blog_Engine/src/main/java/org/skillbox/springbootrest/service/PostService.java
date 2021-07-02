package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.api.response.*;
import org.skillbox.springbootrest.model.*;
import org.skillbox.springbootrest.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
public class PostService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final int MILLIS_IN_SECOND = 1000;
    private static final Sort DEFAULT_SORT = Sort.by("time").descending();

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PrePostsResponse> getAllPosts(int offset, int limit, PostFilterMode mode) {
        Page<Post> posts;
        switch (mode) {
            case best:
                posts = postRepository.findAllMostLikedPosts(PageRequest.of(offset, limit));
                break;
            case popular:
                posts = postRepository.findAllMostCommentedPosts(PageRequest.of(offset, limit));
                break;
            case early:
                posts = postRepository.findAll(PageRequest.of(offset, limit, Sort.by("time").ascending()));
                break;
            default:
                posts = postRepository.findAll(PageRequest.of(offset, limit, DEFAULT_SORT));
        }
        return getPostsResponse(posts);
    }

    public ResponseEntity<PrePostsResponse> getPostsBySearch(int offset, int limit, String query) {
        return getPostsResponse(postRepository.findAllBySearch(query, PageRequest.of(offset, limit, DEFAULT_SORT)));
    }

    public ResponseEntity<PrePostsResponse> getPostsByDate(int offset, int limit, String date) throws ParseException {
        return getPostsResponse(postRepository.findAllByDate(date, PageRequest.of(offset, limit, DEFAULT_SORT)));
    }

    private ResponseEntity<PrePostsResponse> getPostsResponse(Page<Post> posts) {
        PrePostsResponse prePostsResponse = new PrePostsResponse();
        prePostsResponse.setCount((int) posts.getTotalElements());
        for (Post post : posts) {
            int likeCount = 0;
            int dislikeCount = 0;
            for (PostVote vote : post.getPostVotes()) {
                if (vote.getValue() > 0) {
                    likeCount++;
                } else if (vote.getValue() < 0) {
                    dislikeCount++;
                }
            }
            PrePostResponse prePostResponse = new PrePostResponse();
            prePostResponse.setId(post.getId());
            prePostResponse.setTimestamp(post.getTime().getTime() / MILLIS_IN_SECOND);
            prePostResponse.setUser(new SimpleUser(post.getUser().getId(), post.getUser().getName()));
            prePostResponse.setTitle(post.getTitle());
            prePostResponse.setAnnounce(getAnnounceTagLess(post.getText()));
            prePostResponse.setLikeCount(likeCount);
            prePostResponse.setDislikeCount(dislikeCount);
            prePostResponse.setCommentCount(post.getPostComments().size());
            prePostResponse.setViewCount(post.getViewCount());
            prePostsResponse.getPosts().add(prePostResponse);
        }
        return new ResponseEntity<>(prePostsResponse, HttpStatus.OK);
    }

    public ResponseEntity<PrePostsResponse> getPostsByTag(int offset, int limit, String tagName) {
        Page<Post> posts = postRepository.findAllByTagsContains(new Tag(tagName), PageRequest.of(offset, limit, Sort.by("time").descending()));
        return getPostsResponse(posts);
    }

    public ResponseEntity<FullPostResponse> getPostsById(int id) {
        FullPostResponse fullPostResponse = new FullPostResponse();
        boolean isPublished = true;
        int likeCount = 0, dislikeCount = 0;
        Optional<Post> optional = postRepository.findById(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(fullPostResponse, HttpStatus.NOT_FOUND);
        }
        Post post = optional.get();
        if (post.getIsActive() == 0 || !post.getModerationStatus().equals(ModerationStatus.ACCEPTED)) {
            isPublished = false;
        }
        for (PostVote vote : post.getPostVotes()) {
            if (vote.getValue() > 0) {
                likeCount++;
            } else if (vote.getValue() < 0) {
                dislikeCount++;
            }
        }
        fullPostResponse.setId(post.getId());
        fullPostResponse.setTimestamp(post.getTime().getTime() / MILLIS_IN_SECOND);
        fullPostResponse.setActive(isPublished);
        fullPostResponse.setUser(new SimpleUser(post.getUser().getId(), post.getUser().getName()));
        fullPostResponse.setTitle(post.getTitle());
        fullPostResponse.setText(post.getText());
        fullPostResponse.setLikeCount(likeCount);
        fullPostResponse.setDislikeCount(dislikeCount);
        fullPostResponse.setViewCount(post.getViewCount());
        for (PostComment comment : post.getPostComments()) {
            PostCommentResponse postCommentResponse = new PostCommentResponse();
            postCommentResponse.setId(comment.getId());
            postCommentResponse.setTimestamp(comment.getTimestamp());
            postCommentResponse.setText(comment.getText());
            postCommentResponse.setUser(new SimpleUserWithPhoto(comment.getUser().getId(), comment.getUser().getName(), comment.getUser().getPhoto()));
            fullPostResponse.getComments().add(postCommentResponse);
        }
        for (Tag tag : post.getTags()) {
            fullPostResponse.getTags().add(tag.getName());
        }
        post.incViewCount();
        postRepository.save(post);
        return new ResponseEntity<>(fullPostResponse, HttpStatus.OK);
    }

    private String getAnnounceTagLess(String text) {
        String announce = text.replaceAll("#[а-яёА-ЯЁa-zA-Z0-9_]+", "");
        if (announce.length() > 150) {
            announce = announce.substring(0, 150);
        }
        return announce.trim() + "...";
    }

    public ResponseEntity<CalendarResponse> getCalendar(Integer year) {
        CalendarResponse calendarResponse = new CalendarResponse();
        year = year == null ? Calendar.getInstance().get(Calendar.YEAR) : year;
        for (Post post : postRepository.findAll()) {
            calendarResponse.getYears().add(post.getTime().toLocalDateTime().getYear());
            if (post.getIsActive() > 0 && post.getModerationStatus().equals(ModerationStatus.ACCEPTED)
                    && post.getTime().toLocalDateTime().getYear() == year) {
                String date = getToFormattedDate(post.getTime());
                calendarResponse.getPosts().put(date, calendarResponse.getPosts().getOrDefault(date, 0) + 1);
            }
        }
        return new ResponseEntity<>(calendarResponse, HttpStatus.OK);
    }

    private String getToFormattedDate(Timestamp timestamp) {
        return DATE_FORMAT.format(timestamp.getTime());
    }

    private Timestamp getFromFormattedDate(String date) throws ParseException {
        return new Timestamp(DATE_FORMAT.parse(date).getTime());
    }
}