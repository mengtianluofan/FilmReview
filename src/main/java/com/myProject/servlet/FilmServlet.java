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

@WebServlet(name = "FilmServlet", urlPatterns = "/films")
public class FilmServlet extends HttpServlet {
    private static final int FILMS_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前页码，默认为第一页
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        FilmDao filmDao = new FilmDao();
        try {
            // 获取总电影数
            int totalFilms = filmDao.getTotalFilms();
            // 计算总页数
            int totalPages = (int) Math.ceil((double) totalFilms / FILMS_PER_PAGE);
            // 获取当前页的电影列表
            List<Film> films = filmDao.getFilmsByPage(page, FILMS_PER_PAGE);

            // 将数据设置到request中
            request.setAttribute("films", films);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}