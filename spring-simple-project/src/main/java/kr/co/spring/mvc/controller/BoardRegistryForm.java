package kr.co.spring.mvc.controller;

import lombok.Data;

@Data
public class BoardRegistryForm {
	public int BoardSeq;
	public String title;
	public String contents;
	
	public int userSeq;
	public String userId;
}
