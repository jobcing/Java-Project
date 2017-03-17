package org.itner.domain;

public class TimetableVO {
	
	private String cinemaTitle;
	private String[] movie;
	private MovieTimeVO[] movietimeVO;
	
	public void setCinemaTitle(String cinemaTitle){
		this.cinemaTitle = cinemaTitle;
	}
	
	public void setMovie(String[] movie){
		this.movie = movie;
	}
	
	public void setMovieTimeVO(MovieTimeVO[] movietimeVO){
		this.movietimeVO = movietimeVO;
	}
	
	public String getCinemaTitle(){
		return this.cinemaTitle;
	}
	
	public String[] getMovie(){
		return this.movie;
	}
	
	public MovieTimeVO[] getMovietimeVO(){
		return this.movietimeVO;
	}
}
