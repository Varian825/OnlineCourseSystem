/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;

public class RegistrationDTO implements Serializable {

    private int id;
    private int accountId;
    private int courseId;
    private String status;
    private Date createdAt;

    // ✅ thêm để JOIN hiển thị
    private String courseName;
    private String username;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int id, int accountId, int courseId,
            String status, Date createdAt,
            String courseName, String username) {
        this.id = id;
        this.accountId = accountId;
        this.courseId = courseId;
        this.status = status;
        this.createdAt = createdAt;
        this.courseName = courseName;
        this.username = username;
    }

    // ===== Getter & Setter =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
