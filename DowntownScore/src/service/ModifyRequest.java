package service;

import java.util.Map;

public class ModifyRequest {
	
	private String member_id;
	private int noticeNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String member_id, int noticeNumber, String title, String content){
		this.member_id = member_id;
		this.noticeNumber = noticeNumber;
		this.title = title;
		this.content = content;
	}
	
	public String getId(){
		return member_id;
	}
	
	public int getNoticeNumber(){
		return noticeNumber;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getContent(){
		return content;
	}
	
	public void validate(Map<String, Boolean> errors){
		if(title == null || title.trim().isEmpty()){
			errors.put("title", Boolean.TRUE);
		}
	}
}
