package tech.stack.hexo.model.vo;

import lombok.Data;
import tech.stack.hexo.core.constant.SourceType;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:52
 */
@Data
public class SourceVO {
    private Integer id;
    private String filePath;
    private String remoteUrl;
    private SourceType type;
    private Boolean initialized;
}
