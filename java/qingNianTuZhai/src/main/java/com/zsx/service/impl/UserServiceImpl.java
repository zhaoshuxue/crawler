package com.zsx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsx.dao.TuserMapper;
import com.zsx.entity.Tuser;
import com.zsx.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	private TuserMapper tuserMapper;
	
	@Override
	public void addUser(Tuser user) {
		tuserMapper.insertSelective(user);
	}

	@Override
	public Tuser getUserById(Long id) {
		return tuserMapper.selectByPrimaryKey(id);
	}

}
