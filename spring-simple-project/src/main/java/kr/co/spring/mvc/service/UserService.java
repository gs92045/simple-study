package kr.co.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.repository.UserRepository;

/**
 * User service
 * ���� ū ���� ��� x
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
