package com.lyx.classroom.entity;

import com.lyx.classroom.R;
import com.lyx.frame.annotation.TabText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Node 结点
 * <p>
 * Created by luoyingxing on 2017/11/16 0016.
 */

public class Node implements Serializable {
    private int id;
    @TabText
    private String title;
    private int image;
    private String name;
    private long time;
    private boolean hasLight;
    private boolean hasCurtain;
    private boolean hasAir;
    private boolean hasBreeze;

    /**
     * 校区下的子分区，例如：文科大楼
     */
    private List<Node> mChildNode;
    /**
     * 校区下的子分区的子分区，教室
     */
    private List<Node> mNodeList;

    /**
     * 东丽区
     */
    private static String[] mDongLi = new String[]{"文科楼", "伟南楼", "才林楼"};
    /**
     * 东丽区对应的实景图
     */
    private static int[] mDongLiImage = new int[]{R.mipmap.img_1, R.mipmap.img_2, R.mipmap.img_3};
    /**
     * 东区
     */
    private static String[] mDongQu = new String[]{"理科楼", "信息科技大楼", "伟南国际会议中心"};
    /**
     * 东区对应的实景图
     */
    private static int[] mDongQuImage = new int[]{R.mipmap.img_4, R.mipmap.img_5, R.mipmap.img_6};
    /**
     * 韩东校区
     */
    private static String[] mHanDong = new String[]{"学习楼", "科技楼"};
    /**
     * 韩东校区对应的实景图
     */
    private static int[] mHanDongImage = new int[]{R.mipmap.img_7, R.mipmap.img_8};
    /**
     * 西校区
     */
    private static String[] mXiQu = new String[]{"奋进楼"};
    /**
     * 西区对应的实景图
     */
    private static int[] mXiQuImage = new int[]{R.mipmap.img_9};
    /**
     * 分院校区
     */
    private static String[] mFenQu = new String[]{"国际楼", "文科楼", "科研楼"};
    /**
     * 分院校区对应的实景图
     */
    private static int[] mFenQuImage = new int[]{R.mipmap.img_10, R.mipmap.img_11, R.mipmap.img_12};
    /**
     * 科研校区
     */
    private static String[] mKeYanQu = new String[]{"科研楼"};
    /**
     * 科研校区对应的实景图
     */
    private static int[] mKeYanQuImage = new int[]{R.mipmap.img_13};

    /**
     * 获取所有分区和下属分区
     *
     * @return List<Node>
     */
    public static List<Node> getAreaList() {
        List<Node> list = new ArrayList<>();

        Node node1 = new Node(1, "东丽区");
        List<Node> NodeList1 = new ArrayList<>();
        NodeList1.add(new Node("101教室", true, true, true, true));
        NodeList1.add(new Node("201教室", true, true, true, false));
        NodeList1.add(new Node("301教室", true, false, false, true));
        NodeList1.add(new Node("303教室", true, true, false, true));
        NodeList1.add(new Node("401教室", true, false, true, true));
        NodeList1.add(new Node("605教室", true, true, false, false));
        NodeList1.add(new Node("606教室", true, true, true, true));
        NodeList1.add(new Node("701教室", true, true, false, true));
        NodeList1.add(new Node("801教室", true, true, true, false));

        List<Node> subNode1 = new ArrayList<>();
        for (int i = 0; i < mDongLi.length; i++) {
            Node subNode = new Node(mDongLi[i], mDongLiImage[i]);
            subNode.setNodeList(NodeList1);
            subNode1.add(subNode);
        }
        node1.setChildArea(subNode1);
        list.add(node1);

        Node node2 = new Node(2, "东区");
        List<Node> NodeList2 = new ArrayList<>();
        NodeList2.add(new Node("201教室", true, true, true, false));
        NodeList2.add(new Node("301教室", true, false, false, true));
        NodeList2.add(new Node("605教室", true, true, false, false));
        NodeList2.add(new Node("701教室", true, true, false, true));
        NodeList2.add(new Node("801教室", true, true, true, false));
        node2.setNodeList(NodeList2);

        List<Node> subNode2 = new ArrayList<>();
        for (int i = 0; i < mDongQu.length; i++) {
            Node subNode = new Node(mDongQu[i], mDongQuImage[i]);
            subNode.setNodeList(NodeList2);
            subNode2.add(subNode);
        }
        node2.setChildArea(subNode2);

        list.add(node2);

        Node node3 = new Node(3, "韩东校区");
        List<Node> NodeList3 = new ArrayList<>();
        NodeList3.add(new Node("201教室", true, true, true, false));
        NodeList3.add(new Node("301教室", true, false, false, true));
        NodeList3.add(new Node("701教室", true, true, false, true));
        node3.setNodeList(NodeList3);
        List<Node> subNode3 = new ArrayList<>();
        for (int i = 0; i < mHanDong.length; i++) {
            Node subNode = new Node(mHanDong[i], mHanDongImage[i]);
            subNode.setNodeList(NodeList3);
            subNode3.add(subNode);
        }
        node3.setChildArea(subNode3);

        list.add(node3);

        Node node4 = new Node(4, "西校区");
        List<Node> NodeList4 = new ArrayList<>();
        NodeList4.add(new Node("101教室", true, true, true, true));
        NodeList4.add(new Node("301教室", true, false, false, true));
        NodeList4.add(new Node("303教室", true, true, false, true));
        NodeList4.add(new Node("401教室", true, false, true, true));
        NodeList4.add(new Node("606教室", true, true, true, true));
        NodeList4.add(new Node("701教室", true, true, false, true));
        node4.setNodeList(NodeList4);
        List<Node> subNode4 = new ArrayList<>();
        for (int i = 0; i < mXiQu.length; i++) {
            Node subNode = new Node(mXiQu[i], mXiQuImage[i]);
            subNode.setNodeList(NodeList4);
            subNode4.add(subNode);
        }
        node4.setChildArea(subNode4);

        list.add(node4);

        Node node5 = new Node(5, "分院校区");
        List<Node> NodeList5 = new ArrayList<>();
        NodeList5.add(new Node("303教室", true, true, false, true));
        NodeList5.add(new Node("401教室", true, false, true, true));
        node5.setNodeList(NodeList5);
        List<Node> subNode5 = new ArrayList<>();
        for (int i = 0; i < mFenQu.length; i++) {
            Node subNode = new Node(mFenQu[i], mFenQuImage[i]);
            subNode.setNodeList(NodeList5);
            subNode5.add(subNode);
        }
        node5.setChildArea(subNode5);

        list.add(node5);

        Node node6 = new Node(6, "科研校区");
        List<Node> NodeList6 = new ArrayList<>();
        NodeList6.add(new Node("101教室", true, true, true, true));
        NodeList6.add(new Node("303教室", true, true, false, true));
        NodeList6.add(new Node("606教室", true, true, true, true));
        node6.setNodeList(NodeList6);
        List<Node> subNode6 = new ArrayList<>();
        for (int i = 0; i < mKeYanQu.length; i++) {
            Node subNode = new Node(mKeYanQu[i], mKeYanQuImage[i]);
            subNode.setNodeList(NodeList6);
            subNode6.add(subNode);
        }
        node6.setChildArea(subNode6);

        list.add(node6);

        return list;
    }

    public Node() {
    }

    public Node(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Node(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public Node(String name, boolean hasLight, boolean hasCurtain, boolean hasAir, boolean hasBreeze) {
        this.name = name;
        this.time = System.currentTimeMillis();
        this.hasLight = hasLight;
        this.hasCurtain = hasCurtain;
        this.hasAir = hasAir;
        this.hasBreeze = hasBreeze;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public List<Node> getChildArea() {
        return mChildNode;
    }

    public void setChildArea(List<Node> childNode) {
        this.mChildNode = childNode;
    }

    public List<Node> getNodeList() {
        return mNodeList;
    }

    public void setNodeList(List<Node> NodeList) {
        this.mNodeList = NodeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isHasLight() {
        return hasLight;
    }

    public void setHasLight(boolean hasLight) {
        this.hasLight = hasLight;
    }

    public boolean isHasCurtain() {
        return hasCurtain;
    }

    public void setHasCurtain(boolean hasCurtain) {
        this.hasCurtain = hasCurtain;
    }

    public boolean isHasAir() {
        return hasAir;
    }

    public void setHasAir(boolean hasAir) {
        this.hasAir = hasAir;
    }

    public boolean isHasBreeze() {
        return hasBreeze;
    }

    public void setHasBreeze(boolean hasBreeze) {
        this.hasBreeze = hasBreeze;
    }
}
