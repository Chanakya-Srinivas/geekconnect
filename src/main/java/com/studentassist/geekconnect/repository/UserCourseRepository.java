package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.model.UserCourse;
import com.studentassist.geekconnect.utils.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    // Define custom query methods if needed
    List<UserCourse> findByStudentId(String userId);

    List<UserCourse> findByStudentIdAndCourseId(String userId, String courseId, UserRole ta);
}