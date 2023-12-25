package com.myProject.servlet;

import com.myProject.dao.FilmDao;
import com.myProject.entity.Film;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 23:33
 */

@WebServlet(name = "ManageFilmsServlet", value = "/manageFilms")
public class ManageFilmsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        FilmDao filmDao = new FilmDao();
        List<Film> films = null;
        try {
            films = filmDao.getAllFilms();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("films", films);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}