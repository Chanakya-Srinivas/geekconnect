package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.dto.CourseWithUserRoleDTO;
import com.studentassist.geekconnect.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    public List<CourseWithUserRoleDTO> getCoursesAndRoleByUsername(String username) {
        return userCourseRepository.findCoursesAndRoleByUserUsername(username);
    }

    // Other service methods for CRUD operations on courses can be implemented here
}

