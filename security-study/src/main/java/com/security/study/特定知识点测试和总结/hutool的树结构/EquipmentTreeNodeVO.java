package com.security.study.特定知识点测试和总结.hutool的树结构;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备-节点树实体展示类
 */
@NoArgsConstructor
@Data
public class EquipmentTreeNodeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<EquipmentTreeNodeVO> children = new ArrayList<>();

    /**
     * sid
     */
    private String sid;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 节点层级
     */
    private String nodeLevel;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     * 从根节点到当前节点的路径，以/分隔id表示
     */
    private String path;
    /**
     * 从根节点到当前节点的路径的名称，以/分隔name表示
     */
    private String pathName;
    /**
     * 是否叶子节点标记:1是0否
     */
    private Integer leafTag;


    public EquipmentTreeNodeVO(String sid, String nodeName, String nodeLevel, Integer sort, String parentId, String path, String pathName, Integer leafTag) {
        this.sid = sid;
        this.nodeName = nodeName;
        this.sort = sort;
        this.nodeLevel = nodeLevel;
        this.parentId = parentId;
        this.path = path;
        this.pathName = pathName;
        this.leafTag = leafTag;
    }

}