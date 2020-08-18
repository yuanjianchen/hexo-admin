package tech.stack.hexo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jianyuan.chen
 * @date 2020/8/18 16:03
 */
@Controller
public class ViewController {

  @GetMapping("index")
  public String index() {
    return "index";
  }

  @GetMapping("home")
  public String home() {
    return "home";
  }

  @GetMapping("config")
  public String config(){
    return "config";
  }
}
