/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.RegistrationDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RegistrationDAO {

    // ================= GET BY USER =================
    public ArrayList<RegistrationDTO> getByUser(int accountId) {

        ArrayList<RegistrationDTO> list = new ArrayList<>();

        String sql = "SELECT r.*, c.name AS courseName, a.username "
                + "FROM Registrations r "
                + "JOIN Courses c ON r.course_id = c.id "
                + "JOIN Accounts a ON r.account_id = a.id "
                + "WHERE r.account_id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RegistrationDTO r = new RegistrationDTO(
                            rs.getInt("id"),
                            rs.getInt("account_id"),
                            rs.getInt("course_id"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at"),
                            rs.getString("courseName"),
                            rs.getString("username")
                    );
                    list.add(r);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= REGISTER COURSE =================
    public boolean registerCourse(int accountId, int courseId) {

        String sql = "INSERT INTO Registrations(account_id, course_id, status) VALUES (?,?,'pending')";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ps.setInt(2, courseId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= CANCEL =================
    public boolean cancelRegistration(int id) {

        String sql = "DELETE FROM Registrations WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
