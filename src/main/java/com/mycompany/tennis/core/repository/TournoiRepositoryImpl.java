package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(tournoi);
        System.out.println("Tournoi créé");

    }

    public void delete(Long id) {

        Tournoi tournoi = getTournoiById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);

        System.out.println("Tournoi supprimé");

    }

    public Tournoi getTournoiById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Tournoi tournoi = session.get(Tournoi.class,id);
        System.out.println("Tournoi Lu");

        return  tournoi;

    }

    public List<Tournoi> list() {

        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,CODE FROM TOURNOI");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));

                tournois.add(tournoi);
            }

            System.out.println("Tournois affichés ");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  tournois;
    }
}
