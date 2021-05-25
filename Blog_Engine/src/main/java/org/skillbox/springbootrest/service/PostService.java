package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.api.response.CalendarResponse;
import org.skillbox.springbootrest.api.response.PostFilterMode;
import org.skillbox.springbootrest.api.response.PostResponse;
import org.skillbox.springbootrest.api.response.PostsResponse;
import org.skillbox.springbootrest.model.ModerationStatus;
import org.skillbox.springbootrest.model.Post;
import org.skillbox.springbootrest.model.PostVote;
import org.skillbox.springbootrest.model.SimpleUser;
import org.skillbox.springbootrest.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PostsResponse> getPosts(Integer offset, Integer limit, PostFilterMode mode, String query, String date) {
        Predicate<Post> predicate1 = post -> post.getIsActive() > 0;
        Predicate<Post> predicate2 = post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED);
        List<Predicate<Post>> predicates = Arrays.asList(predicate1, predicate2);
        if (query != null) {
            predicates.add(e -> e.getText().contains(query.trim()));
        }
        if (date != null) {
            predicates.add(e -> getFormattedDate(e.getTime()).equals(date));
        }
        Predicate<Post> compositPredicate = predicates.stream().reduce(predicate -> true, Predicate::and);
        List<Post> sourcePosts = postRepository.findAll().stream().filter(compositPredicate).collect(Collectors.toList());
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 10 : limit;
        mode = mode == null ? PostFilterMode.recent : mode;
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
                return new ResponseEntity<>(new PostsResponse(), HttpStatus.BAD_REQUEST);
        }
        sourcePosts.sort(comparator);
        PostsResponse postsResponse = new PostsResponse();
        postsResponse.setCount(sourcePosts.size());
        if (offset >= sourcePosts.size() || offset < 0 || limit <= 0) {
            return new ResponseEntity<>(new PostsResponse(), HttpStatus.BAD_REQUEST);
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
            postsResponse.getPosts().add(new PostResponse(post.getId(), post.getTime().getTime() / 1000,
                    new SimpleUser(post.getUser().getId(), post.getUser().getName()),
                    post.getTitle(), getAnnounceTagLess(post.getText()), likeCount, dislikeCount,
                    post.getPostComments().size(), post.getViewCount()));
        }
        return new ResponseEntity<>(postsResponse, HttpStatus.OK);
    }

    private String getAnnounceTagLess(String text) {
        String announce = text.replaceAll("#[а-яА-Яa-zA-Z0-9_]+", "");
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