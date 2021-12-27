package kr.co.spring.http.Form;

import lombok.Data;

@Data
public class ReplySaveForm {
	public int replySeq;
	public String contents;
	public String userId;
	public int userSeq;
	public int boardSeq;
}
