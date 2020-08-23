package tech.stack.hexo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.Result;
import tech.stack.hexo.service.BlogConfigService;

/**
 * @author chenjianyuan
 * @date
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private BlogConfigService blogConfigService;

    @PostMapping("save")
    public Result<Void> save(@RequestBody BlogConfig blogConfig) {
        blogConfigService.save(blogConfig);
        return Result.ok();
    }

    @PostMapping("init")
    public Result<Void> init(int id) {
        blogConfigService.initBlog(id);
        return Result.ok();
    }

    @GetMapping("menus")
    public Result<Void> menuList() {

        return Result.ok();
    }

}
