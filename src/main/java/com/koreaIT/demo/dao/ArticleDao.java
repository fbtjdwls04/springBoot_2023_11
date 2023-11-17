package com.koreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleDao {

	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					,updateDate = NOW()
					,memberId = #{memberId}
					,title = #{title}
					,`body` = #{body}
			""")
	public void writeArticle(int memberId, String title, String body);
	
	@Select("""
			SELECT a.*, m.name AS writerName
				FROM article AS a
			 	INNER JOIN member AS m
			 	ON a.memberId = m.id
			 	WHERE a.boardId = #{boardId}
				ORDER BY a.id DESC
			""")
	public List<Article> getArticles(int boardId);
	
	@Select("""
			SELECT *
				FROM article 
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Select("""
			SELECT a.*, m.name AS writerName
				FROM article AS a
			 	INNER JOIN member AS m
			 	ON a.memberId = m.id
				WHERE a.id = #{id}
			""")
	public Article forPrintArticle(int id);
	
	@Select("""
			DELETE FROM article 
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);
	
	@Select("""
			<script>
				UPDATE article 
					SET updateDate = NOW() 
					<if test="title != null and title != ''">
						,title = #{title}
					</if>
					<if test="body != null and body != ''">
						,`body` = #{body}
					</if>
					WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
}
