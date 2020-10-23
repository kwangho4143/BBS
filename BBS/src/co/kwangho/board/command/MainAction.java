package co.kwangho.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kwangho.board.common.Action;

public class MainAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/main/main.jsp";
	}

}
