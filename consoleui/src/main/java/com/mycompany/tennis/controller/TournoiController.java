package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;

    public TournoiController(){
        this.tournoiService = new TournoiService();
    }

    public void afficheDetailsTournoi(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du tournoi à afficher ? ");
        long identifiant = scanner.nextLong();

        TournoiDto tournoi = tournoiService.getTournoi(identifiant);
        System.out.println("Les infos du tournoi sont les suivantes : " + tournoi.getNom() + " | " + tournoi.getCode());
    }

    public void creerTournoi(){
        Scanner scanner = new Scanner(System.in);
        TournoiDto tournoiDto = new TournoiDto();

        System.out.println("Veuillez saisir le nom de votre tournoi ? ");
        String nom = scanner.nextLine();
        tournoiDto.setNom(nom);

        System.out.println("Veuillez saisir le code de votre tournoi ? ");
        String code = scanner.nextLine();
        tournoiDto.setCode(code);

        tournoiService.createTournoi(tournoiDto);

    }

    public void supprimerTournoi(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez saisir l'identifiant du tournoi à supprimer ? ");
        Long identifiant = scanner.nextLong();

       tournoiService.deleteTournoi(identifiant);

    }
}
