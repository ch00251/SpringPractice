package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fortune/showfortune")
public class Showfortune extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//응답 한글 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		//응답 content type 설정(html 페이지를 응답하겠다고 알리기)
		resp.setContentType("text/html;charset=utf-8");
		
		//요청한 클라이언트에게 문자열을 출력할 수 있는 객체(console이 아니라)
		PrintWriter pw=resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='usf-8'/>");
		pw.println("<title>오늘의 운세 페이지</title>");
		pw.println("<link rel='stylesheet' href='/Step01_Servlet/resources/css/bootstrap.css'/>");
		pw.println("</head>");
		pw.println("<body>");
			pw.println("<div class='container'>");
			pw.println("<a class='btn btn-primary' href='../index.html'>인덱스로 가기</a>");
		pw.println("<p>오늘의 운세</p>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
