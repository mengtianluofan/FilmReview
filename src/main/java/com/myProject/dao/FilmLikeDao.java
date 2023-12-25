package com.myProject.dao;

import com.myProject.entity.FilmLike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/22 17:27
 */
public class FilmLikeDao {
    public void addFilmLike(int uid, int fid) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DaoUtil.getConnection();
            String sql = "{CALL AddFilmLike(?, ?)}";
            cs = conn.prepareCall(sql);

            cs.setInt(1, uid);
            cs.setInt(2, fid);

            cs.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(null, cs, conn);
        }
    }

    public List<FilmLike> getFilmLikesByUid(int uid) {
        System.out.println("uid: " + uid);
        Connection conn = null;
        PreparedStatement ps = null;
        List<FilmLike> likes = new ArrayList<>();

        try {
            conn = DaoUtil.getConnection();
            String sql = "SELECT * FROM filmlike WHERE uid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.executeQuery();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int lid = rs.getInt("likeid");
                int fid = rs.getInt("fid");
                Timestamp time = rs.getTimestamp("opTime");
                FilmLike like = new FilmLike(lid, uid, fid, time);
                likes.add(like);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(null, ps, conn);
        }
        return likes;
    }

    public boolean checkFilmLike(int uid, int fid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DaoUtil.getConnection();
            String sql = "SELECT * FROM filmlike WHERE uid = ? AND fid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, fid);
            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
    }
}
