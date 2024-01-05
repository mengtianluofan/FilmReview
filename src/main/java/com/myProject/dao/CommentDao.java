package com.myProject.dao;

import com.myProject.entity.Comment;
import com.myProject.entity.FilmComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 18:33
 */
public class CommentDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Comment> getAllComment() throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "select * from comment";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        List<Comment> comments = new ArrayList<>();

        while (rs.next()) {
            int cid = rs.getInt("cid");
            int fid = rs.getInt("fid");
            int uid = rs.getInt("uid");
            String content = rs.getString("content");
            int parentid = rs.getInt("parentid");
            java.sql.Timestamp date = rs.getTimestamp("date");
            Comment comment = new Comment(cid, fid, uid, content, parentid, date);
            comments.add(comment);
        }
        DaoUtil.close(rs, ps, conn);
        return comments;
    }

    public List<Comment> getCommentsByFid(int fid) {
        List<Comment> comments = new ArrayList<>();
        try {
            conn = DaoUtil.getConnection();
            String sql = "select * from comment where fid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fid);
            rs = ps.executeQuery();

            while (rs.next()) {
                int cid = rs.getInt("cid");
                int uid = rs.getInt("uid");
                String content = rs.getString("content");
                int parentid = rs.getInt("parentid");
                java.sql.Timestamp date = rs.getTimestamp("date");
                Comment comment = new Comment(cid, fid, uid, content, parentid, date);
                comments.add(comment);
            }
            DaoUtil.close(rs, ps, conn);
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Comment getCommentByCid(int cid) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "select * from comment where cid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, cid);
        rs = ps.executeQuery();
        Comment comment = null;

        while (rs.next()) {
            int fid = rs.getInt("fid");
            int uid = rs.getInt("uid");
            String content = rs.getString("content");
            int parentid = rs.getInt("parentid");
            java.sql.Timestamp date = rs.getTimestamp("date");
            comment = new Comment(cid, fid, uid, content, parentid, date);
        }
        DaoUtil.close(rs, ps, conn);
        return comment;
    }

    public List<FilmComment> getFilmComments(int filmId) {
        List<FilmComment> comments = new ArrayList<>();
        try {
            conn = DaoUtil.getConnection();
            String sql = "{CALL GetTopLevelCommentsByFid(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, filmId);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                FilmComment comment = new FilmComment();
                comment.setCid(rs.getInt("cid"));
                comment.setUname(rs.getString("uname"));
                comment.setContent(rs.getString("content"));
                comment.setDate(rs.getTimestamp("date"));
                comment.setParentId(rs.getInt("parentid"));
                comment.setFid(rs.getInt("fid"));
                comments.add(comment);
                comment.setReplies(getRepliesByParentId(comment.getCid()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(null, ps, conn);
        }
        return comments;
    }

    public List<FilmComment> getRepliesByParentId(int parentId) throws SQLException {
        List<FilmComment> replies = new ArrayList<>();
        try {
            conn = DaoUtil.getConnection();
            String sql = "{CALL GetRepliesByParentId(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, parentId);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                FilmComment reply = new FilmComment();
                reply.setCid(rs.getInt("cid"));
                reply.setUname(rs.getString("uname"));
                reply.setContent(rs.getString("content"));
                reply.setDate(rs.getTimestamp("date"));
                reply.setParentId(rs.getInt("parentid"));
                reply.setFid(rs.getInt("fid"));
                replies.add(reply);
            }
        } finally {
            DaoUtil.close(null, ps, conn);
        }
        return replies;
    }


    public void addComment(Comment comment) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "{call AddComment(?, ?, ?, ?, ?)}";  // 存储过程调用语法
        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, comment.getFid());
            cstmt.setInt(2, comment.getUid());
            cstmt.setString(3, comment.getContent());
            cstmt.setInt(4, comment.getParentid());
            cstmt.setTimestamp(5, comment.getDate());
            cstmt.execute();
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
    }

    public void deleteCommentByCid(int cid) {
        try {
            conn = DaoUtil.getConnection();
            String sql = "{call DeleteComment(?)}";  // 存储过程调用语法
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, cid);
            cstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
    }

    public void updateComment(Comment comment) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "{call UpdateComment(?, ?, ?, ?, ?, ?)}";  // 存储过程调用语法
        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, comment.getCid());
            cstmt.setInt(2, comment.getFid());
            cstmt.setInt(3, comment.getUid());
            cstmt.setString(4, comment.getContent());
            cstmt.setInt(5, comment.getParentid());
            cstmt.setTimestamp(6, comment.getDate());
            cstmt.execute();
        } finally {
            DaoUtil.close(rs, ps, conn);
        }
    }


}
