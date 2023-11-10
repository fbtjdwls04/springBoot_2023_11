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
					,title = #{title}
					,body = #{body}
			""")
	public void writeArticle(String title, String body);
	
	@Select("""
			SELECT * FROM article
				ORDER BY id DESC
			""")
	public List<Article> getArticles();
	
	@Select("""
			SELECT * FROM article 
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Select("""
			DELETE FROM article 
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);
	
	@Select("""
			UPDATE article 
				SET title = #{title}
				,body = #{body}
				WHERE id = #{id}
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
}
