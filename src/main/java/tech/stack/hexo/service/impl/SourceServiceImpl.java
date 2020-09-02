package tech.stack.hexo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.stack.hexo.core.constant.SourceType;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.core.exception.ResExistedException;
import tech.stack.hexo.domain.Source;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.ao.SourceAO;
import tech.stack.hexo.model.vo.FileTreeVO;
import tech.stack.hexo.model.vo.SourceVO;
import tech.stack.hexo.repository.SourceRepository;
import tech.stack.hexo.service.SourceService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:55
 */
@Service
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Override
    public SourceVO save(SourceAO ao) {
        Optional<Source> sourceOpt = sourceRepository.findByFilePath(ao.getFilePath());
        if (sourceOpt.isPresent() && !sourceOpt.get().getId().equals(ao.getId())) {
            throw new ResExistedException(ao.getFilePath());
        }
        Source source = sourceOpt.orElse(sourceRepository.findById(ao.getId()).orElseGet(Source::new));
        BeanUtils.copyProperties(ao, source);
        sourceRepository.save(source);
        SourceVO vo = new SourceVO();
        BeanUtils.copyProperties(source, vo);
        return vo;
    }

    @Override
    public List<SourceVO> listArticleSource() {
        return sourceRepository.findByType(SourceType.ARTICLE).stream().map(source -> {
            SourceVO vo = new SourceVO();
            BeanUtils.copyProperties(source, vo);
            return vo;
        }).collect(Collectors.toList());

    }

    @Override
    public List<FileTreeVO> listPosts() {

        return null;
    }

    @Override
    public String getFileContent(int id, String fileName) {
        return null;
    }

    @Override
    public void saveFile(int id, FileSourceAO fileSource) {

    }

    private Source get(Integer id) {
        return sourceRepository.findById(id).orElseThrow(NoSuchResException::new);
    }
}
