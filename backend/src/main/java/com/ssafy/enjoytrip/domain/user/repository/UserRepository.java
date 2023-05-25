package com.ssafy.enjoytrip.domain.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByLoginId(String loginId);
	User findByEmail(String email);
	List<User> findByNameContains(String name);
	List<User> findByEmailContains(String email);
}
