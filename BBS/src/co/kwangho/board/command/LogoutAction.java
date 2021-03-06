package co.kwangho.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kwangho.board.common.Action;

public class LogoutAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//로그아웃 처리
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		session.invalidate();
		request.setAttribute("name", name);
		return "jsp/main/logout.jsp";
	}

}
