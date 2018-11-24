package com.mymanuals.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.mymanuals.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	List<User> findbyUserID(@Param("UserId") String Userid);
	User findbyEmailId(@Param("EmailId") String EmailId);
}
