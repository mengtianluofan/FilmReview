<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>影视评论网-用户注册</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url("img/background.jpg");
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            height: 100vh;
            padding-right: 25%;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 50px;
        }

        form {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            color: #fff;
            background-color: #007BFF;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<main>
    <h1>用户注册</h1>
    <form action="register" method="post">
        <div>
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="注册">
        </div>
    </form>
</main>
</body>
</html>