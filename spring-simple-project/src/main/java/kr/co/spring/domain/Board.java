package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	int boardSeq;
	String userId;
	Date regDate;
	Date upDate;
	Date delDate;
	int comments;
	int views;
	String title;
	String contents;
}
