package service;

/********* 사용자가 작성한 게시글 데이터를 담기 위한 클래스 *********/

import java.util.Map;

import model.*;

public class WriteRequest {
	
	private Writer writer;
	private String title;
	private String content;
	
	public WriteRequest(Writer writer, String title, String content){
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	public Writer getWriter(){
		return writer;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getContent(){
		return content;
	}
	
	public void vaildate(Map<String, Boolean> errors){
		if(title == null || title.trim().isEmpty()){
			errors.put("title", Boolean.TRUE);
		}
	}
}
