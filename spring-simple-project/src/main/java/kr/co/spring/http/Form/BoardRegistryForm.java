package kr.co.spring.http.Form;

import lombok.Data;

/**
 * °Ô½Ã±Û µî·Ï Æû Àü¼Û°´Ã¼
 * 
 * @author kodin
 *
 */

@Data
public class BoardRegistryForm {
	public int BoardSeq;
	public String title;
	public String contents;
	
	public int userSeq;
	public String userId;
}
