package service;

import model.*;

public class NoticeData {

	private Notice notice;
	private NoticeContent content;
	
	public NoticeData(Notice notice, NoticeContent content){
		this.notice = notice;
		this.content = content;
	}
	
	public Notice getNotice(){
		return notice;
	}
	
	public String getContent(){
		return content.getContent();
	}
}
