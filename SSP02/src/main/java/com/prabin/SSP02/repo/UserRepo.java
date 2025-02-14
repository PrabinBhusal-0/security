package com.prabin.SSP02.repo;

import com.prabin.SSP02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);
}
