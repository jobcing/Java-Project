package service;

import java.util.List;

import model.*;

public class BarPage {
	
	private int total;
	private int currentPage;
	private List<Bar> content;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public BarPage(int total, int currentPage, int size, List<Bar> content){
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		if(total == 0){
			totalPage = 0;
			startPage = 0;
			endPage = 0;
		} else{
			totalPage = total / size;
			if(total % size > 0){
				totalPage++;
			}
			
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
	
	public List<Bar> getContent(){
		return content;
	}
	
	public int getStartPage(){
		return startPage;
	}
	
	public int getEndPage(){
		return endPage;
	}
}
