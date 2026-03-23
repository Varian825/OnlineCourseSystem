/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class CourseDAO {

    public ArrayList<CourseDTO> getAllCourses() {

        ArrayList<CourseDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();

            String sql = "SELECT * FROM Courses";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CourseDTO c = new CourseDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("fee"),
                        rs.getString("schedule"),
                        rs.getDate("start_date")
                );

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //
    public CourseDTO getCourseById(int id) {

        try {
            Connection conn = DBUtils.getConnection();

            String sql = "SELECT * FROM Courses WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CourseDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("fee"),
                        rs.getString("schedule"),
                        rs.getDate("start_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
