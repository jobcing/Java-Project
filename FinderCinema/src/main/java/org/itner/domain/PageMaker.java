package org.itner.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * 페이징 처리 계산을 위한 클래스
 */

public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev; // [이전] 버튼이 필요한지 필요없는지
	private boolean next; // [다음] 버튼이 필요한지 필요없는지
	
	private int displayPageNum = 10; // 페이지 당 보여지는 게시글 개수
	
	private Criteria cri; // 현재 페이지 번호를 관리하고 있는 도메인 클래스
	
	public void setCri(Criteria cri){
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
		
		calcData(); // startPage, endPage, prev, next 계산 해주는 메소드
	}
	
	private void calcData(){
		// Math.ceil : 올림
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		// 만약 총 데이터 개수가 displayPageNum 배수로 안떨어진다면 데이터 개수가 있는데 까지만
		// 페이지 번호를 표시하게 한다.
		int tempEndPage = (int) (Math.ceil(totalCount / (double) displayPageNum));
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		// 현재 startPage가 1이 맞다면 false, 아니라면 true를 저장
		prev = startPage == 1 ? false : true;
		
		// (현재 보여지는 끝 페이지 번호 * 한 페이지에 보여지는 게시글의 개수) 가
		// 전체 게시글 개수보다 많다면 [다음] 버튼이 생성되어야 하므로 true를 저장. 아니라면 false
		next = endPage * displayPageNum >= totalCount ? false : true;
	}
	
	// 복잡한 URI를 메소드를 통해 설정
	public String makeURI(int page){
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("searchType", cri.getSearchType())
				.queryParam("keyword", cri.getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public void setStartPage(int startPage){
		this.startPage = startPage;
	}
	
	public void setEndPage(int endPage){
		this.endPage = endPage;
	}
	
	public void setPrev(boolean prev){
		this.prev = prev;
	}
	
	public void setNext(boolean next){
		this.next = next;
	}
	
	public Criteria getCri(){
		return this.cri;
	}
	
	public int getTotalCount(){
		return this.totalCount;
	}
	
	public int getStartPage(){
		return this.startPage;
	}
	
	public int getEndPage(){
		return this.endPage;
	}
	
	public boolean getPrev(){
		return this.prev;
	}
	
	public boolean getNext(){
		return this.next;
	}
}
