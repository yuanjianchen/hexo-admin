package tech.stack.hexo.model.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianyuan.chen
 * @date 2020/8/24 16:48
 */
@Data
public class FileTree {
    private static final String FOLDER_OPEN_ICON = "fa fa-folder-open-o";
    private static final String FOLDER_ICON = "fa fa-folder-o";
    private static final String FILE_ICON = "fa fa-file-o";
    public static final String DEFAULT_FOLDER_NAME = "new Folder";
    public static final String DEFAULT_FILE_NAME = "new File";

    private Integer id;
    private String text;
    private String icon;
    private String filePath;
    private NodeState state;
    private List<FileTree> children;

    public FileTree(String text) {
        this.text = text;
    }

    public FileTree() {
    }

    public FileTree addChild(FileTree vo) {
        if (CollectionUtils.isEmpty(this.children)) {
            this.children = new ArrayList<>();
        }
        this.children.add(vo);
        return this;
    }

    public void open() {
        if (this.state == null) {
            this.state = new NodeState();
        }
        this.state.setOpened(true);
//        this.icon = FOLDER_OPEN_ICON;
    }

    @Data
    public static class NodeState {
        private boolean opened;
        private boolean selected;
        private boolean disabled;
    }

    public static FileTree makeTree(File file) {
        FileTree tree = new FileTree(file.getName());
        tree.setFilePath(file.getAbsolutePath());
        if (!file.exists()) {
            return tree;
        }
        tree.setIcon(FILE_ICON);
        if (file.isDirectory()) {
            tree.setIcon(FOLDER_ICON);
        }
        tree.setChildren(listChildren(file));
        return tree;
    }

    private static List<FileTree> listChildren(File file) {
        File[] files = file.listFiles();
        if (ArrayUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return Arrays.stream(files).filter(f -> !f.isHidden()).map(f -> {
            FileTree vo = new FileTree(f.getName());
            vo.setFilePath(f.getAbsolutePath());
            if (f.isDirectory()) {
                vo.setChildren(listChildren(f));
                vo.setIcon(FOLDER_ICON);
            } else {
                vo.setIcon(FILE_ICON);
            }
            return vo;
        }).collect(Collectors.toList());
    }
}
