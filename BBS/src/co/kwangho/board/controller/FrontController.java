package co.kwangho.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kwangho.board.command.LoginAction;
import co.kwangho.board.command.LoginForm;
import co.kwangho.board.command.LogoutAction;
import co.kwangho.board.command.MainAction;
import co.kwangho.board.command.MemberForm;
import co.kwangho.board.command.MemberInsertAction;
import co.kwangho.board.command.MemberListAction;
import co.kwangho.board.common.Action;

//쿠키는 request객체가 아니라 response객체에 담는다.
//session에는 특정 유지해야하는 것들 로그인 등등 을 담는다.

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Action> map = new HashMap<String,Action>();
	
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		//요청들을 정의함
		map.put("/main.do",new MainAction()); //main.do 가 들어오면 MainAction()를 실행하는것   index.jsp가 들어오면 사용하는 것 처음페이지 처리시 사용
		map.put("/login.do",new LoginAction()); //로그인 메뉴를 처리하는 것
		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
		map.put("/memberList.do",new MemberListAction()); //회원 전체 리스트 보기
		map.put("/memberForm.do",new MemberForm()); //회원가입 화면 호출
		map.put("/memberInsert.do",new MemberInsertAction()); //회원입력 
		map.put("/logout.do",new LogoutAction()); //로그인 폼 호출
//		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
//		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
//		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
//		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
//		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
//		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
//		
	}
	//doget,dopost를 사용해도 되지만 간단히 하기 위해서
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수행할 명령을 처리
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length()); //실제 들어오는 요청 페이지를 찾음
		//uri = "/BBS/main.do"    contextPath = "/BBS"    => path = "/main.do"
		
		
		Action command = map.get(path);//키를 /main.do로 하고 MainAction()을 가져오는것
		String viewPage = command.exec(request, response); //명령어 수행되고 나서 보여줄 페이지 선택  //view페이지를 찾는다

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //선택한 페이지로 가는 것 
		dispatcher.forward(request, response);
		
	
	
	
	}

}
