package kr.co.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.spring.mvc.service.UserService;

/**
 * user 관련 컨트롤러
 *   api에 대한 지식을 좀 더 보완 후 회원 기능과 함께 수정
 * @author kodin
 *
 */


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/info/{userId}")
	public int userGetById(@PathVariable String userId){
		return service.userGetById(userId);		 
	}
}
