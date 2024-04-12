package com.studentassist.geekconnect.responsemodel;


import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.model.User;

public class CourseResponseModel {

    public CourseResponseModel(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.professor = new UserResponseModel(course.getProfessor());
    }

    private String id;

    private String courseName;

    private UserResponseModel professor;

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public UserResponseModel getProfessor() {
        return professor;
    }

    public void setProfessor(UserResponseModel professor) {
        this.professor = professor;
    }
}
