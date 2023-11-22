package com.koreaIT.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.demo.vo.RecommendPoint;

@Mapper
public interface RecommendPointDao {

	@Insert("""
			INSERT INTO recommendPoint
				SET memberId = #{memberId}
					, relTypeCode = 'article'
					, relId = #{id}
					, point = 1 
			""")
	public void addRecommend(int id, int memberId);
	
	@Select("""
			SELECT *
				FROM recommendPoint
				WHERE relId = #{id}
				AND memberId = #{memberId}
			""")
	public RecommendPoint getRecommendByMemberId(int id, int memberId);

	@Update("""
			<script>
				UPDATE recommendPoint
					<if test='point == 0'>
						SET `point` = 1 
					</if>
					<if test='point == 1'>
						SET `point` = 0 
					</if>
					WHERE relId = #{id}
					AND memberId = #{memberId}
			</script>
			""")
	public void updateRecommend(int id, int memberId, int point);
}
