package tech.stack.hexo.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.vo.FileTreeVO;
import tech.stack.hexo.service.BlogConfigService;
import tech.stack.hexo.service.BlogService;

import java.io.File;
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
    BlogConfig config = blogConfigService.findById(id).orElseThrow(NoSuchResException::new);
    String sourcePath = config.getSourcePath();
    File file = new File(sourcePath);
    return listPosts(file);
  }

  private List<FileTreeVO> listPosts(File file) {
    File[] files = file.listFiles();
    if (ArrayUtils.isEmpty(files)) {
      return Collections.emptyList();
    }
    return Arrays.stream(files)
        .filter(f -> !f.isHidden())
        .map(
            f -> {
              FileTreeVO vo = new FileTreeVO(f.getName());
              if (f.isDirectory()) {
                vo.setNodes(listPosts(f));
                vo.setTags(
                    new ArrayList<Integer>() {
                      {
                        File[] children = f.listFiles();
                        add(children == null ? 0 : children.length);
                      }
                    });
              } else {
                vo.setIcon("glyphicon glyphicon-file");
              }
              return vo;
            })
        .collect(Collectors.toList());
  }
}
