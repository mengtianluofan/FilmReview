package com.myProject.dao;

import com.myProject.entity.User;
import com.myProject.entity.UserInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 18:32
 */
public class UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getAllUser() throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "select * from user";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        List<User> users = new ArrayList<>();

        while (rs.next()) {
            int uid = rs.getInt("uid");
            String uname = rs.getString("uname");
            String password = rs.getString("password");
            String role = rs.getString("role");
            User user = new User(uid, uname, password, role);
            users.add(user);
        }
        DaoUtil.close(rs, ps, conn);
        return users;
    }

    public User getUserByUid(int uid) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "select * from user where uid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, uid);
        rs = ps.executeQuery();
        User user = null;

        while (rs.next()) {
            String uname = rs.getString("uname");
            String password = rs.getString("password");
            String role = rs.getString("role");
            user = new User(uid, uname, password, role);
        }
        DaoUtil.close(rs, ps, conn);
        return user;
    }

    public int addUser(User user) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "insert into user(uname, password, role) values(?, ?, ?)";

        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user.getUname());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        int uid = 0;
        if (rs.next()) {
            uid = rs.getInt(1);
        }
        DaoUtil.close(rs, ps, conn);
        return uid;
    }

    public void deleteUserByUid(int uid) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "delete from user where uid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, uid);
        ps.executeUpdate();

        DaoUtil.close(rs, ps, conn);
    }

    public void updateUser(User user) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "update user set uname = ?, password = ?, role = ? where uid = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUname());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setInt(4, user.getUid());
        ps.executeUpdate();

        DaoUtil.close(rs, ps, conn);
    }

    public User checkLogin(int uid, String password, String role) throws SQLException {
        conn = DaoUtil.getConnection();
        String sql = "select * from user where uid = ? and password = ? and role = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, uid);
        ps.setString(2, password);
        ps.setString(3, role);
        rs = ps.executeQuery();
        User user = null;

        while (rs.next()) {
            String uname = rs.getString("uname");
            user = new User(uid, uname, password, role);
        }
        DaoUtil.close(rs, ps, conn);
        return user;
    }

    public UserInfo getUserInfoByUid(int uid) {
        UserInfo userInfo = null;
        try {
            conn = DaoUtil.getConnection();
            String sql = "select * from userinfo where uid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();

            while (rs.next()) {
                String uname = rs.getString("uname");
                int commentCount = rs.getInt("comment_count");
                int likeCount = rs.getInt("like_count");
                userInfo = new UserInfo(uid, uname, commentCount, likeCount);
            }

            sql = "select * from filmlike_foruser where uid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            String likeFilms = "";
            while (rs.next()) {
                String fname = rs.getString("fname");
                likeFilms += fname + ",";
            }
            userInfo.setLikeFilms(likeFilms);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DaoUtil.close(rs, ps, conn);
        return userInfo;
    }
}
