package com.studentassist.geekconnect.responsemodel;
import com.studentassist.geekconnect.model.UserCourse;

import javax.persistence.*;

public class UserCourseResponseModel {

    public UserCourseResponseModel(UserCourse userCourse) {
        this.id = userCourse.getId();
        this.student = new UserResponseModel(userCourse.getStudent());
        this.course = new CourseResponseModel(userCourse.getCourse());
    }

    private Long id;
    private UserResponseModel student;
    private CourseResponseModel course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponseModel getStudent() {
        return student;
    }

    public void setStudent(UserResponseModel student) {
        this.student = student;
    }

    public CourseResponseModel getCourse() {
        return course;
    }

    public void setCourse(CourseResponseModel course) {
        this.course = course;
    }
}
