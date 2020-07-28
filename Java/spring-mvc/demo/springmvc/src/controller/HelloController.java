package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController{
    @RequestMapping("/hello")
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }
    @RequestMapping("/jump")
    public ModelAndView jump() {
        return new ModelAndView("redirect:/hello");
    }
    @RequestMapping("/test")
    public ModelAndView handleTest() {
        return new ModelAndView("test");
    }
    @RequestMapping("/param")
    public ModelAndView getParam(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println(userName);
        System.out.println(password);
        return null;
    }
    @RequestMapping("/value")
    public ModelAndView handleRequest(HttpServletRequest request) {
        request.setAttribute("message","成功！");
        return new ModelAndView("test2");
    }
    @RequestMapping("/value1")
    public ModelAndView handleRequest1() {
        ModelAndView mav = new ModelAndView("test2");
        mav.addObject("message", "成功-");
        return mav;
    }
    @RequestMapping("/value2")
    public String handleRequest2(Model model) {
        model.addAttribute("message", "成功~");
        return "test2";
    }
}
