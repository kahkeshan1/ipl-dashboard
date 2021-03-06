package io.stars.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.stars.ipldashboard.repository.TeamRepository; 
import io.stars.ipldashboard.repository.MatchRepository;
import io.stars.ipldashboard.model.Match;
import io.stars.ipldashboard.model.Team;  

@RestController
@CrossOrigin
public class TeamController {
    
    @Autowired
    private TeamRepository teamRepository;  

    @Autowired
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository; 
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeams(){
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) { 
        Team team = this.teamRepository.findByTeamName(teamName); 
        int noOfMatchesTobeFetched = 4;
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, noOfMatchesTobeFetched));
        
        return team;
    } 

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {  
        LocalDate startDate = LocalDate.of(year, 1, 1); 
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    } 

}
