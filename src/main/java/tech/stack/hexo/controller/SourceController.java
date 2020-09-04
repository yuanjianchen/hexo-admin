package tech.stack.hexo.controller;

import com.sun.jna.platform.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.stack.hexo.core.exception.AdminException;
import tech.stack.hexo.model.Result;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.ao.SourceAO;
import tech.stack.hexo.model.vo.FileTree;
import tech.stack.hexo.model.vo.SourceVO;
import tech.stack.hexo.service.SourceService;

import java.io.File;
import java.io.IOException;
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

    @GetMapping("/article/config")
    public Result<List<SourceVO>> listArticleSource() {
        return Result.ok(sourceService.listArticleSource());
    }

    @GetMapping("/articles")
    public Result<List<FileTree>> files() {
        return Result.ok(sourceService.listArticles());
    }

    @GetMapping("/article")
    public Result<String> fileContent(@RequestParam String fileName) {
        return Result.ok(sourceService.getFileContent(fileName));
    }

    @PostMapping("/init/folder")
    public Result<FileTree> initFolder(String parentPath){
        return Result.ok(sourceService.initFolder(parentPath));
    }

    @PostMapping("/init/file")
    public Result<FileTree> initFile(String parentPath){
        return Result.ok(sourceService.initFile(parentPath));
    }


    @PostMapping("/file/rename")
    public Result<Void> renameFile(String oldFile, String newFile){
        sourceService.renameFile(oldFile, newFile);
        return Result.ok();
    }
    @PostMapping("/file/trace")
    public Result<Void> moveTrace(String file){
        try {
            FileUtils.getInstance().moveToTrash(new File(file));
        } catch (IOException e) {
            throw new AdminException(e);
        }
        return Result.ok();
    }

    @PostMapping("/article")
    public Result<Void> save(FileSourceAO fileSource) {
        sourceService.saveFile(fileSource);
        return Result.ok();
    }
}
