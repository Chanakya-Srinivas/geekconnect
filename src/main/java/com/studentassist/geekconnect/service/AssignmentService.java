package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.model.UserCourse;
import com.studentassist.geekconnect.repository.AssignmentRepository;
import com.studentassist.geekconnect.repository.UserCourseRepository;
import com.studentassist.geekconnect.repository.UserRepository;
import com.studentassist.geekconnect.responsemodel.AssignmentResponseModel;
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

            // Fetch all courses associated with the user
            List<UserCourse> userCourses = userCourseRepository.findByStudentId(user.getId());

            List<AssignmentResponseModel> assignments = new ArrayList<>();
            // Fetch assignments for each course
            for (UserCourse uc : userCourses) {
                List<Assignment> courseAssignments = assignmentRepository.findByCourseId(uc.getCourse().getId());
                for(Assignment assignment : courseAssignments){
                    assignments.add(new AssignmentResponseModel(assignment));
                }
            }
            return assignments;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    public List<AssignmentResponseModel> getAllAssignmentsByCourseId(String userId, String courseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getRole().equals(UserRole.STUDENT) || user.getRole().equals(UserRole.ADMIN)){
                throw new IllegalArgumentException("User " + userId + " doesn't have access : ");
            } else if (user.getRole().equals(UserRole.TA)){
                List<UserCourse> userCourses = userCourseRepository.findByStudentIdAndCourseId(userId,courseId,UserRole.TA);
                if(userCourses.size()==0){
                    throw new IllegalArgumentException("User " + userId + " doesn't have access : ");
                }
            }

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

    // You can add more methods here for other assignment-related operations
}
