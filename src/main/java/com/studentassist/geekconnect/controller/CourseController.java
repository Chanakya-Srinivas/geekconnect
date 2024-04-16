package com.studentassist.geekconnect.controller;

import com.studentassist.geekconnect.Response.UserResponse;
import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.responsemodel.AssignmentResponseModel;
import com.studentassist.geekconnect.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geekconnect/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponse> getCoursesByUsername(@PathVariable String username) {
        UserResponse response = new UserResponse();
        try {
            List<CourseWithUserRoleDTO> courses = courseService.getCoursesAndRoleByUsername(username);
            response.setMessage("Fetched All courses associated to  user id!");
            response.setObject(courses);
            response.setStatus(HttpStatus.OK);
            System.out.println(response);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (Exception e){
            response.setMessage("User or Course not found");
            response.setObject(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }

    // Other CRUD operations for courses can be implemented here
}

