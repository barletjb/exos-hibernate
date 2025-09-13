package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController(){
        this.joueurService= new JoueurService();
    }

    public void afficheDetailsJoueur(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les infos ? ");
        long identifiant = scanner.nextLong();

        Joueur joueur = joueurService.getJoueur(identifiant);
        System.out.println("Le joueur sélectionné s'appelle " + joueur.getPrenom() + " " + joueur.getNom());

    }

    public void creerJoueur(){

        Scanner scanner = new Scanner(System.in);

        Joueur joueur = new Joueur();

        System.out.println("Entrez le nom du joueur");
        String nom = scanner.nextLine();
        joueur.setNom(nom);

        System.out.println("Entrez le prénom du joueur");
        String prenom = scanner.nextLine();
        joueur.setPrenom(prenom);

        System.out.println("Entrez le sexe du joueur(se)");
        Character sexe = scanner.nextLine().charAt(0);
        joueur.setSexe(sexe);

        joueurService.createJoueur(joueur);
        System.out.println("Le joueur a été créé , son identifiant est " + joueur.getId());

    }

    public void renommeJoueur(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez renommer ? ");
        long identifiant = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Entrez le nouveau nom du joueur");
        String nom = scanner.nextLine();
        joueurService.renomme(identifiant,nom);

    }

    public void changerSexe(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez modifier ? ");
        long identifiant = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Entrez le nouveau sexe du joueur");
        Character sexe = scanner.nextLine().charAt(0);
        joueurService.changerSexe(identifiant,sexe);

    }

    public void supprimerJoueur(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez supprimer ? ");
        long identifiant = scanner.nextLong();

        joueurService.deleteJoueur(identifiant);

    }

    public void afficheListJoueurs(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le sexe pour la liste de joueur(se) (H ou F) ? ");
        char sexe = scanner.nextLine().charAt(0);

        for (JoueurDto dto : joueurService.getListJoueurs(sexe)){
            System.out.println(dto.getNom() + " | " + dto.getPrenom());
        }
    }
}
