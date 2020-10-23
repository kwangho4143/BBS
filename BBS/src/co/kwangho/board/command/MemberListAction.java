package co.kwangho.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kwangho.board.common.Action;
import co.kwangho.board.dao.MemberDao;
import co.kwangho.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		list = dao.selectAll();
		request.setAttribute("members", list);
		
		
		return "jsp/member/memberList.jsp";
	}

}
