package tech.stack.hexo.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import tech.stack.hexo.core.exception.AdminException;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.vo.FileTreeVO;
import tech.stack.hexo.service.BlogConfigService;
import tech.stack.hexo.service.BlogService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianyuan.chen
 * @date 2020/8/24 17:31
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogConfigService blogConfigService;

    public BlogServiceImpl(BlogConfigService blogConfigService) {
        this.blogConfigService = blogConfigService;
    }

    @Override
    public List<FileTreeVO> listPosts(int id) {
        BlogConfig config = blogConfigService.findById(id);
        String sourcePath = config.getSourcePath();
        File file = new File(sourcePath);
        FileTreeVO vo = new FileTreeVO(file.getName());
        vo.setIcon("fa fa-folder-open-o");
        vo.setChildren(listPosts(file));
        vo.open();
        return new ArrayList<FileTreeVO>() {{
            add(vo);
        }};
    }

    @Override
    public String getFileContent(int id, String fileName) {
        BlogConfig blogConfig = blogConfigService.findById(id);
        String sourcePath = blogConfig.getSourcePath();
        String filePath = sourcePath.substring(0, sourcePath.lastIndexOf("/") + 1).concat(fileName);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new NoSuchResException(fileName + " 文件不存在.");
        }
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AdminException(e);
        }
    }

    @Override
    public void saveFile(int id, FileSourceAO fileSource) {
        BlogConfig config = blogConfigService.findById(id);
        String sourcePath = config.getSourcePath();
        String filePath = sourcePath.substring(0, sourcePath.lastIndexOf("/") + 1).concat(fileSource.getFilename());
        File file = new File(filePath);
        try {
            FileUtils.write(file, fileSource.getMd(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AdminException(e);
        }
    }

    private List<FileTreeVO> listPosts(File file) {
        File[] files = file.listFiles();
        if (ArrayUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return Arrays.stream(files).filter(f -> !f.isHidden()).map(f -> {
            FileTreeVO vo = new FileTreeVO(f.getName());
            if (f.isDirectory()) {
                vo.setChildren(listPosts(f));
                vo.setIcon("fa fa-folder-o");
            } else {
                vo.setIcon("fa fa-file-o");
            }
            return vo;
        }).collect(Collectors.toList());
    }
}
