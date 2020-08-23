package tech.stack.hexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stack.hexo.domain.BlogConfig;

import java.util.Optional;

/**
 * @author chenjianyuan
 * @date 2020/8/21 23:22:13
 */
@Repository
public interface BlogConfigRepository extends JpaRepository<BlogConfig,Integer> {
    /**
     * 根据博客地址查询
     * @param blogPath 博客路径
     * @return 博客配置
     */
    Optional<BlogConfig> findByFilePath(String blogPath);
}
