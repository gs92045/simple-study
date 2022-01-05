package kr.co.spring.http.Form;


import lombok.Data;
/**
 * ����¡ ó���� ���� Ŭ����
 * 
 *  �Խ���, ��� ����¡ ó���� ���� ���� Ŭ������ �����ʿ�
 *  ����¡ ��ü�� ������ ����Ʈ �ܿ��� newer �� older �� ó���ϵ��� �Ͽ��µ� �� ���� ���� ã�ƺ����ҵ�
 * 
 * @author kodin
 *
 */

@Data
public class BoardPage {
	public int count;
	public int offset;
	public int limit;
	
	
	//��û���� ���������� ���� ������
	public int prePage;
	//���� ��û���� ������
	public int current;
	//������ ���������� ���� ������
	public int overPage;
	//������ ������
	public int endPage;
	
	public BoardPage(int current, int count, int offset,int limit) {
		this.current = current;
		this.count = count;
		this.offset = offset;
		this.limit = limit;
		calRange();
	}
	
	private void calRange() {
		
		endPage = count % 5 == 0 ? count/5 : count/5 +1;
		prePage = (1 < current-5) ? (current-5) : 1; 
		overPage = (current + 5 < endPage ) ? current + 5 : endPage;
	}	
}
