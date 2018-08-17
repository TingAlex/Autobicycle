package com.comsoftstar.autobicycle.Model.Bean;

/**
 * Created by Administrator on 2017/9/27.
 */

public class My_Listitem {
    private int image;
    private String title;

    public My_Listitem(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
