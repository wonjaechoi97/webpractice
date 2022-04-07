# 상태 유지를 필요로 하는 경우에 사용하는 것들
요청 후 다음 요청을 할 때에 전에 입력했던 값을 저장해 놓기 위한 전역 변수 같은 것들이 필요하다. 

### 이 때 사용하는 것들이 
> - application 
> - session
> - cookie


### Application 객체를 사용해서 값 저장하기 

---
``` java
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.ApplicationBufferHandler;

/**
 * Servlet implementation class Add
 */
@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//***************************************
		//request 객체를 사용하여 ServletContext 인터페이스 타입의 application객체 생성 
		ServletContext application = request.getServletContext();//!!!!!!!!!!!!!
		//**************************************
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v=0;
		
		if(!v_.equals("")) v=Integer.parseInt(v_);
		
		//계산
		if(op.equals("계산")) {
			////**************************************
			//getAttribute setAttribute를 값 넣고 빼오기 
			int x = (Integer)application.getAttribute("value");
			int y = v;
			//**************************************
			String operator = (String)application.getAttribute("op");
			int result = 0;
			if(operator.equals("+"))result = x+y;
			else result = x-y;		
			response.getWriter().printf("result is %d\n", result);
			
		}//값 저장
		else {
			application.setAttribute("value", v);
			application.setAttribute("op", op);
		}
		
	
	}

}
```
### Session 객체를 사용해서 값 저장하기 

---
``` java
HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v=0;
		
		if(!v_.equals("")) v=Integer.parseInt(v_);
		
		//계산
		if(op.equals("계산")) {
			////**************************************
			//getAttribute setAttribute를 값 넣고 빼오기 
			//int x = (Integer)application.getAttribute("value");
			int x = (Integer)session.getAttribute("value");
			int y = v;
			//**************************************
			//String operator = (String)application.getAttribute("op");
			String operator = (String)session.getAttribute("op");
			int result = 0;
			if(operator.equals("+"))result = x+y;
			else result = x-y;		
			response.getWriter().printf("result is %d\n", result);
			
		}//값 저장
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			session.setAttribute("value", v);
			session.setAttribute("op", op);
		}
		
```

### Application vs Session
> - Application 객체는 Application 전역에서 사용할 수 있다. 브라우저를 바꾸어도 전에 담아둔 값을 사용할 수 있다.
> - Session은 현재 접송한 사용자 공간이고 Session 객체는 세션 범주 내에서 사용할 수 있다.
> - 세션은 웹 브라우저가 달라지면 다른 사용자로 인식하여 크롬에서 저장한 값을 엣지에서 사용할 수 없다. 
