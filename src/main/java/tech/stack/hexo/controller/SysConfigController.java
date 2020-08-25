package tech.stack.hexo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.Result;
import tech.stack.hexo.model.vo.MenuVO;
import tech.stack.hexo.service.BlogConfigService;

import java.util.List;

/**
 * @author chenjianyuan
 * @date
 */
@RestController
@RequestMapping("/config")
public class SysConfigController {

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
    public Result<List<MenuVO>> menuList() {
        return Result.ok(blogConfigService.menus());
    }

}
