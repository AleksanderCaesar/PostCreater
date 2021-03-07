package main.repos;

import main.model.PostVotes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostVotesRepo extends CrudRepository<PostVotes, Integer> {

}
