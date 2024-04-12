package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    // Define custom query methods if needed


}
