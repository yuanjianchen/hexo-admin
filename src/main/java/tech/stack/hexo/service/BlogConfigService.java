package tech.stack.hexo.service;

import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.vo.MenuVO;

import java.util.List;

/**
 * @author chenjianyuan
 */
public interface BlogConfigService {
    /**
     * 根据博客路径查询
     *
     * @param blogPath 博客路径
     * @return 博客配置
     */
    BlogConfig findByBlogPath(String blogPath);

    /**
     * 保存新的博客
     *
     * @param blogConfig 博客路径
     */
    void save(BlogConfig blogConfig);

    List<BlogConfig> findAll();

    void save(int id, String filePath, String remoteUrl);

    void initBlog(int id);

    List<MenuVO> menus();

    BlogConfig findById(int id);
}
