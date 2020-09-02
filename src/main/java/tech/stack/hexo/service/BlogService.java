package tech.stack.hexo.service;

import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.vo.FileTreeVO;

import java.util.List;

/**
 * @author jianyuan.chen
 * @date 2020/8/24 17:31
 */
public interface BlogService {
    List<FileTreeVO> listPosts(int id);

    String getFileContent(int id, String fileName);

    void saveFile(int id, FileSourceAO fileSource);
}
