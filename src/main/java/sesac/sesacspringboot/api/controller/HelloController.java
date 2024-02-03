package sesac.sesacspringboot.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
// @Controller: 해당 클래스가 Controller의 역할을 하는 클래스라는 것을 Spring Container에게 알려준다.

public class HelloController {
    @GetMapping("/hi")
    // URL을 매핑시켜주는 어노테이션
    // 클라이언트가 /hi라는 경로로 GET method로 접근하는 경우, 아래 메소드를 실행시켜라.

    public String getHi (Model model) {
        // Model : Controller 안의 메서드가 파라미터로 받을 수 있는 객체 중 하나
        // Model 안에 정보를 담아서 view로 전달
        // IoC : 개발자가 직접 모델을 생성하지 않음. 받아서 사용할 뿐

        model.addAttribute("name", "홍길동");
        //name이라는 키로 "홍길동"이라는 value를 보냄

        model.addAttribute("key", "값");

        model.addAttribute("name2", "<strong>강조강조</strong>");

        model.addAttribute("age", 10);

        ArrayList<String> names = new ArrayList<>();
        names.add("kim");
        names.add("hong");
        names.add("park");

        model.addAttribute("item", names);

        return "hi"; // 템플릿 파일의 이름, 이 이름을 가진 템플릿 파일을 불러온다.
        // res.render("hi")
        // res.render("hi", {"홍길동"}) -> X
    }
}
