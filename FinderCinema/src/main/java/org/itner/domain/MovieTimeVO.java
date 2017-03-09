package org.itner.domain;

public class MovieTimeVO {
	
	private String movie;
	private String[] time;
	
	public void setMovie(String movie){
		this.movie = movie;
	}
	
	public void setTime(String[] time){
		this.time = time;
	}
	
	public String getMovie(){
		return this.movie;
	}

	public String[] getTime(){
		return this.time;
	}
}
