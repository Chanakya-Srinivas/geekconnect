package com.studentassist.geekconnect.model;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @Column(name = "id")
    private String id;

    private String courseName;

    @ManyToOne
    private User professor;

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

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }
}
