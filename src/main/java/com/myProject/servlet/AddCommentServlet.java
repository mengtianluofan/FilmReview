package com.myProject.servlet;

import com.myProject.dao.CommentDao;
import com.myProject.entity.Comment;
import com.myProject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 20:33
 */

@WebServlet(name = "AddCommentServlet", value = "/addComment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int fid = Integer.parseInt(request.getParameter("fid"));
        String content = request.getParameter("content");
        int parentId = Integer.parseInt(request.getParameter("parentId"));

        Comment comment = new Comment(0, fid, user.getUid(), content, parentId, new Timestamp(new Date().getTime()));

        CommentDao commentDao = new CommentDao();
        try {
            commentDao.addComment(comment);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("filmDetail?fid=" + fid);
    }
}