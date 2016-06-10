// 요청 파라미터를 모든 jsp페이지에서 캐릭터 인코딩하기 위해
// 필터를 설정해서 중복 코드를 없애기 위한 클래스

package util;

import java.io.IOException;

import javax.servlet.*;

// 필터 인터페이스를 상속받는다.
public class CharacterEncodingFilter implements Filter {
	
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException{
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		encoding = config.getInitParameter("encoding");
		if(encoding == null){
			encoding = "UTF-8";
		}
	}
	
	@Override
	public void destroy(){
		
	}
}
