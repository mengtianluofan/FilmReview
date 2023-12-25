package com.myProject.dao;

import com.myProject.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 18:32
 */
public class FilmDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getTotalFilms() throws SQLException {
        int totalFilms = 0;
        try {
            conn = DaoUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM film";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalFilms = rs.getInt(1);
            }
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
        return totalFilms;
    }

    public List<Film> getFilmsByPage(int page, int filmsPerPage) throws SQLException {
        List<Film> films = new ArrayList<>();
        try {
            conn = DaoUtil.getConnection();
            String sql = "SELECT * FROM film LIMIT ?, ?";
            int startIdx = (page - 1) * filmsPerPage;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, startIdx);
            ps.setInt(2, filmsPerPage);
            rs = ps.executeQuery();

            while (rs.next()) {
                int fid = rs.getInt("fid");
                String fname = rs.getString("fname");
                String director = rs.getString("director");
                int releaseYear = rs.getInt("releaseYear");
                String finfo = rs.getString("finfo");
                String picture = rs.getString("picture");
                int commentSum = rs.getInt("comment_sum");
                int likeSum = rs.getInt("like_sum");
                Film film = new Film(fid, fname, director, releaseYear, finfo, picture, commentSum, likeSum);
                films.add(film);
            }
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
        return films;
    }

    public List<Film> getAllFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        try {
            conn = DaoUtil.getConnection();
            String sql = "select * from film";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int fid = rs.getInt("fid");
                String fname = rs.getString("fname");
                String director = rs.getString("director");
                int releaseYear = rs.getInt("releaseYear");
                String finfo = rs.getString("finfo");
                String picture = rs.getString("picture");
                int commentSum = rs.getInt("comment_sum");
                int likeSum = rs.getInt("like_sum");
                Film film = new Film(fid, fname, director, releaseYear, finfo, picture, commentSum, likeSum);
                films.add(film);
            }
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
        return films;
    }

    public Film getFilmByFid(int fid) {
        try {
            conn = DaoUtil.getConnection();
            String sql = "select * from film where fid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fid);
            rs = ps.executeQuery();
            Film film = null;

            while (rs.next()) {
                String fname = rs.getString("fname");
                String finfo = rs.getString("finfo");
                int commentSum = rs.getInt("comment_sum");
                String director = rs.getString("director");
                int releaseYear = rs.getInt("releaseYear");
                String picture = rs.getString("picture");
                int likeSum = rs.getInt("like_sum");
                film = new Film(fid, fname, director, releaseYear, finfo, picture, commentSum, likeSum);
            }
            DaoUtil.close(rs, ps, conn);
            return film;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Film getFilmByFname(String fname) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "select * from film where fname = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, fname);
        rs = ps.executeQuery();
        Film film = null;

        while (rs.next()) {
            int fid = rs.getInt("fid");
            String finfo = rs.getString("finfo");
            int commentSum = rs.getInt("comment_sum");
            String director = rs.getString("director");
            int releaseYear = rs.getInt("releaseYear");
            String picture = rs.getString("picture");
            int likeSum = rs.getInt("like_sum");
            film = new Film(fid, fname, director, releaseYear, finfo, picture, commentSum, likeSum);
        }
        DaoUtil.close(rs, ps, conn);
        return film;
    }

    public Film addFilm(Film film) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DaoUtil.getConnection();
            String sql = "{CALL add_film(?, ?, ?, ?, ?, ?)}";
            cs = conn.prepareCall(sql);

            cs.setString(1, film.getFname());
            cs.setString(2, film.getDirector());
            cs.setInt(3, film.getReleaseYear());
            cs.setString(4, film.getFinfo());
            cs.setString(5, film.getPicture());
            // 注册输出参数
            cs.registerOutParameter(6, Types.INTEGER);

            cs.executeUpdate();
            int fid = cs.getInt(6);
            film.setFid(fid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(null, cs, conn);
        }

        return film;
    }


    public void deleteFilmByFid(int fid) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DaoUtil.getConnection();
            String sql = "{CALL delete_film(?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, fid);

            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(null, cs, conn);
        }
    }

    public void updateFilm(Film film) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DaoUtil.getConnection();
            String sql = "{CALL update_film(?, ?, ?, ?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, film.getFid());
            cs.setString(2, film.getFname());
            cs.setString(3, film.getDirector());
            cs.setInt(4, film.getReleaseYear());
            cs.setString(5, film.getFinfo());
            cs.setString(6, film.getPicture());

            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(null, cs, conn);
        }
    }

    public List<Film> searchFilms(String keyword) throws SQLException {
        List<Film> films = new ArrayList<>();
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM film WHERE fid LIKE ? OR fname LIKE ?");
        statement.setString(1, "%" + keyword + "%");
        statement.setString(2, "%" + keyword + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Film film = new Film();
            film.setFid(resultSet.getInt("fid"));
            film.setFname(resultSet.getString("fname"));
            film.setDirector(resultSet.getString("director"));
            film.setReleaseYear(resultSet.getInt("releaseYear"));
            film.setFinfo(resultSet.getString("finfo"));
            film.setPicture(resultSet.getString("picture"));
            film.setCommentSum(resultSet.getInt("comment_sum"));
            films.add(film);
        }

        DaoUtil.close(null, statement, conn);
        return films;
    }
}
