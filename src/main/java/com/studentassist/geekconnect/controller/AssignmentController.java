package com.studentassist.geekconnect.controller;

import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geekconnect/{id}/assignment")
public class AssignmentController {


    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourseId(@PathVariable String courseId) {
        System.out.println(courseId);
        List<Assignment> assignments = assignmentService.getAssignmentsByCourseId(courseId);
        return ResponseEntity.ok(assignments);
    }

}
