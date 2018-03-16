package com.cc.library;

/**
 * Created by CC on 2018/3/12.
 */

public class Item {
    private int icon;
    private String name;

    public Item(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
