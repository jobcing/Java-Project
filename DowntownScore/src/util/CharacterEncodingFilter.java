package util;

/********** 모든 jsp페이지에서 캐릭터 인코딩을 하기위해 사용되는 클래스 **********/
// jsp페이지는 필터를 거쳐서 실행된다.

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
