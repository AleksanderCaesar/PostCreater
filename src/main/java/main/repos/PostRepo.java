package main.repos;


import main.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PostRepo extends CrudRepository<Post, Integer> {

    @Query("from Post")
    Page<Post> getPosts(Pageable page);
    Post getById(Integer id);
}
