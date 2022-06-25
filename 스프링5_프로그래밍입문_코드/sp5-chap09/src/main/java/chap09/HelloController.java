package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //mvc 스프링에서 controller로 사용 
public class HelloController {
	
	@GetMapping("/hello")//메서드가 처리할 요청 경로 지정  /hello 경로로 들어오 요청은 hello()가 처리 한다고 설정, HTTP 요청 메서드 중에서 GET 메서드에 대한 매핑을 설정한다.
	public String hello(Model model, @RequestParam(value = "name", required = false)String name) { //model은 컨트롤러의 처리 결과를 뷰에 전달할 때 사용  
		model.addAttribute("greeting", "안녕하세요, "+name); //@RequestParam는 HTTP 요청 파라미터의 값을 메서드의 파라미터로 전달할 때 사용 name 요청 파라미터의 값을 name 파라미터에 전달
		return "hello";//..컨트롤러의 처리 결과를 보여줄 뷰 이름을 "hello"
	}
}
