package tech.stack.hexo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.stack.hexo.model.Result;
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

  @GetMapping("/{id}/source/post")
  public Result<List<FileTreeVO>> files(@PathVariable int id) {
    return Result.ok(blogService.listPosts(id));
  }

  @GetMapping("{id}/source/post/{fileName}")
  public Result<String> fileContent(@PathVariable int id, @PathVariable String fileName) {
      return Result.ok(blogService.getFileContent(id, fileName));
  }
}
