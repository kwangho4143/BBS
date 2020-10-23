package co.kwangho.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kwangho.board.common.Action;
import co.kwangho.board.dao.MemberDao;
import co.kwangho.board.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//로그인 인증과정을 처리한다.
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession(false);
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		
		vo= dao.select(vo); //vo에 실었다가 다시 반환받는 것 MemberDao 를 실행시킨다.
		session.setAttribute("id", vo.getId()); //session에 id를 담음
		session.setAttribute("author", vo.getAuthor());
		session.setAttribute("name", vo.getName()); //로그아웃시에 사용하기 위해서
		
		
		
		request.setAttribute("vo", vo); // 멤버를 실어 보냄   기존의 requset객체에 새로운 메세지를 포함시켜 새로 만들어 보낸다.

		return "jsp/main/loginResult.jsp";
		
	
	}
}
