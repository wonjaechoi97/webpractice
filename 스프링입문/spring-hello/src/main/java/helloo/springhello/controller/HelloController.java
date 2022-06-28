package helloo.springhello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello로 요청이 들어오면 아래 메서드 호출
    public String hello(Model model){ //스프링이 model 만들어서 넣어줌
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http 응답 바디 부에 밑의 데이터를 직접 넣어 주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; //"hello spirng"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{
        private String name;
        //alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
