package main.repos;

import main.model.Tags;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends CrudRepository<Tags, Integer> {

    @Query("from Tags")
    List<Tags> getTags();
}
