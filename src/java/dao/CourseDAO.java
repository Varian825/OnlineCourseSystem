/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CourseDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseDAO {

    // ================= GET ALL =================
    public ArrayList<CourseDTO> getAllCourses() {

        ArrayList<CourseDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Courses";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CourseDTO c = new CourseDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("fee"),
                        rs.getString("schedule"),
                        rs.getDate("start_date"),
                        rs.getTimestamp("created_at")
                );
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= GET BY ID =================
    public CourseDTO getCourseById(int id) {

        String sql = "SELECT * FROM Courses WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CourseDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("fee"),
                            rs.getString("schedule"),
                            rs.getDate("start_date"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= ADD =================
    public boolean addCourse(String name, String description, double fee, String schedule, String startDate) {

        String sql = "INSERT INTO Courses(name, description, fee, schedule, start_date) VALUES (?,?,?,?,?)";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, fee);
            ps.setString(4, schedule);
            ps.setDate(5, java.sql.Date.valueOf(startDate));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= UPDATE =================
    public boolean updateCourse(int id, String name, String description, double fee, String schedule) {

        String sql = "UPDATE Courses SET name=?, description=?, fee=?, schedule=? WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, fee);
            ps.setString(4, schedule);
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= DELETE =================
    public boolean deleteCourse(int id) {

        String sql = "DELETE FROM Courses WHERE id=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
