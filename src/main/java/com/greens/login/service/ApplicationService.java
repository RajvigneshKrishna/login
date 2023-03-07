package com.greens.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greens.login.model.UserDetails;
import com.greens.login.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;

	public Map<String, Object> login(UserDetails userDetails) {
		String emailId = userDetails.getEmailId();
		String password = userDetails.getPassword();
		boolean vldLogin = false;
		UserDetails usrDtls = applicationRepository.findByEmailIdAndPassword(emailId, password);
		if (usrDtls.getEmailId().equalsIgnoreCase(emailId) && usrDtls.getPassword().equals(password)) {
			vldLogin = true;
		}
		Map<String, Object> user = new HashMap<>();
		user.put("usrData", usrDtls);
		user.put("vldLogin", vldLogin);
		return user;
	}

	public void register(UserDetails userDetails) {
		applicationRepository.save(userDetails);
	}
}
