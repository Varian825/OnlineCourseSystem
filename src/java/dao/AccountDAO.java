/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AccountDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountDAO {

    // ================= LOGIN =================
    public AccountDTO checkLogin(String username, String password) {

        String sql = "SELECT * FROM Accounts WHERE username=? AND password=? AND status=1";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AccountDTO(
                            rs.getInt("id"),
                            rs.getString("username"),
                            null,
                            rs.getString("fullname"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("role"),
                            rs.getBoolean("status"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= REGISTER =================
    public boolean register(String username, String password, String fullname) {

        if (username == null || password == null || fullname == null
                || username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }

        if (checkUsernameExists(username)) {
            return false;
        }

        String sql = "INSERT INTO Accounts(username,password,fullname,role,status) VALUES (?,?,?,'student',1)";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= CHECK USERNAME =================
    public boolean checkUsernameExists(String username) {

        String sql = "SELECT id FROM Accounts WHERE username=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= GET ALL USERS =================
    public ArrayList<AccountDTO> getAllUsers() {

        ArrayList<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AccountDTO acc = new AccountDTO(
                        rs.getInt("id"),
                        rs.getString("username"),
                        null,
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at")
                );
                list.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= GET USER BY ID =================
    public AccountDTO getUserById(int id) {

        String sql = "SELECT * FROM Accounts WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AccountDTO(
                            rs.getInt("id"),
                            rs.getString("username"),
                            null,
                            rs.getString("fullname"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("role"),
                            rs.getBoolean("status"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= ADD USER =================
    public boolean addUser(String username, String password, String fullname, String role) {

        if (username == null || password == null || fullname == null || role == null) {
            return false;
        }

        String sql = "INSERT INTO Accounts(username,password,fullname,role,status) VALUES (?,?,?,?,1)";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, role);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= UPDATE USER =================
    public boolean updateUser(int id, String fullname, String role) {

        String sql = "UPDATE Accounts SET fullname=?, role=? WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fullname);
            ps.setString(2, role);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= DELETE USER (SOFT DELETE) =================
    public boolean deleteUser(int id) {

        String sql = "UPDATE Accounts SET status = 0 WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
