package com.koreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.Reply;

@Mapper
public interface ReplyDao {

	@Insert("""
			INSERT INTO reply
				SET memberId = #{memberId},
				relTypeCode = #{relTypeCode},
				relId = #{relId},
				regDate = NOW(),
				updateDate = NOW(),    
				`body` = #{body};
			""")
	public void doWrite(int relId, String relTypeCode,int memberId, String body);

	
	@Select("""
			SELECT m.name AS writerName, r.*
				FROM reply AS r
				INNER JOIN member AS m
				ON r.memberId = m.id
				WHERE r.relId = #{id}
				AND r.relTypeCode = #{relTypeCode}
			""")
	public List<Reply> getReplies(int id, String relTypeCode);
	
}
