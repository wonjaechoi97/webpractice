# Servlet
> - 자바 서블릿은 자바를 사용하여 웹 페이지를 동적으로 생성하는 서버측 프로그램 혹은 그 사양을 말한다.
> - 자바 서블릿은 웹 서버의 성능을 향상하기 위해 사용되는 자바 클래스의 일종이다. 
> - JSP가 HTML문서 안에 Java코드를 포함하고 있는 반면, 서블릿은 자바 코드 안에 HTML을 포함하고 있다.

### Servlet API
> -  자바 서블릿을 사용하기 위해서는 Servlet 인터페이스를 상속한 GenericServlet을 상속한 HttpServlet을 상속 받은 클래스를 만들어 사용한다. 

### Servlet Life Cycle
> - 클라이언트가 서버에 요청을 하게 되면 Servlet container가 서블릿 객체를 한번 생성한다.
> - init() 메서드를 호출하여 최초에 한 번 초기화를 진행한다.
> - 매 요청시 마다 요청에 대한 service() 메소드를 호출하는데 요청 방식이 post이면 doPost() get이면 doGet을 호출해서 요청을 처리한다.
> - 서블릿 객체가 필요 없게 되면 destroy() 메서드를 호출해서 제거한다.
