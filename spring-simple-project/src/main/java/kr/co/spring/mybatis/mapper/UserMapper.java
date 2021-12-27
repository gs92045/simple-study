package kr.co.spring.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	
	
	@Select("SELECT USER_SEQ FROM USER WHERE USER_ID = #{userId}")
	public int userGetById(@Param("userId") String userId);

}
