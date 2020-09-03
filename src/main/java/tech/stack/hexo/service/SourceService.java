package tech.stack.hexo.service;

import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.ao.SourceAO;
import tech.stack.hexo.model.vo.FileTree;
import tech.stack.hexo.model.vo.SourceVO;

import java.util.List;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:54
 */
public interface SourceService {

    SourceVO save(SourceAO source);


    List<SourceVO> listArticleSource();

    List<FileTree> listArticles();

    String getFileContent(String fileName);

    void saveFile(FileSourceAO fileSource);

    FileTree initFolder(String parentPath);
}
