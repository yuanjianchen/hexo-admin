package tech.stack.hexo.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.stack.hexo.core.constant.SourceType;
import tech.stack.hexo.core.exception.AdminException;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.core.exception.ResExistedException;
import tech.stack.hexo.domain.Source;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.ao.SourceAO;
import tech.stack.hexo.model.vo.FileTree;
import tech.stack.hexo.model.vo.SourceVO;
import tech.stack.hexo.repository.SourceRepository;
import tech.stack.hexo.service.SourceService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
    public List<FileTree> listArticles() {
        List<FileTree> trees = sourceRepository.findByType(SourceType.ARTICLE).stream()
                .map(source -> FileTree.makeTree(new File(source.getFilePath()))).collect(Collectors.toList());
        if (trees.size() == 1) {
            trees.get(0).open();
        }
        return trees;
    }

    @Override
    public String getFileContent(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new NoSuchResException(fileName);
        }
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AdminException(e);
        }
    }

    @Override
    public void saveFile(FileSourceAO fileSource) {
        File file = new File(fileSource.getFilename());
        try {
            FileUtils.writeStringToFile(file, fileSource.getMd(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new AdminException(e);
        }
    }

    @Override
    public FileTree initFolder(String parentPath) {
        String filePath = parentPath + "/" + FileTree.DEFAULT_FOLDER_NAME;
        File file = new File(filePath);
        if (file.exists()) {
            throw new ResExistedException(filePath);
        }
        boolean mkdir = file.mkdir();
        FileTree tree = new FileTree(file.getName());
        tree.setFilePath(filePath);
        return tree;
    }

    private Source get(Integer id) {
        return sourceRepository.findById(id).orElseThrow(NoSuchResException::new);
    }
}
