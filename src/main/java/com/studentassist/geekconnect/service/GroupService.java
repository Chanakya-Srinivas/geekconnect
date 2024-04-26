package com.studentassist.geekconnect.service;

import com.studentassist.geekconnect.model.Assignment;
import com.studentassist.geekconnect.model.Group;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.repository.GroupRepository;
import com.studentassist.geekconnect.repository.UserRepository;
import com.studentassist.geekconnect.responsemodel.AssignmentResponseModel;
import com.studentassist.geekconnect.responsemodel.GroupResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Group> getGroupsByCreatorId(String userId, Long creatorId) {
        return groupRepository.findByCreatorId(creatorId);
    }

    public List<GroupResponseModel> getGroupsByCourseAndCreator(String userId, Long courseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<GroupResponseModel> groups = new ArrayList<>();
            List<Group> dbGroups = groupRepository.findByCourseId(courseId);;
            for(Group group : dbGroups){
                groups.add(new GroupResponseModel(group));
            }
            return groups;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
}

