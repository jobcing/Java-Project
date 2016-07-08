package mvc;

/********* 카페 평점 리스트를 보여줄 때 사용되는 핸들러 *********/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CafePage;
import service.CafeService;

public class ListCafeHandler implements CommandHandler {

	private static final String FORM_VIEW = "./listCafe.jsp";
	private CafeService cafeService = new CafeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo"); // 페이지 번호를 받는다.
		String station = req.getParameter("station"); // 역 이름을 받는다.
		String name = req.getParameter("name"); // 상호명을 받는다.

		// 만약 페이지번호, 역이름, 상호명 모두 전달받지 않았을 때
		// ( 메뉴를 눌렀을 때)
		if (pageNoVal == null && station == null && name == null)
			return FORM_VIEW;

		int pageNo = 1;

		if (pageNoVal != null) { // 페이지 번호를 받았을 때
			pageNo = Integer.parseInt(pageNoVal);
		}

		// 해당 페이지와 상호명에 해당하는 페이지를 불러온다.
		// 검색어를 입력하지 않거나 검색어에 해당하는 데이터가 존재하지 않는다면 예외페이지를 리턴해야한다.
		// 평점 평균과 사용자 평점 리스트를 보여주는 폼을 리턴해야한다.
		CafePage cafePage = null;
		double scoreAvg = 0;

		if (name.equalsIgnoreCase("") || name == null) { // 만약 사용자가 상호명을 입력하지 않았다면
			cafePage = cafeService.getCafePage(pageNo, station);
			scoreAvg = cafeService.getAvg(station);
		} else {
			cafePage = cafeService.getCafePage(pageNo, station, name);
			scoreAvg = cafeService.getAvg(station, name);
		}

		// 구한 평점 평균을 소수점 2자리까지만 표시한다.
		scoreAvg = Double.parseDouble(String.format("%.2f", scoreAvg));

		req.setAttribute("cafePage", cafePage);
		req.setAttribute("scoreAvg", scoreAvg);
		req.setAttribute("station", station);
		req.setAttribute("name", name);

		return "./listCafe.jsp";
	}
}
