package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;


    public MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void enregistrerNouveauMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

    public MatchDto getMatch(Long id) {

        Session session = null;
        Transaction tx = null;
        MatchDto matchDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Match match = matchRepository.getMatchById(id);

            JoueurDto joueurDtoVainqueur = new JoueurDto();
            joueurDtoVainqueur.setId(match.getVainqueur().getId());
            joueurDtoVainqueur.setNom(match.getVainqueur().getNom());
            joueurDtoVainqueur.setPrenom(match.getVainqueur().getPrenom());
            joueurDtoVainqueur.setSexe(match.getVainqueur().getSexe());

            JoueurDto joueurDtoFinaliste = new JoueurDto();
            joueurDtoFinaliste.setId(match.getFinaliste().getId());
            joueurDtoFinaliste.setNom(match.getFinaliste().getNom());
            joueurDtoFinaliste.setPrenom(match.getFinaliste().getPrenom());
            joueurDtoFinaliste.setSexe(match.getFinaliste().getSexe());

            EpreuveFullDto epreuveFullDtodto = new EpreuveFullDto();
            epreuveFullDtodto.setId(match.getEpreuve().getId());
            epreuveFullDtodto.setAnnee(match.getEpreuve().getAnnee());
            epreuveFullDtodto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());

            epreuveFullDtodto.setTournoi(tournoiDto);

            matchDto = new MatchDto();
            matchDto.setId(match.getId());
            matchDto.setVainqueur(joueurDtoVainqueur);
            matchDto.setFinaliste(joueurDtoFinaliste);
            matchDto.setEpreuve(epreuveFullDtodto);

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
        return matchDto;

    }
}
