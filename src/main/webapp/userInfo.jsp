<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myProject.entity.UserInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="headerForAll.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户信息</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .main-container {
            background-color: #fff;
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h2 {
            color: #333;
            text-align: center;
        }

        p {
            margin: 10px 0;
            color: #555;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            color: #007bff;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="main-container">
    <h2>用户信息</h2>

    <c:if test="${not empty userInfo}">
        <p>UID: ${userInfo.uid}</p>
        <p>用户名: ${userInfo.uname}</p>
        <p>已评论: ${userInfo.commentNum} 条</p>
        <p>点赞影视: ${userInfo.likeNum} 个</p>

        <c:if test="${not empty userInfo.likeFilms}">
            <p>点赞的影视:</p>
            <ul>
                <c:forEach var="filmName" items="${fn:split(userInfo.likeFilms, ',')}">
                    <li>${filmName}</li>
                </c:forEach>
            </ul>
        </c:if>
    </c:if>
</div>
</body>
</html>
