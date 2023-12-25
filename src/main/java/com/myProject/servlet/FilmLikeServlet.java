package com.myProject.servlet;

import com.myProject.dao.FilmDao;
import com.myProject.dao.FilmLikeDao;
import com.myProject.entity.Film;
import com.myProject.entity.User;
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
 * @date 2023/12/23 09:49
 */

@WebServlet(name = "FilmLikeServlet", value = "/FilmLike")
public class FilmLikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("fid"));
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.getWriter().write("-1"); // 用户未登录
        } else {
            int uid = user.getUid();
            FilmLikeDao filmLikeDao = new FilmLikeDao();
            if (filmLikeDao.checkFilmLike(uid, fid)) {
                response.getWriter().write("-2"); // 已点赞
            } else {
                filmLikeDao.addFilmLike(uid, fid);
                Film film = null;
                FilmDao filmDao = new FilmDao();
                film = filmDao.getFilmByFid(fid);
                request.setAttribute("film", film);
                response.getWriter().write(String.valueOf(film.getLikeSum()));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
