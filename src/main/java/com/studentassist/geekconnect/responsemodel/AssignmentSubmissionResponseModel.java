package com.studentassist.geekconnect.responsemodel;

import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.model.UserAssignmentSubmission;

import java.time.LocalDateTime;

public class AssignmentSubmissionResponseModel {

    public AssignmentSubmissionResponseModel(UserAssignmentSubmission userAssignmentSubmission) {
        this.submissionId = userAssignmentSubmission.getSubmissionId();
        this.user = new UserResponseModel(userAssignmentSubmission.getUser());
        this.submissionDate = userAssignmentSubmission.getSubmissionDate();
        this.submissionText = userAssignmentSubmission.getSubmissionText();
        this.submissionFile = userAssignmentSubmission.getSubmissionFile();
        this.grade = userAssignmentSubmission.getGrade();
        this.comments = userAssignmentSubmission.getComments();
    }

    private String submissionId;

    private UserResponseModel user;

    private LocalDateTime submissionDate;

    private String submissionText;

    private String submissionFile;

    private Double grade;

    private String comments;

    // Getters and setters

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSubmissionText() {
        return submissionText;
    }

    public void setSubmissionText(String submissionText) {
        this.submissionText = submissionText;
    }

    public String getSubmissionFile() {
        return submissionFile;
    }

    public void setSubmissionFile(String submissionFile) {
        this.submissionFile = submissionFile;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
