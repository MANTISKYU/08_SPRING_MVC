package com.ohgiraffers.fileupload;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
@Slf4j
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String defaultLocation() {

        String name = "이득규";
        String job = "야호야호";

        System.out.println("메인페이지로 이동합니다");

        log.info("저는 {}이구요 {}이라고 합니다.", job, name);

        // 파일 저장 공간 지정

        //인텔리제이 root 폴더


        return "main";
    }

}