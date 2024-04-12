package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.UserCourse;
import com.studentassist.geekconnect.utils.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    // Define custom query methods if needed
    List<UserCourse> findByStudentId(String userId);

    List<UserCourse> findByStudentIdAndCourseId(String userId, String courseId, UserRole ta);
//
//    @Query("SELECT uc.course,uc.role FROM UserCourse uc " +
//            "JOIN uc.student u " +
//            "JOIN uc.course c " +
//            "WHERE u.username = :username")
//    List<Object[]> findCoursesByUserUsername(@Param("username")String username);


    @Query("SELECT new com.studentassist.geekconnect.dto.CourseWithUserRoleDTO(uc.course, uc.role) FROM UserCourse uc " +
            "JOIN uc.student u " +
            "JOIN uc.course c " +
            "WHERE u.username = :username")
    List<CourseWithUserRoleDTO> findCoursesAndRoleByUserUsername(@Param("username") String username);

}