package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.api.response.*;
import org.skillbox.springbootrest.model.*;
import org.skillbox.springbootrest.repository.PostRepository;
import org.skillbox.springbootrest.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public ResponseEntity<PrePostsResponse> getPosts(int offset, int limit, PostFilterMode mode, String query, String date) {
        return getPostsResponse(offset, limit, mode, query, date, postRepository.findAll());
    }

    private ResponseEntity<PrePostsResponse> getPostsResponse(int offset, int limit, PostFilterMode mode, String query,
                                                              String date, List<Post> sourcePosts) {
        List<Predicate<Post>> predicates = new ArrayList<>();
        predicates.add(post -> post.getIsActive() > 0);
        predicates.add(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED));
        if (query != null) {
            predicates.add(post -> post.getText().contains(query.trim()));
        }
        if (date != null) {
            predicates.add(post -> getFormattedDate(post.getTime()).equals(date));
        }
        Predicate<Post> compositPredicate = predicates.stream().reduce(predicate -> true, Predicate::and);
        sourcePosts = sourcePosts.stream().filter(compositPredicate).collect(Collectors.toList());
        Comparator<Post> comparator;
        switch (mode) {
            case recent:
                comparator = Comparator.comparing(Post::getTime).reversed();
                break;
            case best:
                comparator = Comparator.comparingInt(Post::getLikes).reversed().thenComparingInt(Post::getDislikes);
                break;
            case popular:
                comparator = Comparator.comparingInt(Post::getCommentCount).reversed();
                break;
            case early:
                comparator = Comparator.comparing(Post::getTime);
                break;
            default:
                return new ResponseEntity<>(new PrePostsResponse(), HttpStatus.BAD_REQUEST);
        }
        sourcePosts.sort(comparator);
        PrePostsResponse prePostsResponse = new PrePostsResponse();
        prePostsResponse.setCount(sourcePosts.size());
        if (offset >= sourcePosts.size() || offset < 0 || limit <= 0) {
            return new ResponseEntity<>(new PrePostsResponse(), HttpStatus.BAD_REQUEST);
        }
        if (offset + limit > sourcePosts.size()) {
            limit = sourcePosts.size() - offset;
        }
        for (Post post : sourcePosts.subList(offset, offset + limit)) {
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
            prePostResponse.setTimestamp(post.getTime().getTime() / 1000);
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
        Tag tag = tagRepository.findAll().stream().filter(t -> t.getName().equals(tagName.trim().toUpperCase())).findFirst().orElseThrow();
        List<Post> sourcePosts = new ArrayList<>(tag.getPosts());
        return getPostsResponse(offset, limit, PostFilterMode.recent, null, null, sourcePosts);
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
        fullPostResponse.setTimestamp(post.getTime().getTime() / 1000);
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
                String date = getFormattedDate(post.getTime());
                calendarResponse.getPosts().put(date, calendarResponse.getPosts().getOrDefault(date, 0) + 1);
            }
        }
        return new ResponseEntity<>(calendarResponse, HttpStatus.OK);
    }

    private String getFormattedDate(Timestamp timestamp) {
        return DATE_FORMAT.format(timestamp.getTime());
    }
}