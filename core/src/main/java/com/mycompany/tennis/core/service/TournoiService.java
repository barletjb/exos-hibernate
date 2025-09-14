package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService(){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi(TournoiDto tournoiDto){

        EntityManager em = null;
//        Session session = null;
//        Transaction tx = null;
        EntityTransaction tx = null;


        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();

            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());
            tournoiRepository.create(tournoi);
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

    }

    public TournoiDto getTournoi(Long id){

//        Session session = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        TournoiDto tournoiDto = null;

        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Tournoi tournoi = tournoiRepository.getTournoiById(id);
            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setNom(tournoi.getNom());
            tournoiDto.setCode(tournoi.getCode());

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

        return tournoiDto;

    }

    public void deleteTournoi(Long id){

//        Session session = null;
//        Transaction tx = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();

            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();

            tournoiRepository.delete(id);
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

    }
}
