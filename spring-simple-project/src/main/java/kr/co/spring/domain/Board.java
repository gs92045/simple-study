package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

/*
 * 게시글 Entity
 * delData가 null값이 아니면 삭제된 게시글로 처리하여 조회를 못하게 하도록한다
 *  ㄴ SQL문의 쿼리가 한줄 더 늘어나는 것인데 성능적으로 문제가 될까?
 *  ㄴ 삭제 후 복구를 위한 기능을 위해 해당 컬럼을 두는 것이 좋다고 생각되는데 일정기간 지나면 삭제 하려면?
 */

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
