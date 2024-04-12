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
@RequestMapping("/api/geekconnect/{userId}/assignment")
public class AssignmentController {


    @Autowired
    private AssignmentService assignmentService;

//    @CrossOrigin
    @GetMapping("/{courseId}")
    public ResponseEntity<UserResponse> getAssignmentsByCourseId(@PathVariable String userId, @PathVariable String courseId) {
        UserResponse response = new UserResponse();
        try {
            List<AssignmentResponseModel> assignments = assignmentService.getAssignmentsByCourseId(userId,courseId);
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

//    @CrossOrigin
    @GetMapping("/students/{courseId}")
    public ResponseEntity<UserResponse> getAllAssignmentsByCourseId(@PathVariable String userId, @PathVariable String courseId) {
        UserResponse response = new UserResponse();
        try {
            List<AssignmentResponseModel> assignments = assignmentService.getAllAssignmentsByCourseId(userId,courseId);
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

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<UserResponse> getAllAssignments(@PathVariable String userId) {
        UserResponse response = new UserResponse();
        try {
            List<AssignmentResponseModel> assignments = assignmentService.getAssignmentsByUserId(userId);
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
