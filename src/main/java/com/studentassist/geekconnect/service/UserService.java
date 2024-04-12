package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.model.Course;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.responsemodel.UserResponseModel;
import com.studentassist.geekconnect.repository.UserCourseRepository;
import com.studentassist.geekconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    public UserResponseModel getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserResponseModel(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserResponseModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseModel> result = new ArrayList<>();
        for(User user : users){
            result.add(new UserResponseModel(user));
        }
        return result;
    }

//    public List<CourseWithUserRoleDTO> getCoursesAndRoleByUsername(String username) {
//        return userCourseRepository.findCoursesAndRoleByUserUsername(username);
//    }


    // Other service methods
}
