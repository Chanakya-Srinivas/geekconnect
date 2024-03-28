package com.studentassist.geekconnect.repository;
import com.studentassist.geekconnect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Define custom queries or use default methods from JpaRepository
    User findByUsername(String username);
}
