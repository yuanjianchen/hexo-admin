package tech.stack.hexo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tech.stack.hexo.service.PageInitService;

/**
 * @author jianyuan.chen
 * @date 2020/8/18 16:03
 */
@Controller
public class ViewController {

    private final PageInitService pageInitService;

    public ViewController(PageInitService pageInitService) {
        this.pageInitService = pageInitService;
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @GetMapping("config")
    public String config(Model model) {
        model.addAttribute("blogConfig", pageInitService.initConfigPage());
        return "config";
    }

    @GetMapping("articles")
    public String articles() {
        return "articleManager";
    }

    @GetMapping("/source/article")
    public String blogSource() {
        return "mdSource";
    }
}
