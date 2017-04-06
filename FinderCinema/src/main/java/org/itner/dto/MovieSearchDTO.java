package org.itner.dto;

/*
 * 화면에서 전달되는 데이터를 수집하는 용도.
 * 사용자가 원하는 영화와 원하는 시간대를 받아온다.
 */

public class MovieSearchDTO {

	private String movieTitle;
	private String movieTime1;
	private String movieTime2;
	
	public void setMovieTitle(String movieTitle){
		this.movieTitle = movieTitle;
	}
	
	public void setMovieTime1(String movieTime1){
		this.movieTime1 = movieTime1;
	}
	
	public void setMovieTime2(String movieTime2){
		this.movieTime2 = movieTime2;
	}
	
	public String getMovieTitle(){
		return this.movieTitle;
	}
	
	public String getMovieTime1(){
		return this.movieTime1;
	}
	
	public String getMovieTime2(){
		return this.movieTime2;
	}
}
