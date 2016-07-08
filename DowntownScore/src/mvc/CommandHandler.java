package mvc;

/********* 모든 명령어 핸들러는 동일한 인터페이스를 상속받아서 구현 *********/

import javax.servlet.http.*;

public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
