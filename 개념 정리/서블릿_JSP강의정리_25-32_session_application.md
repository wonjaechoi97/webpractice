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


### Session
> - 클라이언트가 브라우저를 통해서 WAS에게 요청을 하면 처음 요청을 수행하는 경우 Session ID가 없기 때문에 서버에는 새로운 사용자를 위한 Session공간이 존재하지 않는다.
> - 요청 수행 후 돌아갈 때 서버에서 클라이언트에게 쿠키를 통해서 Session ID를 부여하고 그 사용자를 위한 Session 공간이 만들어진다.
> - 브라우저가 달라지거나 브라우저를 닫았다가 다시 여는 경우에는 새로운 사용자로 인식하여 처음 요청하는 것과 마찬가지로 Session ID를 가지고 있지 않다.

### Cookie
> - 상태값을 서버가 가지고 있는 것이 아니라 클라이언트 컴퓨터 내에 저장하고 들고 다닌다. 
> - 쿠키로 저장되는 것은 반드시 문자열이어야 한다. 
> - response의 헤더에 심어진 형태로 클라이언트에게 전달된다. 


### Cookie 사용 예

---
``` java
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//***************************************
		/*
		 * application 객체는 application 전역에서 사용할 수 있고
		 * 세션은 세션 범주 내에서 사용할 수 있다. -->현재 접속한 사용자 =세션 
		 * 사용자 별로 공간이 달라질 수 있다. 
		 * 웹 브라우저가 달라지면 다른 사용자로 인식하여 세션이 달라진다. 
		 */
		
		//request 객체를 사용하여 ServletContext 인터페이스 타입의 application객체 생성 
		//ServletContext application = request.getServletContext();//!!!!!!!!!!!!!
		//**************************************
		
		//HttpSession session = request.getSession();
		//사용자가 보내준 쿠키 읽어 들이기 
		Cookie[] cookies = request.getCookies();
		
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
			
			//int x = (Integer)session.getAttribute("value");
			int x=0;
			
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			String operator="";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			
			int y = v;
			//**************************************
			//String operator = (String)application.getAttribute("op");
			//String operator = (String)session.getAttribute("op");
			int result = 0;
			if(operator.equals("+"))result = x+y;
			else result = x-y;		
			response.getWriter().printf("result is %d\n", result);
			
		}//값 저장
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);
			
			//쿠키 두 개 제작 
			Cookie valuecookie = new Cookie("value", String.valueOf(v));
			Cookie opcookie = new Cookie("op",op);
			
			//클라이언트에게 보내기
			response.addCookie(valuecookie);
			response.addCookie(opcookie);
		}
		
	
	}
```

### Cookie 옵션 설정 
> - 쿠키를 불러올 때 url과 관련된 서블릿에게만 값이 전달될 수 있도록 하여 쿠키 이름 중복이나 여러 비효울 개선.

---
``` java
			Cookie valuecookie = new Cookie("value", String.valueOf(v));
			Cookie opcookie = new Cookie("op",op);
			
			//쿠키가 어느 경우에 사용자로부터 전달 되어야 하는지에 대한 경로 
			// "/" 모든 페이지 요청할 때마다 쿠키를 가져와라
			// "add/" add라는 uri를 요청해야만 쿠키가 전달됨.
			// localhost:8080/add를 요청하면 쿠키가 전달됨.
			
			// "/notice/" notice안의 모든 경로를 요청하면 가능 
			valuecookie.setPath("/calc2");
			opcookie.setPath("/calc2");
			//클라이언트에게 보내기
			response.addCookie(valuecookie);
			response.addCookie(opcookie);
``` 
> - maxAge 옵션 
>	- maxAge를 따로 설정하지 않으면 쿠키의 생존주기는 브라우저의 생존주기와 같다.
>	- 설정해주면 그 값을 유지 사용자 로컬 디렉토리에 저장되어 설정한 기간 만큼 유지한다. 
> ![만료](https://user-images.githubusercontent.com/62707891/163419301-3252fa37-55ca-480c-9acb-19842127e208.png)

> ### 사용법
 ```java
 // "/notice/" notice안의 모든 경로를 요청하면 가능 
			valuecookie.setPath("/calc2");
			valuecookie.setMaxAge(60*60*24);//초 단위 ==하루
			opcookie.setPath("/calc2");
			//클라이언트에게 보내기
			response.addCookie(valuecookie);
			response.addCookie(opcookie);
```
>


