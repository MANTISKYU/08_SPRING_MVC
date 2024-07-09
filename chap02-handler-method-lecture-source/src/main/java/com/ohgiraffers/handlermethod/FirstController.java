package com.ohgiraffers.handlermethod;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")
public class FirstController {

    /*
    * 컨트롤러 핸들러 메소드의 반환 값을 void로 설정하면 요청 주소가 view 이름이 된다.
    * => /first/regist 요청이 들어오면 /first/regist/ 뷰를 응답한다
    * */

    // 페이지 이동용 핸들러 메소드
    @GetMapping("regist")
    public void regist() {}

    /*
    * WebRequest로 요청 파라미터 전달 받기
    * 파라미터 선언부에 WebRequest 타입을 선언하면 해당 메소드 호출 시 인자로 값을 전달해준다.
    * HttpServletRequest, HttpServletResponse / servletRequest, ServletResponse 가능하다
    * */

    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        System.out.println("categoryCode = " + categoryCode);
        System.out.println("name = " + name);
        System.out.println("price = " + price);

        String message = name + "을 신규 메뉴 목록의" + categoryCode + "번 카테고리에" +
                price + "원으로 등록 하셨습니다";
        System.out.println("message = " + message);

        model.addAttribute("message", message);


        return "first/messagePrinter";
    }

    /*
    * @RequestParam으로 요청 파라미터 전달 받기
    * 요청 파라미터를 매핑해 호출 시 값을 넣어주는 어토네이션으로 매개변수 앞에 작성한다.
    * form 의 name 속성값과 매개변수의 이름이 다른 경우 @RequestParam("이름") 설정하면 된다.
    * */

    @GetMapping("modify")
    public void modify() {}

    @PostMapping("modify")
    public String modifyMenuPrice(Model model, @RequestParam(required = false) String modifyName,
                                  @RequestParam(defaultValue = "0") int modifyPrice) {


        String message = modifyName + "메뉴의 가격을" + modifyPrice + "원으로 가격을 변경하였습니다";
        System.out.println("message = " + message);

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    /*
    * 파라미터가 여러개인 경우 Map으로 한번에 처리할 수 있다.
    * 이때, Map의 키는 form의 name 속성값이 된다.
    * */
    @PostMapping("modifyAll")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters) {

        String modifyName = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = modifyName + "메뉴의 가격을" + modifyPrice + "원으로 변경하였습니다";
        System.out.println("message" + message);

        model.addAttribute("message", message);

        return "first/messagePrinter";

    }

    //first/search
    @GetMapping("search")
    public void search() {}

    @PostMapping("search")
    public String searchMenu(@ModelAttribute MenuDTO menu) {

        System.out.println("menu = " + menu);

        return "first/searchResult";

    }

    @GetMapping("login")
    public void login() {}


    /*
    * 4-1 session 이용하기
    * HttpSession을 매개변수로 선언하면 핸들러 메소드 호출 시 세션 객체를 넣어서 호출한다.
    * */
    @PostMapping("login1")
    public String sessionTest1(HttpSession session, @RequestParam String id, @RequestParam String pwd) {

        System.out.println("session = " + session);
        System.out.println("id = " + id);

        session.setAttribute("id", id);
        return "first/loginResult";


    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {

        session.invalidate();
        return "first/loginResult";


        }

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {

        model.addAttribute("id", id);
        return "first/loginResult";


    }

    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus) {


        sessionStatus.setComplete();

        return "first/loginResult";

    }


    @GetMapping("body")
    public void body() {}

    @PostMapping("body")
    public void bodyTest(@RequestBody String body,
                         @RequestHeader("content-type") String contentType,
                         @CookieValue(value = "JSESSIONID", required = false) String sessionId) throws UnsupportedEncodingException {


        System.out.println("contentType = " + contentType);
        System.out.println("sessionId = " + sessionId);
        System.out.println("body = " + body);
        System.out.println("body " + URLDecoder.decode(body, "UTF-8"));

    }




}
