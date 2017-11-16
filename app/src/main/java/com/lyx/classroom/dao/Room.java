package com.lyx.classroom.dao;

import java.io.Serializable;

/**
 * Room
 * <p>
 * Created by luoyingxing on 2017/11/17 0017.
 */

public class Room implements Serializable {
    private String name;
    private long time;
    private boolean hasLight;
    private boolean hasCurtain;
    private boolean hasAir;
    private boolean hasBreeze;

    public Room() {
    }

    public Room(String name, boolean hasLight, boolean hasCurtain, boolean hasAir, boolean hasBreeze) {
        this.name = name;
        this.time = System.currentTimeMillis();
        this.hasLight = hasLight;
        this.hasCurtain = hasCurtain;
        this.hasAir = hasAir;
        this.hasBreeze = hasBreeze;
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
