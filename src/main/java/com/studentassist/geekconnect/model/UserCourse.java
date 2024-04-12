package com.studentassist.geekconnect.model;
import com.studentassist.geekconnect.utils.UserRole;

import javax.persistence.*;

@Entity
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('STUDENT', 'TA') DEFAULT 'STUDENT'")
    private UserRole role = UserRole.STUDENT;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
