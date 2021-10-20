package main.repos;

import main.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepo extends CrudRepository<Post, Integer> {

    Page<Post> findAll(Pageable page);
    Page<Post> findByTextContaining(String query,Pageable pageable);
    List<Post> findAll();
    Post getById(Integer id);
}
