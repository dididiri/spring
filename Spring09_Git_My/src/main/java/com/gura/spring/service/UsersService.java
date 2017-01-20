package com.gura.spring.service;

import java.util.Map;

import com.gura.spring.users.dto.UsersDto;



public interface UsersService {
	public void insert(UsersDto dto);
	public boolean isValid(UsersDto dto);
	public void update(UsersDto dto);
	public void delete(String id);
	public Map<String,Object> canUseId(String id);
}
