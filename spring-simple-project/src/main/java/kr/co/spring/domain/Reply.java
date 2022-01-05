package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

/*
 * ��� Entity
 * 
 * userSeq�� �ܷ�Ű������ userId�� �ѹ� �� īƼ�ǰ��� �ϴ°��� ���������� ������ ���� ������?
 *   �� Seq�� �Բ� user Id�� ���� �����ϵ��� �����غ��ٸ� �� ������ ������?
 *  
 * ����� ���� parent,depth,order �� �÷� ����, Ư�� ����� ����� �� ��� ���� ������ �ٿ� ǥ��
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
	
	//���� �ٴ� ��۹�ȣ
	public int parent;
	public int depth;
	public int order;
}
