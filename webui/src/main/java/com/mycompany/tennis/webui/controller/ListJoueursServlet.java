package com.mycompany.tennis.webui.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.service.JoueurService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listes")
public class ListJoueursServlet extends HttpServlet {

    private JoueurService joueurService;

    public ListJoueursServlet() {
        this.joueurService = new JoueurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<JoueurDto> listeHommes = joueurService.getListJoueurs('H');
        List<JoueurDto> listeFemmes = joueurService.getListJoueurs('F');
        System.out.println("Hommes : " + listeHommes.size());
        System.out.println("Femmes : " + listeFemmes.size());

        req.setAttribute("listeHommes",listeHommes);
        req.setAttribute("listefemmes",listeFemmes);

        RequestDispatcher disp = req.getRequestDispatcher("listes.jsp");
        disp.forward(req,resp);

    }
}
