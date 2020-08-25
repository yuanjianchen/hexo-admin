package tech.stack.hexo.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjianyuan
 * @date 2020/8/23 15:47:23
 */
@Data
public class MenuVO {
    private String name;
    private String url;
    private List<MenuVO> children;

    public MenuVO() {
        this.children = new ArrayList<>();
    }

    public MenuVO(String name, String url) {
        this.name = name;
        this.url = url;
        this.children = new ArrayList<>();
    }

    public MenuVO addChild(MenuVO vo){
        this.getChildren().add(vo);
        return this;
    }
}
