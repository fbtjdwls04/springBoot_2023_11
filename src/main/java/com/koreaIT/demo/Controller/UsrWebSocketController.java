package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrWebSocketController {
	
	@RequestMapping("usr/chat/chattingRoom")
	public String chatRoom() {
		return "/usr/chat/chattingRoom";
	}
	
}