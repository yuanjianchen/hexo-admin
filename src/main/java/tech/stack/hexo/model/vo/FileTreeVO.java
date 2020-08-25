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
  private String text;
  private List<Integer> tags;
  private String icon;
  private NodeState state = new NodeState();
  private List<FileTreeVO> nodes;

  public FileTreeVO(String text) {
    this.text = text;
  }

  public FileTreeVO() {}

  public FileTreeVO addNode(FileTreeVO vo) {
    if (CollectionUtils.isEmpty(this.nodes)) {
      this.nodes = new ArrayList<>();
    }
    this.nodes.add(vo);
    return this;
  }

  @Data
  public static class NodeState {
    private boolean checked;
    private boolean disabled;
    private boolean expanded;
    private boolean selected;
  }
}
