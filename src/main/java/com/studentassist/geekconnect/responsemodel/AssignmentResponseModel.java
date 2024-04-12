package com.studentassist.geekconnect.responsemodel;
import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.model.Course;

import java.time.LocalDate;


public class AssignmentResponseModel {

    public AssignmentResponseModel(Assignment assignment) {
        this.assignmentId = assignment.getAssignmentId();
        this.assignmentName = assignment.getAssignmentName();
        this.description = assignment.getDescription();
        this.deadline = assignment.getDeadline();
        this.course = new CourseResponseModel(assignment.getCourse());
    }

    private String assignmentId;
    private String assignmentName;
    private String description;
    private LocalDate deadline;
    private CourseResponseModel course;
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

    public CourseResponseModel getCourse() {
        return course;
    }

    public void setCourse(CourseResponseModel course) {
        this.course = course;
    }
}
