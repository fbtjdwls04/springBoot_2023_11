package com.koreaIT.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
	private int id = 1;
	private String title;
	private String body;
}
