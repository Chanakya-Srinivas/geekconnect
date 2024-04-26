package com.studentassist.geekconnect.model;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Assignment {
    @Id
    @Column(name = "assignment_id")
    private String assignmentId;

    private String assignmentName;
    private String description;
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
