package com.studentassist.geekconnect.responsemodel;

import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.model.Group;
import com.studentassist.geekconnect.model.User;

import javax.persistence.*;

public class GroupResponseModel {

    public GroupResponseModel(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.creator = group.getCreator();
        this.course = group.getCourse();
    }

    private Long id;

    private String name;

    private User creator;

    private Course course;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
