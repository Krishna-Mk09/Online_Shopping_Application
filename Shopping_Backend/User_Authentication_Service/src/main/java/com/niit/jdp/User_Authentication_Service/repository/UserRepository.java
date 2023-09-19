package com.niit.jdp.User_Authentication_Service.repository;

import com.niit.jdp.User_Authentication_Service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
}
