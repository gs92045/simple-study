package kr.co.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.repository.UserRepository;

/**
 * User service
 * 아직 큰 관련 기능 x
 * @author kodin
 *
 */

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public int userGetById(String userId) {
		return repository.userGetById(userId);
	}
}
