package com.zsx.service;

import com.zsx.entity.Tuser;

public interface UserService {

	void addUser(Tuser user);
	
	Tuser getUserById(Long id);
	
}
