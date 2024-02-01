package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.vo.PracVO;

@Controller
public class PracController {

    @GetMapping("/introduce")
    public String pracMain() {return "reqPrac";}

    @GetMapping("/introduce/{name}")
    public String response1(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "resPrac";
    }

    @GetMapping("/introduce2")
    public String response2(@RequestParam String name,
                            @RequestParam String age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "resPrac";
    }

    @PostMapping("/practice/register")
    @ResponseBody
    public String pracPost(@RequestBody PracVO pracVO) {
        return pracVO.getName() + " 회원가입 성공!";
    }
}
