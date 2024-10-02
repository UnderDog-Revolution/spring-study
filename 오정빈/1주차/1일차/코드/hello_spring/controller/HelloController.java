package hello.hello_spring.controller;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping ("hello")  // /hello로 요청이 들어오면 아래를 실행
    public String hello(Model model) {
        model.addAttribute("data","hello!!"); // 모델에 data : hello!! 추가
        return "hello"; // template패키지에서 hello.html을 찾아 처리
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // name 값을 받으면 model에 담아 전달
        model.addAttribute("name", name);  // key : name value : name
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 body부분에 data를 직접 넣어줄 때 사용
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // @ResponseBody에 객체를 반환하면 Json으로 반환이 되게 설정되어 있다
    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
