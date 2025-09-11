package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;
import java.sql.*;

public class MatchRepositoryImpl {

    public void create(Match match) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println("Match créé");
    }

    public Match getMatchById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class, id);

        System.out.println("Match lu");

        return match;


    }
}



