package com.studentassist.geekconnect.controller;

import com.studentassist.geekconnect.Response.UserResponse;
import com.studentassist.geekconnect.model.Group;
import com.studentassist.geekconnect.responsemodel.AssignmentResponseModel;
import com.studentassist.geekconnect.responsemodel.GroupResponseModel;
import com.studentassist.geekconnect.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geekconnect/{userId}/courses/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/byCreator/{creatorId}")
    public List<Group> getGroupsByCreatorId(@PathVariable String userId, @PathVariable Long creatorId) {
        return groupService.getGroupsByCreatorId(userId, creatorId);
    }

    @GetMapping("/byCourse/{courseId}")
    public ResponseEntity<UserResponse> getGroupsByCourseAndCreator(@PathVariable String userId, @PathVariable Long courseId) {
        UserResponse response = new UserResponse();
        try {
            List<GroupResponseModel> groups = groupService.getGroupsByCourseAndCreator(userId, courseId);
            response.setMessage("Fetched All groups associated to course id!");
            response.setObject(groups);
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

