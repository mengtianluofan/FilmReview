package com.myProject.servlet;

import com.myProject.dao.UserDao;
import com.myProject.entity.User;
import com.myProject.entity.UserInfo;
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
 * @description: 登录C
 * TODO
 * @date 2023/12/17 10:44
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String id = request.getParameter("uid");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        if (id == null || id.equals("") || password == null || password.equals("")) {
            String message = "用户名或密码不能为空！！！   请重试！！！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (!id.matches("[0-9]+")) {
            String message = "用户名或密码错误！！！   请重试！！！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        int uid = Integer.parseInt(id);

        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();

        try {
            User user = userDao.checkLogin(uid, password, role);
            String destPage = "login.jsp";

            if (user != null) {
                if (role.equals("admin")) {
                    destPage = "admin.jsp";
                } else if (role.equals("user")) {
                    destPage = "index.jsp";
                    UserInfo userInfo = userDao.getUserInfoByUid(uid);
                    session.setAttribute("userInfo", userInfo);
                }
            } else {
                String message = "用户名或密码错误！！！   请重试！！！";
                request.setAttribute("message", message);
            }

            session.setAttribute("user", user);
            request.getRequestDispatcher(destPage).forward(request, response);

        } catch (SQLException | ServletException ex) {
            throw new ServletException(ex);
        }
    }
}
