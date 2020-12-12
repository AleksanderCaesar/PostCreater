package main.repos;


import main.model.Posts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Posts, Integer> {

    @Query("from Posts")
    List<Posts> getPosts();
}
