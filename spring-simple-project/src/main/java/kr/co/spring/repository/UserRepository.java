package kr.co.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring.mybatis.mapper.UserMapper;

@Repository
@Transactional
public class UserRepository {
	@Autowired
	private UserMapper mapper;
	
	public int userGetById(String userId) {
		return mapper.userGetById(userId);
	}
}
