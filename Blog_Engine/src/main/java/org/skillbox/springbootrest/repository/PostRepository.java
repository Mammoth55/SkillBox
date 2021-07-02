package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.Post;
import org.skillbox.springbootrest.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("FROM Post p where (p.isActive > 0) and (p.moderationStatus = 'ACCEPTED') and (p.time < current_time)")
    Page<Post> findAll(Pageable pageable);

    @Query("FROM Post p WHERE :tag IN (p.tags)")
    Page<Post> findAllByTagsContains(@Param("tag") Tag tag, Pageable pageable);

    @Query("FROM Post p where (p.isActive > 0) and (p.moderationStatus = 'ACCEPTED') "
            + "and (p.time < current_time) ORDER BY size(p.postComments) DESC")
    Page<Post> findAllMostCommentedPosts(Pageable pageable);

    @Query(value = "SELECT p.* FROM posts p left join post_votes v on p.id = v.post_id group by p.id order by ((count(v.value) "
            + "+ IFNULL(sum(v.value), 0)) / 2) desc, ((count(v.value) - IFNULL(sum(v.value), 0)) / 2)", nativeQuery = true)
    Page<Post> findAllMostLikedPosts(Pageable pageable);

    @Query("FROM Post p where (p.isActive > 0) and (p.moderationStatus = 'ACCEPTED') "
            + "and (p.time < current_time) and ((p.text like %?1%) or (p.title like %?1%))")
    Page<Post> findAllBySearch(String query, Pageable pageable);

    @Query(value = "SELECT * FROM posts p where (p.is_active > 0) and (p.moderation_status = 'ACCEPTED') "
            + "and (p.time < now()) and DATE_FORMAT(p.time,'%Y-%m-%d') = ?0", nativeQuery = true)
    Page<Post> findAllByDate(String date, Pageable pageable);
}