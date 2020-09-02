package tech.stack.hexo.service;

import tech.stack.hexo.domain.Source;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.ao.SourceAO;
import tech.stack.hexo.model.vo.FileTreeVO;
import tech.stack.hexo.model.vo.SourceVO;

import java.util.List;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:54
 */
public interface SourceService {

    SourceVO save(SourceAO source);


    List<SourceVO> listArticleSource();

    List<FileTreeVO> listPosts();

    String getFileContent(int id, String fileName);

    void saveFile(int id, FileSourceAO fileSource);
}
