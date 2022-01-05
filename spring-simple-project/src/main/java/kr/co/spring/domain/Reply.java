package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

/*
 * 댓글 Entity
 * 
 * userSeq가 외래키이지만 userId를 한번 더 카티션곱을 하는것이 성능적으로 문제가 되지 않을까?
 *   ㄴ Seq와 함께 user Id도 같이 저장하도록 변경해본다면 더 편하지 않을까?
 *  
 * 답글을 위해 parent,depth,order 를 컬럼 설정, 특정 댓글의 답글을 달 경우 마진 왼쪽을 줄여 표현
 * 
 * 
 */
@Data
public class Reply {
	public int replySeq;
	public int userSeq;
	public int boardSeq;
	
	public String contents;
	
	public Date regDate;
	public Date upDate;
	public Date delDate;
	
	//대댓글 다는 댓글번호
	public int parent;
	public int depth;
	public int order;
}
