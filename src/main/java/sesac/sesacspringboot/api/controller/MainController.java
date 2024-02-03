package sesac.sesacspringboot.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.api.dto.UserDTO;
import sesac.sesacspringboot.api.vo.UserVO;

@Controller
//@RestController // @Controller + @ResponseBody -> 모든 응답이 데이터를 보내는 경우엔 이 어노테이션을 사용하고 @ResponseBody 별도로 안 붙여도 됨
public class MainController {
    @GetMapping("/")
    public String getMain() { return "request";}

    // === GET ===
    // 매개변수를 넘겨받는 방법
    // 1. /test?id=123 -> @RequestParam
    // 2. /test/123 -> @PathVariable
    @GetMapping("/get/response1")
    // ?key=value 형태로 들어오는 값을 받을 땐 @RequestParam 을 사용
    // ?name= 으로 온 값을 받겠다.
    // @RequestParam 은 기본값으로 required=true를 갖고 있다. -> 요청 url에서 설정한 key가 필수로 있어야 함
    public String getResponse1(@RequestParam(value="name") String i, Model model){
        // @RequestParam 어노테이션
        // - ?name=112&id=11&age=abc
        // - String query( ? 뒤의 값) 에서 key("name")에 대한 value("112")를 변수("i")에 매핑
        model.addAttribute("name", i);
        return "response";
    }

    // 요청이 false일 때를 별도로 설정해 처리
    @GetMapping("/get/response2")
    // required=false 옵션 (@RequestParam(value="", required=false) )
    // - query string에서 특정 key를 옵셔널하게 받아야 하는 경우 사용
    // 예를 들어 ?search=검색어
    // ?search=검색어(필수)&hashtag=코딩(선택) 으로도 검색이 가능해야 할 경우
    public String getResponse2(
            @RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
                return "response";
    }

    @GetMapping("/get/response3/{param1}/{param2}")
    // 변수를 url 안에 넣어 보낼 때 @PathVariable 사용
    // @PathVariable 어노테이션
    // - /test/{id} 형식의 url 경로로 데이터를 넘겨줄 때 받는 방법
    // - 기본적으로 경로 변수의 값을 필수로 받아야 하기 때문 (보내지 않으면 404 에러 발생함)
    public String getResponse3(@PathVariable String param1, // 받은 변수를 그대로 사용할 때
                               @PathVariable(value = "param2") String age, // 변수명을 원하는 이름으로 따로 설정하고 싶을 때
                               Model model) {
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "response";
    }

    // pathVariable을 보낼 때 선택적으로 처리해야 한다면
    // GetMapping에 옵셔널한 파라미터가 있는 경우/없는 경우 두 가지 경로를 모두 적어주어야 함
    @GetMapping({"/get/response4/{param1}", "/get/response4/{param1}/{param2}"})
    public String getResponse4(@PathVariable String param1,
                               @PathVariable(required = false, value = "param2") String age,
                               Model model) {
        // 중요! optinal한 parameter는 맨 뒤에 오도록 설정해야 한다!(앞에 두면 파라미터가 있고 없고에 따라 순서가 바뀌게 되기 때문)
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "response";
    }

    // post 방식 - @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(
            @RequestParam(value = "name") String a,
            @RequestParam(value = "age") String b,
            Model model
    ) {
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    }

    @PostMapping("/post/response2")
    public String postResponse2(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value = "age", required = false) String b,
            Model model
    ) {
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    }

    // @ResponseBody :
    // - 응답 시 객체를 직렬화해서 (== json 형태로) 리턴한다.
    // express 의 res.send 와 유사
    @PostMapping("/post/response3")
    @ResponseBody
    public String postResponse3(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value = "age", required = false) String b,
            Model model
    ) {
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        // return "response"; // 템플릿을 불러오는 게 아니라 응답 결과가 됨
        return a + " - " + b;
    }

    @GetMapping("/dto/response1")
    @ResponseBody
    // @ModelAttribute 안 붙여도 spring에서 알아서 붙여서 실행함
    // @ModelAttribute : html 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑해 준다.
    // 매핑한다. = setter 함수를 실행한다.
    // 예를 들면, ?name=홍길동&age=10 -> setName("홍길동") setAge("10")
    public String dtoResponse1(@ModelAttribute UserDTO userDTO) {
        // DTO : getter와 setter가 있는 객체
        // Get 요청에 담긴 값이 DTO 객체로 받아진다.
        return userDTO.getName() + " " + userDTO.getAge();
    }
    
    // @RequestBody : 요청의 본문에 있는 데이터(body)를 받는 어노테이션
    @GetMapping("/dto/response11")
    @ResponseBody
    public String dtoResponse11(@RequestBody UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getAge();
    }

    // 일반 폼 전송 (get, post 모두 해당) -> www-x-form-urlencoded 형태로 데이터가 간다. -> 쿼리 매개변수
    // 따라서 일반 폼 전송으로 보내면 requestBody로 값을 가져올 수 없다. (json, xml 일 때만 처리 가능)
    // axios, ajax 요청은 application/json 형태로 간다.

    // 일반 폼 전송 - DTO(getter, setter 모두 존재)
    // 1) 어노테이션 없이 DTO로 받을 경우 -> O
    // 2) @ModelAttribute DTO 받을 경우 -> O
    // 3) @RequestBody DTO로 받을 경우 -> X (오류 발생)

    // 일반 폼 전송 - VO
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO) {
        return userVO.getName() + " " + userVO.getAge();
    } // null (setter가 없기 때문)

    @PostMapping("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVO userVO) {
        return userVO.getName() + " " + userVO.getAge();
    } // null

    @PostMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO) {
        return userVO.getName() + " " + userVO.getAge();
    } // error

    /////////////=== axios를 이용한 데이터 처리 ===/////////////////

    // 1. axios - get - @RequestParam -> O
    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age) {
        return name + " " + age;
    }

    // 2. axios - get - @ModelAttribute -> O
    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getAge();
    }

    @PostMapping("/axios/response3")
    @ResponseBody
    // axios post는 url에 데이터가 없음. url에 아무것도 없는데 name, age가 필수값(require=true)이기 때문에 에러가 발생
    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        return "이름: " + name + ", 나이: " + age;
    }

    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosRes4(UserDTO userDTO){
        return "이름:" + userDTO.getName() + ", 나이: " + userDTO.getAge(); //@ModelAttribute로 보냈을 땐 값이 null로 들어감
        // axios로 보내면 url로 데이터를 보내는 게 아니라 본문으로 데이터를 보내게 된다.
        // 따라서 @ModelAttrubute가 값을 읽어 낼 수 없음
    }

    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDTO userDTO){
        return "이름:" + userDTO.getName() + ", 나이: " + userDTO.getAge();
    } // axios + post 데이터 -> @RequestBody 정상 실행 O

    // ========== VO 이용 with. axios ==========
    @GetMapping("/axios/vo/response1")
    @ResponseBody
    public String axiosVoRes1(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosVoRes2(UserVO userVO) {
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response3")
    @ResponseBody
    public String axiosVoRes3(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosVoRes4(UserVO userVO){
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosVoRes5(@RequestBody UserVO userVO){
        // axios post로 데이터를 보내면, 요청의 본문에 데이터가 들어간다.
        // @RequestBody는 요청의 본문에 있는 데이터를 읽을 수 있다.
        // UserVO 클래스는 setter 메소드가 없다.
        // @RequestBody는 데이터 각각의 필드(변수)에 직접 값을 주입함
        // 따라서 UserVO, UserDTO 여부와 관계없이, setter 메소드의 유무와 관계없이 변수에 값을 넣을 수 있다.
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }
}
