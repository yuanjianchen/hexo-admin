package tech.stack.hexo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.stack.hexo.model.Result;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.ao.SourceAO;
import tech.stack.hexo.model.vo.FileTreeVO;
import tech.stack.hexo.model.vo.SourceVO;
import tech.stack.hexo.service.BlogService;
import tech.stack.hexo.service.SourceService;

import java.util.List;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:49
 */
@RestController
@RequestMapping("/source")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @PostMapping("/save")
    public Result<SourceVO> save(@RequestBody @Validated SourceAO source) {
        return Result.ok(sourceService.save(source));
    }

    @GetMapping("/articles")
    public Result<List<SourceVO>> listArticleSource() {
        return Result.ok(sourceService.listArticleSource());
    }

    @GetMapping("/source/posts")
    public Result<List<FileTreeVO>> files() {
        return Result.ok(sourceService.listPosts());
    }

    @GetMapping("/source/{id}/post")
    public Result<String> fileContent(@PathVariable int id, @RequestParam String fileName) {
        return Result.ok(sourceService.getFileContent(id, fileName));
    }

    @PostMapping("/source/{id}/post")
    public Result<Void> save(@PathVariable int id, FileSourceAO fileSource) {
        sourceService.saveFile(id, fileSource);
        return Result.ok();
    }
}
