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
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.http.Form.ReplyVO;

@Mapper
public interface ReplyMapper {
	final String insert = "INSERT INTO REPLY";
	final String select = "SELECT U.USER_ID, R.CONTENTS FROM USER U, REPLY R";
	
	
	@Insert(insert
			+" (CONTENTS, USER_SEQ, BOARD_SEQ, REG_DATE)"
			+" VALUES"
			+" (#{parameter.contents},#{parameter.userSeq},#{parameter.boardSeq},NOW())")
	@Options(useGeneratedKeys = true, keyProperty = "replySeq")
	void save(@Param("parameter") ReplySaveForm parameter);
	
	@Select(select 
			+" WHERE R.BOARD_SEQ=#{boardSeq} AND U.USER_SEQ = R.USER_SEQ")
	@ResultMap("ReplyMap")
	List<ReplyVO> getList(@Param("boardSeq") int boardSeq);	
	
	//생성한 댓글만 따로 부를 때를위해 만들어둔 메소드(삭제예정)
	@Select(select
			+" WHERE REPLY_SEQ = #{replySeq}")
	@Results(id = "ReplyMap", value = {
			@Result(property="contents",column="contents"),
			@Result(property="userId",column="user_id")
	})
	ReplyVO getReply(@Param("replySeq")int replySeq);

}
