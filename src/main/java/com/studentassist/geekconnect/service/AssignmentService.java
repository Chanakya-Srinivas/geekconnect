package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.*;
import com.studentassist.geekconnect.repository.*;
import com.studentassist.geekconnect.responsemodel.AssignmentResponseModel;
import com.studentassist.geekconnect.responsemodel.AssignmentSubmissionResponseModel;
import com.studentassist.geekconnect.responsemodel.SubmissionListResponseModel;
import com.studentassist.geekconnect.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserAssignmentSubmissionRepository userAssignmentSubmissionRepository;

    @Autowired
    private CourseService courseService;

    public List<AssignmentResponseModel> getAssignmentsByCourseId(String userId, String courseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<AssignmentResponseModel> assignments = new ArrayList<>();
            List<Assignment> courseAssignments = assignmentRepository.findByCourseId(courseId);
            for(Assignment assignment : courseAssignments){
                assignments.add(new AssignmentResponseModel(assignment));
            }
            return assignments;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    public List<AssignmentResponseModel> getAssignmentsByUserId(String userId) {
        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            List<AssignmentResponseModel> assignments = new ArrayList<>();

            if(user.getRole()==UserRole.PROFESSOR){
                List<CourseWithUserRoleDTO> courses = courseService.getCoursesAndRoleByUserId(userId);
                for (CourseWithUserRoleDTO course : courses) {
                    List<Assignment> courseAssignments = assignmentRepository.findByCourseId(course.getCourse().getId());
                    for(Assignment assignment : courseAssignments){
                        assignments.add(new AssignmentResponseModel(assignment));
                    }
                }
            } else{
                // Fetch all courses associated with the user
                List<UserCourse> userCourses = userCourseRepository.findByStudentId(user.getId());
                // Fetch assignments for each course
                for (UserCourse uc : userCourses) {
                    List<Assignment> courseAssignments = assignmentRepository.findByCourseId(uc.getCourse().getId());
                    for(Assignment assignment : courseAssignments){
                        assignments.add(new AssignmentResponseModel(assignment));
                    }
                }
            }
            return assignments;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    public List<SubmissionListResponseModel> getAllAssignmentsByCourseId(String userId, String courseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getRole().equals(UserRole.STUDENT) || user.getRole().equals(UserRole.ADMIN)){
                throw new IllegalArgumentException("User " + user.getUsername() + " doesn't have access");
            } else if (user.getRole().equals(UserRole.TA)){
                List<UserCourse> userCourses = userCourseRepository.findByStudentIdAndCourseId(userId,courseId);

                if(userCourses.isEmpty()){
                    throw new IllegalArgumentException("User " + user.getUsername() + " not TA for this courses");
                }
            }

            List<Assignment> courseAssignments = assignmentRepository.findByCourseId(courseId);
            List<SubmissionListResponseModel> submissionList = new ArrayList<>();
            for(Assignment assignment : courseAssignments){
                SubmissionListResponseModel assignments = new SubmissionListResponseModel(new AssignmentResponseModel(assignment));
                List<UserAssignmentSubmission> dbAssignments = userAssignmentSubmissionRepository.findByAssignmentAssignmentId(assignment.getAssignmentId());
                for(UserAssignmentSubmission dbAssignment: dbAssignments){
                    assignments.getUserList().add(new AssignmentSubmissionResponseModel(dbAssignment));
                }
                submissionList.add(assignments);
            }
            return submissionList;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    // You can add more methods here for other assignment-related operations
}
