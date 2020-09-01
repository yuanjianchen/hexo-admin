package tech.stack.hexo.controller;

import org.springframework.web.bind.annotation.*;
import tech.stack.hexo.model.Result;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.vo.FileTreeVO;
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

    @GetMapping("/{id}/source/posts")
    public Result<List<FileTreeVO>> files(@PathVariable int id) {
        return Result.ok(blogService.listPosts(id));
    }

    @GetMapping("{id}/source/post")
    public Result<String> fileContent(@PathVariable int id, @RequestParam String fileName) {
        return Result.ok(blogService.getFileContent(id, fileName));
    }

    @PostMapping("{id}/source/post")
    public Result<Void> save(@PathVariable int id, @RequestBody FileSourceAO fileSource) {
        blogService.saveFile(fileSource);
        return Result.ok();
    }
}
