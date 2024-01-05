package com.myProject.servlet;

import com.myProject.dao.CommentDao;
import com.myProject.dao.FilmDao;
import com.myProject.entity.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/20 09:01
 */

@WebServlet(name = "UpdateFilm", value = "/updateFilm")
@MultipartConfig
public class UpdateFilm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        int fid = Integer.parseInt(request.getParameter("fid"));

        if (action.equals("updateFilm")) {
            updateFilm(request, response, fid);
        } else if (action.equals("deleteComments")) {
            deleteComment(request, response, fid);
        }

    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response, int fid) throws IOException {
        String[] selectedComments = request.getParameterValues("selectedComments");
        CommentDao commentDao = new CommentDao();
        for (String cid : selectedComments) {
            commentDao.deleteCommentByCid(Integer.parseInt(cid));
        }

        response.sendRedirect("manageFilmDetail?fid=" + fid);
    }

    private void updateFilm(HttpServletRequest request, HttpServletResponse response, int fid) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String director = request.getParameter("director");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String finfo = request.getParameter("finfo");

        // 处理上传的文件（图片）
        Part filePart = request.getPart("file");
        String pictureUrl = processUploadedFile(filePart, fid);

        // 更新电影信息
        FilmDao filmDao = new FilmDao();
        Film newFilm = filmDao.getFilmByFid(fid);
        newFilm.setFname(fname);
        newFilm.setDirector(director);
        newFilm.setReleaseYear(releaseYear);
        newFilm.setFinfo(finfo);
        newFilm.setPicture(pictureUrl);
        filmDao.updateFilm(newFilm);

        // 重定向到电影详情页面
        response.sendRedirect("manageFilmDetail?fid=" + fid);
    }

    private String processUploadedFile(Part filePart, int fid) throws IOException {
        // 处理上传的文件，将其保存到指定目录，并返回文件相对路径
        String uploadDir = getServletContext().getRealPath("/filmImg");

        // 检查目录是否存在，如果不存在就创建
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = "film-" + fid + ".jpg"; // 设置文件名
        String filePath = "filmImg/" + fileName; // 上传文件保存的相对路径

        File file = new File(uploadDir, fileName);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return filePath;
    }

}