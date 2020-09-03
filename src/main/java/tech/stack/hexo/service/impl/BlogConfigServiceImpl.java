package tech.stack.hexo.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.core.exception.ResExistedException;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.vo.MenuVO;
import tech.stack.hexo.repository.BlogConfigRepository;
import tech.stack.hexo.service.BlogConfigService;
import tech.stack.hexo.util.ShellUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new NoSuchResException(filePath);
        }
        config.setRemoteUrl(initGitRemoteUrl(filePath));
        config.setSourceRemoteUrl(initGitRemoteUrl(config.getSourcePath()));
        config.setInitialized(true);
        config.setName(filePath.substring(filePath.lastIndexOf("/") + 1));
    }

    @Override
    public List<MenuVO> menus() {
        List<BlogConfig> configs = repository.findAll();
        return configs.stream().map(config -> {
            MenuVO vo = new MenuVO();
            vo.setName(config.getName());
            vo.setUrl("#");
            if (StringUtils.isNotBlank(config.getSourcePath())) {
                vo.addChild(new MenuVO("源文件", "/blog/" + config.getId() + "/source"));
            }
            vo.addChild(new MenuVO("博客配置", "/blog/" + config.getId() + "/config"));
            File file = new File(config.getFilePath() + "/themes");
            File[] files = file.listFiles();
            if (ArrayUtils.isEmpty(files)) {
                return vo;
            }
            assert files != null;
            Arrays.stream(files).filter(theme -> !theme.isHidden() && theme.isDirectory()).forEach(
                    theme -> {
                        MenuVO themeMenu = new MenuVO(theme.getName().replace("hexo-theme-", ""), "#");
                        vo.addChild(themeMenu);
                        themeMenu.addChild(new MenuVO("文章管理", "/blog/" + theme.getName() + "/post"));
                        themeMenu.addChild(new MenuVO("数据中心", "/blog/" + theme.getName() + "/data"));
                        themeMenu.addChild(new MenuVO("主题配置", "/blog/" + theme.getName() + "/config"));
                    });
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public BlogConfig findById(int id) {
        return repository.findById(id).orElseThrow(NoSuchResException::new);
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
            throw new NoSuchResException(filePath);
        }
    }
}
