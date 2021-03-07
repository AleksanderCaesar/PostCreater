package main.repos;


import main.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post, Integer> {

    @Query("from Post")
    List<Post> getPosts();
    Post getById(Integer id);
}
