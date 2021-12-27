package kr.co.spring.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring.domain.Board;
import kr.co.spring.domain.BoardSaveForm;
import kr.co.spring.domain.BoardUpdateForm;
import kr.co.spring.http.Form.BoardVO;
import kr.co.spring.mvc.controller.BoardRegistryForm;

@Mapper
//@Transactional
public interface BoardMapper {
	final String select = "SELECT B.BOARD_SEQ, B.TITLE, B.CONTENTS, U.USER_ID, B.REG_DATE, B.UP_DATE, B.COMMENTS FROM BOARD B, USER U";
	
	/*
	 * �Խñ� ��ȸ
	 * 
	 */
	@Select(select
			+ " WHERE BOARD_SEQ=#{boardSeq}"
			+ " AND B.USER_SEQ=U.USER_SEQ"
			+ " AND B.DEL_DATE IS NULL"
			)
	@Results(id = "BoardMap", value = {
	            @Result(property = "boardSeq", column = "board_seq"),
	            @Result(property = "userId", column = "user_id"),
	            @Result(property = "regDate", column = "reg_date"),
	            @Result(property = "upDate", column = "up_date"),
	            @Result(property = "title",column = "title"),
	            @Result(property = "contents",column = "contents"),
	            @Result(property = "comments",column = "comments")
	})
	BoardVO get(@Param("boardSeq") int boardSeq);
	
	/**
	 * ���� �˻� ����Ʈ
	 * @param keyword
	 * @return
	 */
	@Select({"<script>",
			"SELECT B.BOARD_SEQ, B.TITLE, B.CONTENTS, U.USER_ID, B.REG_DATE, B.UP_DATE, B.COMMENTS FROM BOARD B, USER U ",
			"<where>",
			"<if test='#{keyword} != null'>",
			"AND B.TITLE LIKE CONCAT('%',#{keyword},'%')",
			"</if>",
			"</where>",
			"</script>"
	})
	@ResultMap("BoardMap")
	List<BoardVO> getSearch(@Param("keyword") String keyword);

	//(�����ʿ�) user id�� �ۼ��� �Խñ� �˻�
	@Select(select
			+ " WHERE B.DEL_DATE IS NULL"
			+ " AND U.USER_ID = #{userId}"
			+ " AND B.USER_SEQ=U.USER_SEQ")
	@ResultMap("BoardMap")
	BoardVO getById(@Param("userId") String userId);
	
	
	//�Խñ� ����Ʈ ��ȸ
	@Select(select
			+ " WHERE B.DEL_DATE IS NULL"
			+ " AND B.USER_SEQ=U.USER_SEQ"
			+ " ORDER BY REG_DATE DESC"
			+ " LIMIT #{offset}, #{limit}" 
			)
	@ResultMap("BoardMap")
	List<BoardVO> getList(@Param("offset") int offset, @Param("limit") int limit);
	
	//�Խñ� ���
	@Insert("INSERT INTO BOARD (TITLE,CONTENTS,USER_ID,USER_SEQ,REG_DATE)"
			+ "VALUES (#{parameter.title},#{parameter.contents},#{parameter.userId},#{parameter.userSeq},NOW())")
	@Options(useGeneratedKeys = true, keyProperty = "boardSeq")
	void save(@Param("parameter") BoardRegistryForm board);
	
	
	//�Խñ� ����
	@Update("UPDATE BOARD SET TITLE=#{parameter.title}, CONTENTS=#{parameter.contents},UP_DATE=NOW() WHERE BOARD_SEQ=#{parameter.boardSeq}")
	void update(@Param("parameter") BoardUpdateForm board);
	
	//�Խñ� ����
	//@Delete("DELETE FROM BOARD WHERE BOARD_SEQ=#{boardSeq}")
	@Update("UPDATE BOARD SET DEL_DATE=NOW() WHERE BOARD_SEQ=#{boardSeq}")
	void delete(@Param("boardSeq") int boardSeq);
	
	//�Խù� ��� �� ����
	@Update("UPDATE BOARD SET COMMENTS = COMMENTS + 1 WHERE BOARD_SEQ = #{boardSeq}")
	void addComment(@Param("boardSeq") int boardSeq);

	//�Խù� ��� �� ����
	@Update("UPDATE BOARD SET VIEWS = VIEWS + 1 WHERE BOARD_SEQ = #{boardSeq}")
	void addView(@Param("boardSeq") int boardSeq);
	
	//�Խñ� ���� ��ȸ
	@Select("SELECT COUNT(*) FROM BOARD WHERE DEL_DATE IS NULL")
	int getCount();
}
