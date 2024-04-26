package com.studentassist.geekconnect.repository;

import com.studentassist.geekconnect.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByCreatorId(Long creatorId);
    List<Group> findByCourseId(Long courseId);//, Long creatorId);
}
