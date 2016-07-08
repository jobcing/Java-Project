package service;

/********* 페이지 번호에 따른 페이지를 보여주기 위한 클래스 *********/

import java.util.List;

import model.*;

public class NoticePage {
	private int total; // 전체 게시글의 개수
	private int currentPage; // 사용자가 요청한 페이지 번호
	private List<Notice> content; // 화면에 출력할 게시글 목록
	private int totalPage; // 전체 페이지 개수
	private int startPage; // 화면 하단에 보여줄 페이지 이동링크의 시작 번호
	private int endPage; // 끝 번호
	
	public NoticePage(int total, int currentPage, int size, List<Notice> content){
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		if(total == 0){ // 게시글의 개수가 0 개 이면
			totalPage = 0;
			startPage = 0;
			endPage = 0;
		} else{
			totalPage = total / size; // 한 페이지에 보여줄 게시글 개수를 size로 전달받는다.
			if(total % size > 0){
				totalPage++; // 나머지가 남는다면 전체 페이지 수를 하나 증가시켜주어야 한다.
			}
			// 화면 하단에 보여줄 페이지 이동 링크의 시작 페이지 번호를 구한다.
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal == 0)
				startPage -= 5;
			// 화면 하단에 보여줄 페이지 이동 링크의 끝 페이지 번호를 구한다.
			// 5개씩 이동 링크를 출력하므로 시작 번호에서 4를 추가한 값이 페이지 끝 번호
			endPage = startPage + 4;
			if(endPage > totalPage)
				endPage = totalPage;
		}
	}
	
	public int getTotal(){
		return total;
	}
	
	public boolean hasNoNotice(){
		return total == 0;
	}
	
	public boolean hasNotices(){
		return total > 0;
	}
	
	public int getCurrentPage(){
		return currentPage;
	}
	
	public int getTotalPage(){
		return totalPage;
	}
	
	public List<Notice> getContent(){
		return content;
	}
	
	public int getStartPage(){
		return startPage;
	}
	
	public int getEndPage(){
		return endPage;
	}
}
