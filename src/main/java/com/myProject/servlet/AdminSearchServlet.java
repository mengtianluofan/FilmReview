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
 * @date 2023/12/20 08:42
 */
@WebServlet(name = "AdminSearchServlet", value = "/adminSearch")
public class AdminSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String search = request.getParameter("search");
        FilmDao filmDao = new FilmDao();
        List<Film> films = null;
        try {
            films = filmDao.searchFilms(search);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("films", films);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}
