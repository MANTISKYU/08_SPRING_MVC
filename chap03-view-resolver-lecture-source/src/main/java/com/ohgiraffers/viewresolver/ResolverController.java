package com.ohgiraffers.viewresolver;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model) {

        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환");

        return "result";


    }

    @GetMapping("string-redirect")
    public String stringRedirect() {

        System.out.println("리다이렉트 호출됨");

        return "redirect:/";

    }

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {


        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");

        return "redirect:/";
    }

    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {

        /*
        * 모델과 뷰를 합친 개념
        * */

        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");

        mv.setViewName("result");

        return mv;


    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {

        mv.setViewName("redirect:/");
        return mv;

    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirect(ModelAndView mv, RedirectAttributes rttr) {

        rttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 redirect attr");

        mv.setViewName("redirect:/");

        return mv;

    }

}
