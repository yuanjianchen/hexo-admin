package tech.stack.hexo.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tech.stack.hexo.core.constant.SourceType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jianyuan.chen
 * @date 2020/9/2 14:39
 */
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filePath;
    private String remoteUrl;
    private SourceType type;
    private Boolean initialized;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
}
