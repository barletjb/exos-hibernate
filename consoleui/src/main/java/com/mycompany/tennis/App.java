package com.mycompany.tennis;

import com.mycompany.tennis.controller.*;

public class App
{
    public static void main( String[] args )
    {

//        JoueurController controller = new JoueurController();
//        controller.afficheDetailsJoueur();
//        controller.creerJoueur();

//        TournoiController controller2 =  new TournoiController();
//        controller2.creerTournoi();
//
//        controller2.afficheDetailsTournoi();

//        JoueurController controller3 = new JoueurController();
//        controller3.renommeJoueur();

//        JoueurController controller = new JoueurController();
//        controller.supprimerJoueur();

//        TournoiController controller = new TournoiController();
//        controller.supprimerTournoi();

//        EpreuveController controller = new EpreuveController();
//        controller.afficheDetailsEpreuve();

//        MatchController controller = new MatchController();
//        controller.afficheDetailsMatch();

//        ScoreController controller = new ScoreController();
//        controller.afficheDetailsScore();
//        controller.tapisVert();

//        MatchController controller = new MatchController();
//        controller.ajouterMatch();
//        controller.supprimerMAtch();

//        ScoreController controller1 = new ScoreController();
//        controller1.supprimerScore();

        JoueurController controller = new JoueurController();
        controller.afficheListJoueurs();

        EpreuveController controller2 = new EpreuveController();
        controller2.afficheListEpreuve();

    }
}
