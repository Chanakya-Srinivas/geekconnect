package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {

    List<Assignment> findByCourseId(String courseId);

    // Define custom query methods if needed
}

