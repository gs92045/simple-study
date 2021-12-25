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

import kr.co.spring.domain.Board;
import kr.co.spring.domain.BoardSaveForm;
import kr.co.spring.domain.BoardUpdateForm;

@Mapper
public interface BoardMapper {
	
	@Select("SELECT * FROM BOARD WHERE BOARD_SEQ=#{boardSeq} AND DEL_DATE IS NULL")
	@Results(id = "BoardMap", value = {
	            @Result(property = "boardSeq", column = "board_seq"),
	            @Result(property = "userId", column = "user_id"),
	            @Result(property = "regDate", column = "reg_date"),
	            @Result(property = "upDate", column = "up_date"),
	            @Result(property = "delDate", column = "del_date")
	            })
	Board get(@Param("boardSeq") int boardSeq);
	
	@Select("SELECT * FROM BOARD WHERE DEL_DATE IS NULL AND USER_ID = #{userId}")
	@ResultMap("BoardMap")
	Board getById(@Param("userId") String userId);
	
	
	@Select("SELECT * FROM BOARD WHERE DEL_DATE IS NULL")
	@ResultMap("BoardMap")
	List<Board> getList();
	
	
	@Insert("INSERT INTO BOARD (TITLE,CONTENTS,USER_ID,REG_DATE)"
			+ "VALUES (#{parameter.title},#{parameter.contents},#{parameter.userId},NOW())")
	void save(@Param("parameter") BoardSaveForm board);
	

	@Update("UPDATE BOARD SET TITLE=#{parameter.title}, CONTENTS=#{parameter.contents},UP_DATE=NOW() WHERE BOARD_SEQ=#{parameter.boardSeq}")
	@Options(useGeneratedKeys = true, keyProperty = "boardSeq")
	void update(@Param("parameter") BoardUpdateForm board);
	
	//@Delete("DELETE FROM BOARD WHERE BOARD_SEQ=#{boardSeq}")
	@Update("UPDATE BOARD SET DEL_DATE=NOW() WHERE BOARD_SEQ=#{boardSeq}")
	void delete(@Param("boardSeq") int boardSeq);
	
	@Update("UPDATE BOARD SET COMMENTS = COMMENTS + 1 WHERE BOARD_SEQ = #{boardSeq}")
	void addComment(@Param("boardSeq") int boardSeq);
	
}
