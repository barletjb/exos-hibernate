package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService(){
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getEpreuveDetaillee(Long id){

        Session session = null;
        Transaction tx = null;
        EpreuveFullDto epreuveFullDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Epreuve epreuve = epreuveRepository.getEpreuveById(id);

            epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(epreuve.getId());
            epreuveFullDto.setAnnee(epreuve.getAnnee());
            epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());

            epreuveFullDto.setTournoi(tournoiDto);

            epreuveFullDto.setParticipants(new HashSet<>());

            for (Joueur joueur : epreuve.getParticipants()){
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                epreuveFullDto.getParticipants().add(joueurDto);
            }

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

        return epreuveFullDto;

    }

    public EpreuveLightDto getEpreuveSansTournoi(Long id){

        Session session = null;
        Transaction tx = null;
        EpreuveLightDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Epreuve epreuve = epreuveRepository.getEpreuveById(id);

            dto = new EpreuveLightDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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

        return dto;

    }

    public List<EpreuveFullDto> getListEpreuves(String codeTournoi){
//        Session session = null;
//        Transaction tx = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EpreuveFullDto> dtos = new ArrayList<>();

        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Epreuve> epreuves = epreuveRepository.list(codeTournoi);

            for (Epreuve epreuve : epreuves){
                final EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
                epreuveFullDto.setId(epreuve.getId());
                epreuveFullDto.setAnnee(epreuve.getAnnee());
                epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());

                TournoiDto tournoiDto = new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                epreuveFullDto.setTournoi(tournoiDto);

                dtos.add(epreuveFullDto);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return dtos;
    }

}
