package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.repository.CourseRepository;
import com.studentassist.geekconnect.repository.UserCourseRepository;
import com.studentassist.geekconnect.repository.UserRepository;
import com.studentassist.geekconnect.responsemodel.UserResponseModel;
import com.studentassist.geekconnect.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    public List<CourseWithUserRoleDTO> getCoursesAndRoleByUserId(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            if(user.get().getRole()== UserRole.PROFESSOR){
                List<Course> courses = courseRepository.findByProfessorId(userId);
                List<CourseWithUserRoleDTO> result = new ArrayList<>();
                for(Course course: courses){
                    result.add(new CourseWithUserRoleDTO(course,UserRole.PROFESSOR));
                }
                return result;
            }
            return userCourseRepository.findCoursesAndRoleByUserUsername(user.get().getUsername());
        } else {
            throw new IllegalArgumentException("User not found with Username: " + userId);
        }

    }

    // Other service methods for CRUD operations on courses can be implemented here
}

