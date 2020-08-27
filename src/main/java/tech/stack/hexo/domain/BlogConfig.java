package tech.stack.hexo.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author chenjianyuan
 * @date 2020/8/21 23:12:48
 */
@Data
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BlogConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String filePath;
    private String sourcePath;
    private String sourceRemoteUrl;
    private String remoteUrl;
    private boolean initialized;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;

    public BlogConfig() {
    }

    public BlogConfig(String filePath, String remoteUrl) {
        this.filePath = filePath;
        this.remoteUrl = remoteUrl;
    }
}
