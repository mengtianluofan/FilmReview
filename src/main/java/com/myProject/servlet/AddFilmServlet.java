package com.myProject.servlet;

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
 * @date 2023/12/20 11:36
 */

@WebServlet(name = "AddFilmServlet", value = "/addFilm")
@MultipartConfig
public class AddFilmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fname = request.getParameter("fname");
        String director = request.getParameter("director");
        String releaseYearStr = request.getParameter("releaseYear");
        int releaseYear = 0;
        if (releaseYearStr != null) {
            releaseYear = Integer.parseInt(releaseYearStr);
        }
        String finfo = request.getParameter("finfo");

        Film newFilm = new Film();
        newFilm.setFname(fname);
        newFilm.setDirector(director);
        newFilm.setReleaseYear(releaseYear);
        newFilm.setFinfo(finfo);

        FilmDao filmDao = new FilmDao();
        newFilm = filmDao.addFilm(newFilm);

        Part filePart = request.getPart("file");
        String pictureUrl = processUploadedFile(filePart, newFilm.getFid());
        newFilm.setPicture(pictureUrl);
        filmDao.updateFilm(newFilm);

        response.sendRedirect("admin.jsp");
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
