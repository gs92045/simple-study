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

import kr.co.spring.domain.Reply;
import kr.co.spring.domain.ReplySaveForm;

@Mapper
public interface ReplyMapper {
	
	@Insert("INSERT INTO REPLY (TITLE,CONTENTS,USER_SEQ,BOARD_SEQ)"
			+ "VALUES (#{parameter.title},#{parameter.contents},1,#{parameter.boardSeq})")
	@Options(useGeneratedKeys = true, keyProperty = "replySeq")
	void save(@Param("parameter") ReplySaveForm parameter);
	
	@Select("SELECT * FROM REPLY WHERE BOARD_SEQ=#{boardSeq}")
	@ResultMap("ReplyMap")
	List<Reply> list(@Param("boardSeq") int boardSeq);	
	
	@Select("SELECT * FROM REPLY WHERE REPLY_SEQ = #{replySeq}")
	@Results(id = "ReplyMap", value = {
			@Result(property="replySeq",column = "reply_seq"),
			@Result(property="title",column="title"),
			@Result(property="contents",column="contents"),
			@Result(property="userSeq",column="user_seq"),
			@Result(property="boardSeq",column="board_seq")
	})
	Reply getReply(@Param("replySeq")int replySeq);
}
