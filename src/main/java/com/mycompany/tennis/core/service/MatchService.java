package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;


    public MatchService(){
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void enregistrerNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }
}
