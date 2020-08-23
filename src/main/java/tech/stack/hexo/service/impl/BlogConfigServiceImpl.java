package tech.stack.hexo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.core.exception.ResExistedException;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.repository.BlogConfigRepository;
import tech.stack.hexo.service.BlogConfigService;
import tech.stack.hexo.util.ShellUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * @author chenjianyuan
 */
@Service
public class BlogConfigServiceImpl implements BlogConfigService {

    private final BlogConfigRepository repository;

    public BlogConfigServiceImpl(BlogConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public BlogConfig findByBlogPath(String blogPath) {
        return repository.findByFilePath(blogPath).orElse(null);
    }

    @Override
    public void save(BlogConfig blogConfig) {
        Optional<BlogConfig> configOpt = repository.findByFilePath(blogConfig.getFilePath());
        if (configOpt.isPresent() && configOpt.get().getId() != blogConfig.getId()) {
            throw new ResExistedException(blogConfig.getFilePath().concat("已经存在"));
        }
        BlogConfig config = repository.findById(blogConfig.getId()).orElse(blogConfig);
        config.setFilePath(blogConfig.getFilePath());
        config.setRemoteUrl(blogConfig.getRemoteUrl());
        config.setSourcePath(blogConfig.getSourcePath());
        repository.save(config);
    }

    @Override
    public List<BlogConfig> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(int id, String filePath, String remoteUrl) {
        Optional<BlogConfig> configOpt = repository.findByFilePath(filePath);
        if (configOpt.isPresent() && configOpt.get().getId() != id) {
            throw new ResExistedException(filePath.concat("已经存在"));
        }
        BlogConfig config = repository.findById(id).orElse(new BlogConfig());
        config.setId(id);
        config.setFilePath(filePath);
        config.setRemoteUrl(remoteUrl);
        repository.save(config);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void initBlog(int id) {
        BlogConfig config = repository.findById(id).orElseThrow(NoSuchResException::new);
        String filePath = config.getFilePath();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new NoSuchResException(filePath.concat("不存在,请检查博客路径."));
        }
        config.setRemoteUrl(initGitRemoteUrl(filePath));
        config.setSourceRemoteUrl(initGitRemoteUrl(config.getSourcePath()));
        config.setInitialized(true);
        config.setName(filePath.substring(filePath.lastIndexOf("/") + 1));
    }

    private String initGitRemoteUrl(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:script/git-remote.sh");
            List<String> execRes = ShellUtil.execRes(file, filePath);
            if (execRes.isEmpty()) {
                return null;
            }
            return execRes.get(0).split("\\s")[1];
        } catch (IOException e) {
            throw new NoSuchResException(filePath + "不存在,请检查配置.");
        }
    }

}
