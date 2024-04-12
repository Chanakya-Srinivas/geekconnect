package com.studentassist.geekconnect.controller;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geekconnect/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CourseWithUserRoleDTO>> getCoursesByUsername(@PathVariable String username) {
        List<CourseWithUserRoleDTO> courses = courseService.getCoursesAndRoleByUsername(username);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Other CRUD operations for courses can be implemented here
}

