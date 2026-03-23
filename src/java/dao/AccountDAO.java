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

public class AccountDAO {

    public AccountDTO checkLogin(String username, String password) {

        try {
            Connection conn = DBUtils.getConnection();

            String sql = "SELECT * FROM Accounts WHERE username=? AND password=? AND status=1";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new AccountDTO(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getBoolean("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //
    public boolean register(String username, String password, String fullname) {

        if (checkUsernameExists(username)) {
            return false;
        }

        try {
            Connection conn = DBUtils.getConnection();

            String sql = "INSERT INTO Accounts(username,password,fullname,role,status) VALUES (?,?,?,'student',1)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //
    public boolean checkUsernameExists(String username) {

        try {
            Connection conn = DBUtils.getConnection();

            String sql = "SELECT id FROM Accounts WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
