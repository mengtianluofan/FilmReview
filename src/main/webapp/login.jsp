<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>影视评论网-登录界面</title>
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

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        .field {
            margin-bottom: 10px;
        }

        .field label {
            display: block;
            margin-bottom: 5px;
        }

        .field input, .field select {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        .field input[type="submit"], button {
            width: 48%;
            background-color: #007BFF;
            color: #fff;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            padding: 10px;
            margin-top: 10px;
        }

        .field input[type="submit"]:hover, button:hover {
            background-color: #0056b3;
        }

        button {
            background-color: #28a745;
            float: right;
        }

        button:hover {
            background-color: #1e7e34;
        }
    </style>
    <script>
        window.onload = function () {
            var message = '<%= request.getAttribute("message") %>';
            if (message && message !== "null" && message.trim().length > 0) {
                alert(message);
                <% session.removeAttribute("message"); %>
            }
        }
    </script>
</head>
<body>
<div>
    <h1>登录界面</h1>
    <form method="post" action="login">
        <input type="hidden" name="action" value="login">
        <div class="field">
            <label for="uid">账号：</label>
            <input type="text" id="uid" name="uid">
        </div>
        <div class="field">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password">
        </div>
        <div class="field">
            <label for="role">身份:</label>
            <select id="role" name="role">
                <option value="user">普通用户</option>
                <option value="admin">管理员</option>
            </select>
        </div>
        <div class="field">
            <input type="submit" value="登录">
        </div>
    </form>
</div>
</body>
</html>