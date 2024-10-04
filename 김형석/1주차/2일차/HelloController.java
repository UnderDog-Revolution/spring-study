package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        /* 키는 data이고, 값은 hello이다 */
        model.addAttribute("data", "hello :)");

        /* 깃허브 커밋 테스트 */
        /* templates의 hello.html을 찾아 렌더링하라는 뜻 */
        return "hello";
    }
    @GetMapping("hello-mvc")
    /* url로부터 받은 정보를 name에 입력하고 최종적으로 model에 담아 html에서 사용한다. (리액트 props느낌) */
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);

        return "hello-template";
    }
}