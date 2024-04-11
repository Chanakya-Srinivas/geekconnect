package com.studentassist.geekconnect.repository;
import com.studentassist.geekconnect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Define custom queries or use default methods from JpaRepository
    User findByUsername(String username);
}
