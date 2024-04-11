package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignment> getAssignmentsByCourseId(String courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    // You can add more methods here for other assignment-related operations
}
