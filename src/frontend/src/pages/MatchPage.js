import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';  
import './MatchPage.scss';
import { YearSelector } from './YearSelector';
import { Link } from 'react-router-dom';
 
export const MatchPage = () => { 

    const [matches, setMatches] = useState([]); 
    const {teamName, year} = useParams(); 

    useEffect(
        () => {
            const fetchMatches = async () => { 
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data); 

            };
            fetchMatches();
        }, [teamName, year]
    ); 

    const noMatches = matches.length === 0; 

    return (
        <div className="MatchPage">  
            <h4 className="home-link"> 
                <Link to={`/team`}>Home</Link>
            </h4>
            <div className="year-selector">
                <h3>Select Year</h3>
                <YearSelector teamName = {teamName}/>
            </div>
            <div>
                <h1 className="page-heading">{teamName} matches in {year}</h1>
                { 
                    noMatches ? <h2 className="no-matches">No Matches Found</h2> :
                                matches.map(match => <MatchDetailCard teamName = {teamName} match = {match} />) 
                }  
            </div>
        </div>
    );
}