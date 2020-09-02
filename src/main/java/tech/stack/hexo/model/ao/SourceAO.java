package tech.stack.hexo.model.ao;

import lombok.Data;
import tech.stack.hexo.core.constant.SourceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 15:02
 */
@Data
public class SourceAO {
    private int id;
    @NotBlank
    private String filePath;
    private String remoteUrl;
    @NotNull
    private SourceType type;
}
