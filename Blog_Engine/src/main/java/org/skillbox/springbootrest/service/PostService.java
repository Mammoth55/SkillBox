package org.skillbox.springbootrest.service;

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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<PostsResponse> getPosts(Integer offset, Integer limit, PostFilterMode mode) {
        List<Post> sourcePosts = postRepository.findAll().stream().
                filter(e -> e.getIsActive() > 0 || e.getModerationStatus().equals(ModerationStatus.ACCEPTED)).
                collect(Collectors.toList());
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
}