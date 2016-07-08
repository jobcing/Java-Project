package service;

/********* 게시글, 게시글 내용의 데이터를 담고있는 클래스 *********/

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
