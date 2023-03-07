package com.greens.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greens.login.model.UserDetails;

@Repository
public interface ApplicationRepository extends JpaRepository<UserDetails, Long>{

	UserDetails findByEmailIdAndPassword(String userName, String password);

}
