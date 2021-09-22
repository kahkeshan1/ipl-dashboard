package io.stars.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.stars.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
    
    public List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
    
    default List<Match> findLatestMatchesByTeam(String teamName, int count){
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

    @Query("SELECT m from Match m WHERE (m.team1 = :teamName or m.team2 = :teamName) and date BETWEEN :dateStart and :dateEnd")
    public List<Match> getMatchesByTeamBetweenDates(
        @Param("teamName") String teamName,
        @Param("dateStart") LocalDate dateStart,
        @Param("dateEnd") LocalDate dateEnd
    );

    // public List<Match> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    //                 String teamName1, LocalDate date1, LocalDate date2,
    //                 String teamName2, LocalDate date3, LocalDate date4);
    
}
