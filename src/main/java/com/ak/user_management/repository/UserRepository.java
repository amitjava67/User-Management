package com.ak.user_management.repository;

import com.ak.user_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository   <User, Long> {
    //custom query methods can be defined here if needed
}
