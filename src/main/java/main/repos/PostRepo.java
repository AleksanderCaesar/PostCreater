package main.repos;

import main.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;


public interface PostRepo extends CrudRepository<Post, Integer> {

    Page<Post> findAll(Pageable page);
    Page<Post> findByTextContaining(String query,Pageable pageable);

    @Query(value = "SELECT * FROM posts " +
            "ORDER BY time DESC", nativeQuery = true)
    Page<Post> findAllPostRecent(Pageable paging);

    @Query(value = "SELECT * FROM posts " +
            "ORDER BY time ASC", nativeQuery = true)
    Page<Post> findAllPostEarly(Pageable paging);

    @Query(value = "SELECT *, " +
            "(SELECT count(*) from post_comments " +
            "WHERE post_comments.post_id=posts.post_id) c " +
            "FROM posts ORDER BY c DESC", nativeQuery = true)
    Page<Post> findAllSortedByComments(Pageable paging);

    @Query(value = "SELECT *, " +
            "(SELECT count(*) FROM post_votes WHERE post_votes.post_id = posts.post_id) c " +
            "FROM posts ORDER BY c DESC", nativeQuery = true)
    Page<Post> findAllSortedByLikes(Pageable paging);

    @Query(value = "SELECT * FROM posts " +
            "WHERE time<=?1 " +
            "ORDER BY time DESC", nativeQuery = true)
    Page<Post> findPostsByDate(Instant instant, Pageable page);

    List<Post> findAll();
    Post getById(Integer id);
}
