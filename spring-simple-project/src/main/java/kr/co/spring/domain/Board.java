package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

/*
 * �Խñ� Entity
 * delData�� null���� �ƴϸ� ������ �Խñ۷� ó���Ͽ� ��ȸ�� ���ϰ� �ϵ����Ѵ�
 *  �� SQL���� ������ ���� �� �þ�� ���ε� ���������� ������ �ɱ�?
 *  �� ���� �� ������ ���� ����� ���� �ش� �÷��� �δ� ���� ���ٰ� �����Ǵµ� �����Ⱓ ������ ���� �Ϸ���?
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
