package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService(){
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void createScore(Score score){

        Session session= null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreRepository.create(score);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public ScoreFullDto getScore (Long id){

        Session session= null;
        Transaction tx = null;
        ScoreFullDto scoreFullDto = null;


        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Score score = scoreRepository.getById(id);

            JoueurDto joueurDtoVainqueur = new JoueurDto();
            joueurDtoVainqueur.setId(score.getMatch().getVainqueur().getId());
            joueurDtoVainqueur.setNom(score.getMatch().getVainqueur().getNom());
            joueurDtoVainqueur.setPrenom(score.getMatch().getVainqueur().getPrenom());
            joueurDtoVainqueur.setSexe(score.getMatch().getVainqueur().getSexe());

            JoueurDto joueurDtoFinaliste = new JoueurDto();
            joueurDtoFinaliste.setId(score.getMatch().getFinaliste().getId());
            joueurDtoFinaliste.setNom(score.getMatch().getFinaliste().getNom());
            joueurDtoFinaliste.setPrenom(score.getMatch().getFinaliste().getPrenom());
            joueurDtoFinaliste.setSexe(score.getMatch().getFinaliste().getSexe());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());

            EpreuveFullDto epreuveFullDtodto = new EpreuveFullDto();
            epreuveFullDtodto.setId(score.getMatch().getEpreuve().getId());
            epreuveFullDtodto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveFullDtodto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            epreuveFullDtodto.setTournoi(tournoiDto);

            MatchDto matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());
            matchDto.setVainqueur(joueurDtoVainqueur);
            matchDto.setFinaliste(joueurDtoFinaliste);
            matchDto.setEpreuve(epreuveFullDtodto);

            scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());
            scoreFullDto.setMatch(matchDto);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return scoreFullDto;
    }
}
