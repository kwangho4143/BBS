package co.kwangho.board.common;

//인터페이스는 사용할 원형만 선언해주면 된다
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public String exec(HttpServletRequest request,HttpServletResponse response);
}


