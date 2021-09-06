package io.stars.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.stars.ipldashboard.repository.TeamRepository; 
import io.stars.ipldashboard.repository.MatchRepository; 
import java.util.List;
import io.stars.ipldashboard.model.Team; 
import io.stars.ipldashboard.model.Match;


@RestController
public class TeamController {
    
    @Autowired
    private TeamRepository teamRepository;  

    @Autowired
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository; 
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) { 
        Team team = this.teamRepository.findByTeamName(teamName); 
        int noOfMatchesTobeFetched = 4;
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, noOfMatchesTobeFetched));

        return team;
    } 

}
