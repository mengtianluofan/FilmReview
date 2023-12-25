<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myProject.entity.User" %>
<%@ page import="com.myProject.entity.Film" %>

<jsp:include page="headerForAll.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加影视</title>
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
    </style>
</head>
<body>
<div class="container">
    <h2>添加影视</h2>

    <form action="addFilm" method="post" enctype="multipart/form-data">
        <label for="fname">影视名称:</label>
        <input type="text" id="fname" name="fname" required><br>

        <label for="director">导演:</label>
        <input type="text" id="director" name="director"><br>

        <label for="releaseYear">上映年份:</label>
        <input type="number" id="releaseYear" name="releaseYear"><br>

        <label for="finfo">影视概述:</label>
        <textarea id="finfo" name="finfo" rows="4" cols="50"></textarea><br>

        <label for="file">影视图片:</label>
        <input type="file" id="file" name="file" accept="image/*"><br>

        <button type="submit">添加影视</button>
    </form>
</div>
</body>
</html>