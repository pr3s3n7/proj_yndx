package com.example.btm;

import java.io.Serializable;

public class Item implements Serializable {
    private String tiker;
    private String fullname;
    private int iconResource;
    private String favorite;
    private String itemId;

    public Item(String itemId, String tiker, String fullname, int icon, String favorite) {
        this.itemId = itemId;
        this.tiker = tiker;
        this.fullname = fullname;
        this.iconResource = icon;
        this.favorite = favorite;
    }

    public String getItemId() { return this.itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getTiker() {
        return this.tiker;
    }

    public void setTiker(String tiker) {
        this.tiker = tiker;
    }

    public String getFullname() { return this.fullname; }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getIconResource() { return this.iconResource; }

    public void setIconResource(int icon) {
        this.iconResource = icon;
    }

    public String getFavorite() { return this.favorite; }

    public void setFavorite(String favorite) { this.favorite = favorite; }
}

