package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ //Model에 데이터 실어서 뷰로 넘길 수 있다
        model.addAttribute("data", "hello!!!");

        return "hello"; //화면 이름
    }

}
