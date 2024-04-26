package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.model.UserAssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAssignmentSubmissionRepository extends JpaRepository<UserAssignmentSubmission, Long> {
    // You can add custom query methods here if needed
    List<UserAssignmentSubmission> findByAssignmentAssignmentId(String assignment_id);
}
