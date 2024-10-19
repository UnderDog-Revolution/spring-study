package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 만들어두었던 멤버 서비스 가져옴.
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm"; // 위 주소로 라우팅이 오면 바로 제작폼 html로 연결함.
    }
    @PostMapping("/members/new")
    // 멤버 폼에서 정보들을 입력한 객체를 가져옴.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); // 이걸로 정보를 딱 넘김(회원가입 완료).

        return "redirect:/"; // node와는 다른 리디렉션.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // 멤버를 전부 찾아 모델에 담아 넘김.
        return "members/memberList";
    }
}