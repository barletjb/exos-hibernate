package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.service.MatchService;

import java.util.Scanner;

public class MatchController {

    private MatchService matchService;

    public MatchController() {
        this.matchService = new MatchService();
    }

    public void ajouterMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant de l'épreuve du match ? ");
        long idEpreuve = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Entrez l'identifiant du vainqueur du match ? ");
        long idVainqueur = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Entrez l'identifiant du finaliste du match ? ");
        long idFinaliste = scanner.nextLong();
        scanner.nextLine();

        MatchDto matchDto = new MatchDto();
        matchDto.setEpreuve(new EpreuveFullDto());
        matchDto.getEpreuve().setId(idEpreuve);
        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(idVainqueur);
        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(idFinaliste);

        System.out.println("Quel est la valeur du 1er set ? ");
        byte set1 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 2eme set ? ");
        byte set2 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 3eme set ? ");
        byte set3 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 4eme set ? ");
        byte set4 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 5eme set ? ");
        byte set5 = scanner.nextByte();
        scanner.nextLine();

        ScoreFullDto scoreFullDto = new ScoreFullDto();
        scoreFullDto.setSet1(set1);
        scoreFullDto.setSet2(set2);
        scoreFullDto.setSet3(set3);
        scoreFullDto.setSet4(set4);
        scoreFullDto.setSet5(set5);

        matchDto.setScore(scoreFullDto);
        scoreFullDto.setMatch(matchDto);

        matchService.createMatch(matchDto);

    }

    public void tapisVert() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du match que vous voulez annuler ? ");
        long identifiant = scanner.nextLong();
        matchService.tapisVert(identifiant);

    }

    public void afficheDetailsMatch() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du match à afficher ? ");
        long identifiant = scanner.nextLong();

        MatchDto matchDto = matchService.getMatch(identifiant);
        System.out.println("Les informations du match (Id =" + matchDto.getId() + ") sont les suivantes : ");
        System.out.println("Le vainqueur est : " + matchDto.getVainqueur().getNom() + " | " + matchDto.getVainqueur().getPrenom()
                + " | " + matchDto.getVainqueur().getSexe());
        System.out.println("Le finaliste est : " + matchDto.getFinaliste().getNom() + " | " + matchDto.getFinaliste().getPrenom()
                + " | " + matchDto.getFinaliste().getSexe());
        System.out.println("Il s'agit d'un match du tournoi : " + matchDto.getEpreuve().getTournoi().getNom() + " en " + matchDto.getEpreuve().getAnnee());

        System.out.println("Les résultats des sets sont les suivants : ");
        System.out.println(matchDto.getScore().getSet1());
        System.out.println(matchDto.getScore().getSet2());
        if (matchDto.getScore().getSet3() != null) {
            System.out.println(matchDto.getScore().getSet3());
        }
        if (matchDto.getScore().getSet4() != null) {
            System.out.println(matchDto.getScore().getSet4());
        }
        if (matchDto.getScore().getSet5() != null) {
            System.out.println(matchDto.getScore().getSet5());
        }
    }
}
