package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class EpreuveRepositoryImpl {

    public Epreuve getEpreuveById(Long id) {

//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class,id);
        System.out.println("Epreuve Lu");

        return epreuve;

    }

    public List<Epreuve> list(String codeTournoi){

//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        query.setParameter(0,codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuves lues");

        return epreuves;
    }


}
