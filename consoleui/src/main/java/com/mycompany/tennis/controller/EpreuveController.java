package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController() {
        this.epreuveService = new EpreuveService();
    }

    public void afficheDetailsEpreuve() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant de l'epreuve à afficher ? ");
        long identifiant = scanner.nextLong();

        EpreuveFullDto epreuve = epreuveService.getEpreuveDetaillee(identifiant);
        System.out.println("Les infos de l'épreuve sont les suivantes " + epreuve.getTournoi().getNom() + " | " + epreuve.getAnnee());
        System.out.println("La liste des participants est la suivante : ");
        for (JoueurDto joueurDto : epreuve.getParticipants()){
            System.out.println(joueurDto.getNom() + " " + joueurDto.getPrenom());
        }

    }

}
