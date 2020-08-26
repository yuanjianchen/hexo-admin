package tech.stack.hexo.model.vo;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianyuan.chen
 * @date 2020/8/24 16:48
 */
@Data
public class FileTreeVO {
  private Integer id;
  private String text;
  private String icon;
  private NodeState state;
  private List<FileTreeVO> children;

  public FileTreeVO(String text) {
    this.text = text;
  }

  public FileTreeVO() {}

  public FileTreeVO addChild(FileTreeVO vo) {
    if (CollectionUtils.isEmpty(this.children)) {
      this.children = new ArrayList<>();
    }
    this.children.add(vo);
    return this;
  }

  @Data
  public static class NodeState {
    private boolean opened;
    private boolean selected;
    private boolean disabled;
  }

  public void open(){
    if(this.state == null){
      this.state = new NodeState();
    }
    this.state.setOpened(true);
  }
}
