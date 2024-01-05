<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myProject.entity.Film" %>
<%@ page import="com.myProject.entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="headerForAll.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>影视详情</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }

        form {
            padding: 20px;
            background: #fff;
            box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
            margin: 20px 0;
        }

        label {
            display: block;
            margin: 5px 0;
        }

        input[type="text"], input[type="number"], textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            margin-bottom: 10px;
        }

        button {
            display: block;
            width: 100%;
            height: 40px;
            border: 0;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            cursor: pointer;
            font-size: 18px;
        }

        button:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>影视详情</h1>

    <form action="updateFilm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="fid" value="${film.fid}">
        <h2>影视名：<input type="text" name="fname" value="${film.fname}" required></h2>
        <p>导演：<input type="text" name="director" value="${film.director}"></p>
        <p>上映日期：<input type="text" name="releaseYear" value="${film.releaseYear}"></p>
        <p>影视概述：<textarea name="finfo" rows="4" cols="50">${film.finfo}</textarea></p>
        <!-- 添加上传图片的输入 -->
        <p>上传图片：<input type="file" name="file"></p>
        <img src="${film.picture}" alt="${film.fname}" width="100" height="150"/>
        <p>
            <button type="submit" name="action" value="updateFilm">保存修改</button>
        </p>
    </form>

    <h3>评论</h3>
    <form action="updateFilm" method="post">
        <input type="hidden" name="action" value="deleteComments">
        <input type="hidden" name="fid" value="${film.fid}">
        <table>
            <thead>
            <tr>
                <th>选择</th>
                <th>评论内容</th>
                <th>日期</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td><input type="checkbox" name="selectedComments" value="${comment.cid}"></td>
                    <td>${comment.content}</td>
                    <td>${comment.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="submit">删除选中评论</button>
    </form>
</div>

</body>
</html>
