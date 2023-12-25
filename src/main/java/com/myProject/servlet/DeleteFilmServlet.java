package com.myProject.servlet;

import com.myProject.dao.FilmDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/20 15:12
 */

@WebServlet(name = "DeleteFilmServlet", value = "/deleteFilm")
public class DeleteFilmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int fid = Integer.parseInt(request.getParameter("fid"));

        FilmDao filmDao = new FilmDao();
        filmDao.deleteFilmByFid(fid);

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}