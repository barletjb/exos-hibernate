package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import org.hibernate.Session;


public class EpreuveRepositoryImpl {

    public Epreuve getEpreuveById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Epreuve epreuve = session.get(Epreuve.class,id);
        System.out.println("Epreuve Lu");

        return epreuve;

    }


}
