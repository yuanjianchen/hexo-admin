package tech.stack.hexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stack.hexo.core.constant.SourceType;
import tech.stack.hexo.domain.Source;
import tech.stack.hexo.model.vo.SourceVO;

import java.util.List;
import java.util.Optional;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:48
 */
@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
    Optional<Source> findByFilePath(String filePath);

    List<Source> findByType(SourceType type);
}
