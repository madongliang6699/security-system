package com.security.study.特定知识点测试和总结.hutool的树结构;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HutoolTreeUtilTest {

    public static void main(String[] args) {

        List<EquipmentTreeNodeVO> list = new ArrayList<>();

        list.add(new EquipmentTreeNodeVO("1", "顶级节点1", "顶层", 2, "0", "1", "顶级节点1", 0));
        list.add(new EquipmentTreeNodeVO("11", "二级节点11", "第二层", 2, "1", "1/11", "顶级节点1/二级节点11", 0));
        list.add(new EquipmentTreeNodeVO("111", "三级节点111", "第三层", 1, "11", "1/11/111", "顶级节点1/二级节点11/三级节点111", 1));
        list.add(new EquipmentTreeNodeVO("12", "二级节点12", "第二层", 1,"1", "1/12", "顶级节点1/二级节点12", 1));
        list.add(new EquipmentTreeNodeVO("2", "顶级节点2", "顶层", 1, "0", "2", "顶级节点2", 1));


        List<Tree<String>> nodeLevel = TreeUtil.build(list, "0", (vo, tree) -> {
            tree.setId(vo.getSid());
            tree.setName(vo.getNodeName());
            tree.setParentId(vo.getParentId());
            tree.setWeight(vo.getSort());//权重,做同级节点排序用的
            //tree.setChildren(new ArrayList<>());//这里如果想让叶子节点也有children属性的话,就提前设置一个空集合
            tree.put("nodeLevel", vo.getNodeLevel());
            tree.put("nodeName", vo.getNodeName());
            tree.put("sort", vo.getSort());
            tree.put("path", vo.getPath());
            tree.put("pathName", vo.getPathName());
            tree.put("leafTag", vo.getLeafTag());
            tree.put("sid", vo.getSid());
        });


        System.out.println(JSON.toJSONString(nodeLevel));//[{"id":"2","name":"顶级节点2","parentId":"0","weight":1,"nodeLevel":"顶层","sort":1,"path":"2","pathName":"顶级节点2","leafTag":1,"sid":"2"},{"id":"1","name":"顶级节点1","parentId":"0","weight":2,"nodeLevel":"顶层","sort":2,"path":"1","pathName":"顶级节点1","leafTag":0,"sid":"1","children":[{"id":"12","name":"二级节点12","parentId":"1","weight":1,"nodeLevel":"第二层","sort":1,"path":"1/12","pathName":"顶级节点1/二级节点12","leafTag":1,"sid":"12"},{"id":"11","name":"二级节点11","parentId":"1","weight":2,"nodeLevel":"第二层","sort":2,"path":"1/11","pathName":"顶级节点1/二级节点11","leafTag":0,"sid":"11","children":[{"id":"111","name":"三级节点111","parentId":"11","weight":1,"nodeLevel":"第三层","sort":1,"path":"1/11/111","pathName":"顶级节点1/二级节点11/三级节点111","leafTag":1,"sid":"111"}]}]}]

        Tree<String> stringTree = nodeLevel.get(0);
        Tree<String> node = stringTree.getNode("11");
        System.out.println("node:"+node);//null

        stringTree = nodeLevel.get(1);
        node = stringTree.getNode("111");//getNode()方法 根据子孙节点的id获取该子孙节点,如果有多个ID相同的节点，只返回第一个, 如果传的id是本身的则返回本身, 如果获取不到返回null
        System.out.println(node);

        Tree<String> parent = stringTree.getParent();

        List<CharSequence> parentsName = node.getParentsName(true);
        Collections.reverse(parentsName);//倒排序
        System.out.println(parentsName);
        String collect = parentsName.stream().filter(Objects::nonNull).collect(Collectors.joining("/"));
        System.out.println(collect);

        node.getParentsName("", true);

        //过滤不需要的节点,待详细测试
        Tree<String> aaa = node.filter(en -> en.getName().equals("aaa"));
        Tree<String> bbb = node.filterNew(en -> en.getName().equals("aaa"));
    }

}
