<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.myProject.entity.Film" %>
<%@ page import="com.myProject.entity.User" %>
<%@ page import="com.myProject.entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="headerForAll.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>影视评论网 - ${film.fname}</title>
    <link rel="stylesheet" type="text/css" href="css/filmDetail.css">
    <script>
        function toggleReplyForm(commentId) {
            var replyForm = document.getElementById('replyForm-' + commentId);
            replyForm.style.display = (replyForm.style.display === 'none' || replyForm.style.display === '') ? 'block' : 'none';
        }

        function likeFilm(fid) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var result = xhr.responseText;
                    var successMessage = document.getElementById('successMessage');
                    if (result === "-1") {
                        successMessage = document.getElementById('notLoginMessage');
                    } else if (result === "-2") {
                        successMessage = document.getElementById('hasLikeMessage');
                    } else {
                        // 更新点赞数
                        document.getElementById('likeCount').innerText = result;
                    }
                    // 显示点赞成功消息框
                    successMessage.style.opacity = 1; // 初始透明度
                    successMessage.style.display = 'block';

                    // 在两秒后触发淡出效果
                    setTimeout(function () {
                        fadeOut(successMessage);
                    }, 2000);
                }
            };
            xhr.open('POST', 'FilmLike?fid=' + fid, true);
            xhr.send();
        }

        function fadeOut(element) {
            var opacity = 1;
            var interval = setInterval(function () {
                if (opacity <= 0) {
                    clearInterval(interval);
                    element.style.display = 'none';
                } else {
                    element.style.opacity = opacity;
                    opacity -= 0.1;
                }
            }, 100);
        }
    </script>
</head>
<body>
<div class="film-detail-container">
    <div class="film-header">
        <img src="${film.picture}" alt="${film.fname}" class="film-image">
        <div class="film-info">
            <h2>${film.fname}</h2>
            <p>导演：${film.director}</p>
            <p>上映日期：${film.releaseYear}</p>
            <p>电影概述：${film.finfo}</p>
            <p>影评数：${film.commentSum}</p>
            <p>点赞数： <span id="likeCount">${film.likeSum}</span></p>
            <button onclick="likeFilm(${film.fid})">点赞</button>
            <div id="successMessage" class="notification">点赞成功！</div>
            <div id="notLoginMessage" class="notification">用户未登录！</div>
            <div id="hasLikeMessage" class="notification">您已点赞！</div>
        </div>
    </div>

    <h3>评论</h3>
    <c:forEach var="comment" items="${comments}">
        <c:choose>
            <c:when test="${comment.parentId eq 0}">
                <div class="comment">
                    <p class="comment-info">${comment.uname} - ${comment.date}</p>
                    <p class="comment-content">${comment.content}</p>

                    <c:if test="${user ne null}">
                        <button onclick="toggleReplyForm(${comment.cid})">回复评论</button>
                        <div id="replyForm-${comment.cid}" class="reply-form" style="display: none;">
                            <form action="addComment" method="post">
                                <input type="hidden" name="fid" value="${film.fid}">
                                <input type="hidden" name="parentId" value="${comment.cid}">
                                <textarea name="content" rows="4" cols="50" placeholder="回复评论"></textarea>
                                <button type="submit">发表回复</button>
                            </form>
                        </div>
                    </c:if>

                    <div class="replies">
                        <c:forEach var="reply" items="${comment.replies}">
                            <div class="reply">
                                <p class="reply-info">${reply.uname} - ${reply.date}</p>
                                <p class="reply-content">${reply.content}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </c:forEach>

    <c:if test="${user ne null}">
        <div class="comment-form">
            <form action="addComment" method="post">
                <input type="hidden" name="fid" value="${film.fid}">
                <input type="hidden" name="parentId" value=0>
                <textarea name="content" rows="4" cols="50" placeholder="发表评论"></textarea>
                <button type="submit">发表评论</button>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>