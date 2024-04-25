package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.repository.UserCourseRepository;
import com.studentassist.geekconnect.repository.UserRepository;
import com.studentassist.geekconnect.responsemodel.UserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserRepository userRepository;


    public List<CourseWithUserRoleDTO> getCoursesAndRoleByUsername(String username) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()) {
            return userCourseRepository.findCoursesAndRoleByUserUsername(username);

        } else {
            throw new IllegalArgumentException("User not found with Username: " + username);
        }

    }

    // Other service methods for CRUD operations on courses can be implemented here
}

