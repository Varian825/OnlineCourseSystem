/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;

public class CourseDTO implements Serializable {

    private int id;
    private String name;
    private String description;
    private double fee;
    private String schedule;
    private Date startDate;
    private Date createdAt; // ✅ thêm cho chuẩn DB

    public CourseDTO() {
    }

    public CourseDTO(int id, String name, String description,
            double fee, String schedule, Date startDate, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.schedule = schedule;
        this.startDate = startDate;
        this.createdAt = createdAt;
    }

    // ===== Getter & Setter =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
