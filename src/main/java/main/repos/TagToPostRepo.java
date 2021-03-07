package main.repos;

import main.model.TagToPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagToPostRepo extends CrudRepository<TagToPost, Integer> {
    @Query("from TagToPost")
    List<TagToPost> getTagToPostList();

    @Query("from TagToPost where tagId.name = :name")
    List<TagToPost> getTagToPostByTagName(String name);
}
