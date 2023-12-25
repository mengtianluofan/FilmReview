package com.myProject.servlet;

import com.myProject.dao.CommentDao;
import com.myProject.dao.FilmDao;
import com.myProject.entity.Comment;
import com.myProject.entity.Film;
import com.myProject.entity.FilmComment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FilmDetailServlet", urlPatterns = "/filmDetail")
public class FilmDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int filmId = Integer.parseInt(request.getParameter("fid"));
        FilmDao filmDao = new FilmDao();
        CommentDao commentDao = new CommentDao();
        Film film = null;
        film = filmDao.getFilmByFid(filmId);

        List<FilmComment> Comments = commentDao.getFilmComments(filmId);

        request.setAttribute("film", film);
        request.setAttribute("comments", Comments);

        request.getRequestDispatcher("/filmDetail.jsp").forward(request, response);
    }
}
