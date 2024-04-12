package com.studentassist.geekconnect.dto;

import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.utils.UserRole;

import java.beans.JavaBean;

//@JavaBean
public class CourseWithUserRoleDTO {

    private Course course;
    private UserRole userRole;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public CourseWithUserRoleDTO(Course course, UserRole userRole) {
        this.course = course;
        this.userRole = userRole;
    }


}
