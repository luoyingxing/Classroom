package com.lyx.classroom.dao;

import com.lyx.classroom.R;
import com.lyx.frame.annotation.TabText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Area
 * <p>
 * Created by luoyingxing on 2017/11/16 0016.
 */

public class Area implements Serializable {
    private int id;
    @TabText
    private String title;
    private int image;
    /**
     * 校区下的子分区，例如：文科大楼
     */
    private List<Area> mChildArea;

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
     * @return List<Area>
     */
    public static List<Area> getAreaList() {
        List<Area> list = new ArrayList<>();

        Area area1 = new Area(1, "东丽区");
        List<Area> subArea1 = new ArrayList<>();
        for (int i = 0; i < mDongLi.length; i++) {
            subArea1.add(new Area(mDongLi[i], mDongLiImage[i]));
        }
        area1.setChildArea(subArea1);
        list.add(area1);

        Area area2 = new Area(2, "东区");
        List<Area> subArea2 = new ArrayList<>();
        for (int i = 0; i < mDongQu.length; i++) {
            subArea2.add(new Area(mDongQu[i], mDongQuImage[i]));
        }
        area2.setChildArea(subArea2);
        list.add(area2);

        Area area3 = new Area(3, "韩东校区");
        List<Area> subArea3 = new ArrayList<>();
        for (int i = 0; i < mHanDong.length; i++) {
            subArea3.add(new Area(mHanDong[i], mHanDongImage[i]));
        }
        area3.setChildArea(subArea3);
        list.add(area3);

        Area area4 = new Area(4, "西校区");
        List<Area> subArea4 = new ArrayList<>();
        for (int i = 0; i < mXiQu.length; i++) {
            subArea4.add(new Area(mXiQu[i], mXiQuImage[i]));
        }
        area4.setChildArea(subArea4);
        list.add(area4);

        Area area5 = new Area(5, "分院校区");
        List<Area> subArea5 = new ArrayList<>();
        for (int i = 0; i < mFenQu.length; i++) {
            subArea5.add(new Area(mFenQu[i], mFenQuImage[i]));
        }
        area5.setChildArea(subArea5);
        list.add(area5);

        Area area6 = new Area(6, "科研校区");
        List<Area> subArea6 = new ArrayList<>();
        for (int i = 0; i < mKeYanQu.length; i++) {
            subArea6.add(new Area(mKeYanQu[i], mKeYanQuImage[i]));
        }
        area6.setChildArea(subArea6);
        list.add(area6);

        return list;
    }

    public Area() {
    }

    public Area(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Area(String title, int image) {
        this.title = title;
        this.image = image;
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

    public List<Area> getChildArea() {
        return mChildArea;
    }

    public void setChildArea(List<Area> childArea) {
        this.mChildArea = childArea;
    }
}
