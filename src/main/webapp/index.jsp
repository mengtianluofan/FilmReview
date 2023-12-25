<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.myProject.entity.User" %>
<%@ page import="com.myProject.entity.Film" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    // 检查是否已经存在电影数据
    List<Film> films = (List<Film>) request.getAttribute("films");
    if (films == null || films.isEmpty()) {
        response.sendRedirect("films");
        return;
    }
%>
<%
    session = request.getSession();
    User user = (User) session.getAttribute("user");
%>

<jsp:include page="headerForAll.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>影视评论网</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        #movies {
            margin-top: 20px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .film {
            width: 80%; /* Adjust width as needed */
            margin-bottom: 20px;
            border: 1px solid #ddd;
            box-sizing: border-box;
            overflow: hidden;
            display: flex;
        }

        .film img {
            width: 80%; /* Image takes 60% width of its container */
            height: auto;
            display: block;
        }

        .film-info {
            padding: 20px;
            width: 40%;
        }

        .film-info h2 {
            margin: 0;
            font-size: 24px;
            color: #343a40;
        }

        .film-info p {
            margin: 10px 0 0;
            font-size: 16px;
            color: #666;
        }

        #pagination {
            margin-top: 20px;
            text-align: center;
        }

        #pagination a, #pagination span {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 5px;
            border: 1px solid #ddd;
            text-decoration: none;
            color: #343a40;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        #pagination a:hover {
            background-color: #ddd;
        }

        #pagination span {
            background-color: #ddd;
        }
    </style>
</head>
<body>
<div id="movies">
    <c:forEach var="film" items="${films}">
        <div class="film">
            <a href="filmDetail?fid=${film.fid}">
                <img src="${film.picture}" alt="${film.fname}">
            </a>
            <div class="film-info">
                <h2>${film.fname}</h2>
                <p>导演：${film.director}</p>
                <p>上映日期：${film.releaseYear}</p>
            </div>
        </div>
    </c:forEach>
</div>

<!-- 分页控件 -->
<div id="pagination">
    <c:if test="${totalPages > 0}">
        <c:choose>
            <c:when test="${currentPage > 1}">
                <a href="films?page=${currentPage - 1}">上一页</a>
            </c:when>
            <c:otherwise>
                <span>上一页</span>
            </c:otherwise>
        </c:choose>

        <c:forEach var="pageNum" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${pageNum == currentPage}">
                    <span>${pageNum}</span>
                </c:when>
                <c:otherwise>
                    <a href="films?page=${pageNum}">${pageNum}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentPage < totalPages}">
                <a href="films?page=${currentPage + 1}">下一页</a>
            </c:when>
            <c:otherwise>
                <span>下一页</span>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
</body>
</html>