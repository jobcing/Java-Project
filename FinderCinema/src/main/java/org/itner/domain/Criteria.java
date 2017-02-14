package org.itner.domain;

/*
 * 페이지 번호와 검색키워드, 검색 타입을 관리하는 도메인 클래스
 */

public class Criteria {

	private int page; // 현재 페이지
	private int perPageNum = 10; // 한 페이지당 보여지는 게시글의 개수
	private String searchType; // 검색 유형
	private String keyword; // 검색어
	
	public Criteria(){
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page){
		if(page <= 0){
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setSearchType(String searchType){
		this.searchType = searchType;
	}
	
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	public int getPage(){
		return this.page;
	}
	
	public String getSearchType(){
		return this.searchType;
	}
	
	public String getKeyword(){
		return this.keyword;
	}
	
	// method for MyBatis SQL Mapper-
	public int getPageStart(){
		return (this.page - 1) * perPageNum;
	}
	
	// method for MyBatis SQL Mapper-
	public int getPerPageNum(){
		return this.perPageNum;
	}
}
