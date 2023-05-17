package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> getUserByLogin(String login);

}