<%@ page import="com.myProject.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/headerForAll.css">
</head>
<body>
<header>
    <div class="navbar">
        <div class="title">
            <a href="index.jsp" style="color: white; text-decoration: none;">
                影视评论网
            </a>
        </div>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
        %>
        <a href="login.jsp">
            <button>登录</button>
        </a>
        <a href="register.jsp">
            <button>注册</button>
        </a>
        <%
        } else {
        %>
        <span>
            <a href="userInfo.jsp"><%= user.getUname() %></a>
            <a href="logout">
                <button>退出登录</button>
            </a>
        </span>
        <%
            }
        %>
    </div>
</header>
</body>
</html>