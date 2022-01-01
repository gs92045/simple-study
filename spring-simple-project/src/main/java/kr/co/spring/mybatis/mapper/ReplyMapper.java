package kr.co.spring.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring.domain.ReplyDto;

@Mapper
@Transactional
public interface ReplyMapper {
	final String selectSql = "SELECT R.REPLY_SEQ, R.BOARD_SEQ, R.USER_SEQ, U.USER_ID, R.CONTENTS, R.REG_DATE,R.UP_DATE, R.PARENT, R.DEPTH, R.REPLY_ORDER "
			+ "FROM REPLY R,USER U "
			+ "WHERE U.USER_SEQ=R.USER_SEQ";
	final String insertSql = "INSERT INTO REPLY "
			+ "(BOARD_SEQ, USER_SEQ, CONTENTS, REG_DATE, UP_DATE, PARENT, DEPTH, REPLY_ORDER) "
			+ "VALUES "
			+ "(#{p.boardSeq},#{p.userSeq},#{p.contents},"
			+ "NOW(),#{p.upDate},#{p.parent},"
			+ "#{p.depth},#{p.order}) ";
	
	@Insert(insertSql)
	@Options(useGeneratedKeys = true, keyProperty = "replySeq")
	void save(@Param("p") ReplyDto parameter);
	
	
	@Select(selectSql
			+ " AND REPLY_SEQ = #{replySeq}")
	@Results(id="ReplyDto", value= {
			@Result(property = "replySeq", column = "REPLY_SEQ"),
			@Result(property = "boardSeq", column = "BOARD_SEQ"),		
			@Result(property = "userSeq", column = "USER_SEQ"),		
			@Result(property = "userId", column = "USER_ID"),		
			@Result(property = "contents", column = "CONTENTS"),		
			@Result(property = "regDate", column = "REG_DATE"),		
			@Result(property = "upDate", column = "UP_DATE"),		
			@Result(property = "parent", column = "PARENT"),		
			@Result(property = "depth", column = "DEPTH"),		
			@Result(property = "order", column = "REPLY_ORDER"),		
	})
	ReplyDto getReply(@Param("replySeq") int replySeq);
	
	
	@Select("SELECT R.REPLY_SEQ, R.BOARD_SEQ,R.USER_SEQ, U.USER_ID, R.CONTENTS,R.REG_DATE,R.UP_DATE, R.PARENT,R.DEPTH,R.REPLY_ORDER"
			+ " FROM REPLY R, USER U" 
			+ " WHERE R.BOARD_SEQ=#{boardSeq}"
			+ " AND U.USER_SEQ = R.USER_SEQ"
			+ " AND R.DEL_DATE IS NULL"
			+ " ORDER BY R.REPLY_ORDER"
	)
	@ResultMap("ReplyDto")
	List<ReplyDto> getList(@Param("boardSeq") int boardSeq);
	
	
	@Update("UPDATE REPLY"
			+ " SET REPLY_ORDER = REPLY_ORDER + 1"
			+ " WHERE REPLY_ORDER >= #{order}"
			+ " AND BOARD_SEQ = #{boardSeq}"
			)
	void updateOrder(@Param("boardSeq") int boardSeq, @Param("order") int order);

	@Select("SELECT COUNT(*)"
			+ " FROM REPLY"
			+ " WHERE BOARD_SEQ=#{boardSeq}")
	int countReply(@Param("boardSeq") int boardSeq);
}
