package com.studentassist.geekconnect.controller;

import com.studentassist.geekconnect.Response.UserResponse;
import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.responsemodel.AssignmentResponseModel;
import com.studentassist.geekconnect.responsemodel.UserResponseModel;
import com.studentassist.geekconnect.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geekconnect/{id}/assignment")
public class AssignmentController {


    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/{courseId}")
    public ResponseEntity<UserResponse> getAssignmentsByCourseId(@PathVariable String id, @PathVariable String courseId) {
        UserResponse response = new UserResponse();
        try {
            List<AssignmentResponseModel> assignments = assignmentService.getAssignmentsByCourseId(id,courseId);
            response.setMessage("Fetched All Assignments associated to course id!");
            response.setObject(assignments);
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

    @GetMapping("/students/{courseId}")
    public ResponseEntity<UserResponse> getAllAssignmentsByCourseId(@PathVariable String id, @PathVariable String courseId) {
        UserResponse response = new UserResponse();
        try {
            List<AssignmentResponseModel> assignments = assignmentService.getAllAssignmentsByCourseId(id,courseId);
            response.setMessage("Fetched All Assignments associated to course id!");
            response.setObject(assignments);
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

    @GetMapping("/")
    public ResponseEntity<UserResponse> getAllAssignments(@PathVariable String id) {
        UserResponse response = new UserResponse();
        try {
            List<AssignmentResponseModel> assignments = assignmentService.getAssignmentsByUserId(id);
            response.setMessage("Fetched All Assignments associated to user id!");
            response.setObject(assignments);
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

}
