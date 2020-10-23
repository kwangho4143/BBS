package co.kwangho.board.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kwangho.board.common.Action;
import co.kwangho.board.dao.MemberDao;
import co.kwangho.board.vo.MemberVO;

public class MemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();

		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));
		int n = dao.insert(vo);
		String page;

		if (n != 0) {
			page = "jsp/member/insertSucess.jsp";
		} else {
			page = "jsp/member/insertFail.jsp";
		}

		return page;
	}

}
