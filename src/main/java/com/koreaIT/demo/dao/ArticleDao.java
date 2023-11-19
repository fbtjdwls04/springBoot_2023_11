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
					,boardId = #{boardId}
					,title = #{title}
					,`body` = #{body}
			""")
	public void writeArticle(int memberId, int boardId, String title, String body);
	
	@Select("""
			SELECT a.*, m.name AS writerName
				FROM article AS a
			 	INNER JOIN member AS m
			 	ON a.memberId = m.id
			 	WHERE a.boardId = #{boardId}
				ORDER BY a.id DESC
			 	LIMIT #{boardPage},10
			""")
	public List<Article> getArticles(int boardId, int boardPage);
	
	@Select("""
			SELECT a.*, m.name AS writerName
				FROM article AS a
			 	INNER JOIN member AS m
			 	ON a.memberId = m.id
			 	WHERE a.boardId = #{boardId}
			 	AND a.title LIKE CONCAT('%',#{searchMsg},'%')
				ORDER BY a.id DESC
			 	LIMIT #{boardPage},10
			""")
	public List<Article> getSearchArticles(int boardId, int boardPage, String searchMsg);
	
	@Select("""
			SELECT COUNT(*)
			FROM article
			WHERE boardId = #{boardId}
		""")
	public int getArticleCntById(int boardId);
	
	@Select("""
			SELECT COUNT(*)
			FROM article
			WHERE boardId = #{boardId}
			AND title LIKE CONCAT('%',#{searchMsg},'%')
		""")
	public int getSearchArticlesCntById(int boardId, String searchMsg);
	
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

	@Select("""
				SELECT MAX(id)
				FROM article
			""")
	public int getLastArticleId();

}
