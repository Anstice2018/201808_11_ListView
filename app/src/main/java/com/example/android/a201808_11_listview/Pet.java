package com.example.android.a201808_11_listview;

import java.io.Serializable;

public class Pet implements Serializable{
    private String id;
    private String name;
    private int drawableId;


    public Pet(String id, String name, int drawableId) {
        this.id = id;
        this.name = name;
        this.drawableId = drawableId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }

}
