<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myProject.entity.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myProject.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null || !user.getRole().equals("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%
    List<Film> films = (List<Film>) request.getAttribute("films");
    if (films == null || films.isEmpty()) {
        response.sendRedirect("manageFilms");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="headerForAll.jsp"/>
<head>
    <meta charset="UTF-8">
    <title>管理员界面</title>
    <link rel="stylesheet" href="css/adminStyle.css">
<body>
<h1>管理员界面</h1>

<div class="container">
    <div class="form-container">
        <form action="adminSearch" method="get">
            <input type="text" name="search" placeholder="搜索电影...">
            <button type="submit">搜索</button>
        </form>

        <a href="addFilm.jsp">
            <button>添加影视</button>
        </a>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>FName</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="film" items="${films}">
                <tr>
                    <td>${film.fname}</td>
                    <td>
                        <a href="manageFilmDetail?fid=${film.fid}">管理</a>
                        <a href="deleteFilm?fid=${film.fid}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
