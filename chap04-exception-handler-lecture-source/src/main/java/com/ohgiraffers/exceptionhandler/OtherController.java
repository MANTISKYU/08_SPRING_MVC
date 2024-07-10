package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {


    @GetMapping("other-controller-user")
    public String userExceptionTest() throws MemberRegistException {

        boolean check = true;

        if(check) {
            throw new MemberRegistException("당신같은 사람은 안돼");
        }
        return "/";


    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public String nullPointRxceptionHandler(NullPointerException exception) {
//
//        System.out.println("controller 레벨의 exception 처리");
//        return "error/nullPointer";
//
//    }

    @GetMapping("other-controller-null")
    public String nullPointExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";

    }


    @GetMapping("other-controller-array")
    public String otherArrayExceptiontest() {

        Double[] array = new Double[1];
        System.out.println(array[3]);

        return "/";

    }





}
