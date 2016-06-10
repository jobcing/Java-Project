package model;

import java.util.Date;

public class Notice {
	
	private Integer number;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modDate;
	private int readCount;
	
	public Notice(Integer number, Writer writer, String title, Date regDate, Date modDate, int readCount){
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modDate = modDate;
		this.readCount = readCount;
	}
	
	public Integer getNumber(){
		return number;
	}
	
	public Writer getWriter(){
		return writer;
	}
	
	public String getTitle(){
		return title;
	}
	
	public Date getRegDate(){
		return regDate;
	}
	
	public Date getModDate(){
		return modDate;
	}
	
	public int getReadCount(){
		return readCount;
	}
}
