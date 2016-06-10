package model;

public class NoticeContent {

	private Integer number; // Integer는 클래스. null값 처리 가능.
	private String content;
	
	public NoticeContent(Integer number, String content){
		this.number = number;
		this.content = content;
	}
	
	public Integer getNumber(){
		return number;
	}
	
	public String getContent(){
		return content;
	}
}
