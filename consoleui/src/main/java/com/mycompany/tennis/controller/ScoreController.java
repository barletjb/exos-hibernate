package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.ScoreService;

import java.util.Scanner;

public class ScoreController {

    private ScoreService scoreService;

    public ScoreController() {
        this.scoreService = new ScoreService();
    }

    public void afficheDetailsScore() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du score dont vous voulez afficher les infos ? ");
        long identifiant = scanner.nextLong();

        ScoreFullDto score = scoreService.getScore(identifiant);
        System.out.println("Le match de " + score.getMatch().getEpreuve().getTournoi().getNom() + " en " + score.getMatch().getEpreuve().getAnnee()
                + " entre les joueur(ses) : | " + score.getMatch().getVainqueur().getNom() + " " + score.getMatch().getVainqueur().getPrenom() + " & "
                + score.getMatch().getFinaliste().getNom() + " " + score.getMatch().getFinaliste().getPrenom() + " | a eu pour résultat : ");

        System.out.println("Les résultats des sets sont les suivants : ");
        System.out.println(score.getSet1());
        System.out.println(score.getSet2());
        if (score.getSet3() != null){
            System.out.println(score.getSet3());
        }
        if (score.getSet4() != null){
            System.out.println(score.getSet4());
        }
        if (score.getSet5() != null){
            System.out.println(score.getSet5());
        }
        System.out.println("Le vainqueur est : " + score.getMatch().getVainqueur().getNom() + " " + score.getMatch().getVainqueur().getPrenom());

    }

    public void creerScore() {

    }
}
