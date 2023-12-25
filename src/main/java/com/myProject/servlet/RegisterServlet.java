package com.myProject.servlet;

import com.myProject.dao.UserDao;
import com.myProject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: 注册C
 * TODO
 * @date 2023/12/17 10:51
 */

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUname(username);
        user.setPassword(password);
        user.setRole("user");

        int uid = 0;
        try {
            uid = userDao.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.setUid(uid);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("index.jsp");
    }
}