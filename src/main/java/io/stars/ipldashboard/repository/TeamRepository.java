package io.stars.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository; 
import io.stars.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{
    
    Team findByTeamName(String teamName);
}
