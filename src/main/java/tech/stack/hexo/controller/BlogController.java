package tech.stack.hexo.controller;

import org.springframework.web.bind.annotation.*;
import tech.stack.hexo.model.Result;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.vo.FileTree;
import tech.stack.hexo.service.BlogService;

import java.util.List;

/**
 * @author jianyuan.chen
 * @date 2020/8/24 15:47
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
}
