package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    // Define custom query methods if needed
}