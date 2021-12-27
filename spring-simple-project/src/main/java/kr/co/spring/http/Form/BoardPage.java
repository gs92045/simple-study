package kr.co.spring.http.Form;


import lombok.Data;

@Data
public class BoardPage {
	public int count;
	public int offset;
	public int limit;
	
	public int prePage;
	public int current;
	public int overPage;
	public int endPage;
	
	public BoardPage(int current, int count, int offset,int limit) {
		this.current = current;
		this.count = count;
		this.offset = offset;
		this.limit = limit;
		calRange();
	}
	
	private void calRange() {
		int newer = 1;
		endPage = (count%3 == 0) ? count/3 : count/3 + 1;
		
		prePage = (1 < current-2) ? (current-2) : newer; 
		overPage = (current + 2 < endPage ) ? current + 2 : endPage;
	}
	
	public Boolean isEqual(String str) {
		int n = Integer.parseInt(str);
		
		return (n==current) ? true : false;
	}
	
}
