package tech.stack.hexo.controller;

import org.springframework.stereotype.Controller;

import java.util.Collections;

/**
 * @author jianyuan.chen
 * @date 2020/8/18 16:31
 */
@Controller
public class HomeController {

    public static void test(){
        if (!Collections.emptyList().isEmpty()) {
            System.out.println(1);
        }
    }
}
