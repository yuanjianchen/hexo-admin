package tech.stack.hexo.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import tech.stack.hexo.core.exception.AdminException;
import tech.stack.hexo.core.exception.NoSuchResException;
import tech.stack.hexo.domain.BlogConfig;
import tech.stack.hexo.model.ao.FileSourceAO;
import tech.stack.hexo.model.vo.FileTree;
import tech.stack.hexo.service.BlogConfigService;
import tech.stack.hexo.service.BlogService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianyuan.chen
 * @date 2020/8/24 17:31
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogConfigService blogConfigService;

    public BlogServiceImpl(BlogConfigService blogConfigService) {
        this.blogConfigService = blogConfigService;
    }
}
