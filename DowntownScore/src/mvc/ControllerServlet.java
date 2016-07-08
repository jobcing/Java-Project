package mvc;

/********* 사용자 요청을 받아서 수행 내용을 결정하는 서블릿 클래스 *********/

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerServlet extends HttpServlet {
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	// init() 메서드는 서블릿을 생성하고 초기화할 때 호출되는 메서드
	public void init() throws ServletException{
		String configFile = getInitParameter("configFile"); // 초기화 파라미터 정보를 가져온다.
		Properties prop = new Properties();
		// confingFile 값을 이용해서 설정 파일 경로를 구한다.
		String configFilePath = getServletContext().getRealPath(configFile);
		
		try(FileInputStream fis = new FileInputStream(configFilePath)){
			prop.load(fis);
		} catch(IOException e){
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator(); // Iterator를 사용하기 위해 프로퍼티에서 메서드를 사용하여 얻어옴
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			String handlerClassName = prop.getProperty(command); // 키에 해당하는 값을 가져온다.
			try{
				// CommandHandler 인터페이스를 상속받은 클래스 생성
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				// 생성한 클래스와 명령어를 HashMap에 삽입
				commandHandlerMap.put(command, handlerInstance);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}
	
	// Http 요청을 받는다. (GET, POST 방식 둘 다)
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		process(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		process(request, response);
	
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		// 요청 파악 (request객체로부터 사용자의 요청을 파악하는 코드)
		// URL를 명령어로 사용한다.
		String command = request.getRequestURI();
		if(command.indexOf(request.getContextPath()) == 0){
			command = command.substring(request.getContextPath().length());
		}
		// HaspMap에 저장되있는 CommandHandler 상속 받은 클래스들 중 해당 command 키에 해당하는 클래스를 가져와서
		// handler 에 저장한다.
		CommandHandler handler = commandHandlerMap.get(command);
		
		if(handler == null){
			handler = new NullHandler();
		}
		
		String viewPage = null; // cmd를 받아서 뷰 페이지를 지정할 경우
		
		try{
			viewPage = handler.process(request, response);
		} catch(Exception e){
			throw new ServletException(e);
		}
		
		// process()메소드에서 리턴된 뷰페이지를 이용
		if(viewPage != null){
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
