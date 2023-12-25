package com.myProject.servlet;

import com.myProject.dao.CommentDao;
import com.myProject.dao.FilmDao;
import com.myProject.entity.Comment;
import com.myProject.entity.Film;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 23:33
 */

@WebServlet(name = "ManageFilmDetailServlet", value = "/manageFilmDetail")
public class ManageFilmDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int fid = Integer.parseInt(request.getParameter("fid"));

        FilmDao filmDao = new FilmDao();
        Film film = filmDao.getFilmByFid(fid);

        CommentDao commentDao = new CommentDao();
        List<Comment> comments = commentDao.getCommentsByFid(fid);

        request.setAttribute("film", film);
        request.setAttribute("comments", comments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("adminFilmDetail.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}