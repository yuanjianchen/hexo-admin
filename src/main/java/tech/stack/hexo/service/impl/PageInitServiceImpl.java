package tech.stack.hexo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.service.BlogConfigService;
import tech.stack.hexo.service.PageInitService;

import java.util.List;

/**
 * @author chenjianyuan
 * @date 2020/8/22 11:19:07
 */
@Service
public class PageInitServiceImpl implements PageInitService {
    @Autowired
    private BlogConfigService blogConfigService;

    @Override
    public BlogConfig initConfigPage() {
        List<BlogConfig> configs = blogConfigService.findAll();
        if (configs.size() > 0) {
            return configs.get(0);
        }
        return new BlogConfig();
    }
}
