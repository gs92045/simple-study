package kr.co.spring.http.Form;

import lombok.Data;
/**
 * �Խñ� ���� �����۰�ü
 * @author kodin
 *
 */
@Data
public class BoardUpdateForm {
	int boardSeq;
	String userId;
	String title;
	String contents;
}
