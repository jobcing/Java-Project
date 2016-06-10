// 모든 명령어 핸들러는 동일한 인터페이스를 상속받아서 구현이 되는데
// CommandHandler가 그 역할을 수행할 공통 인터페이스가 된다.

package mvc;

import javax.servlet.http.*;

public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
