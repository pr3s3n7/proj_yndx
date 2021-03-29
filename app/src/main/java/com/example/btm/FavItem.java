package com.example.btm;

import java.io.Serializable;

public class FavItem implements Serializable {
    private String favTiker;
    private String favFullname;
    private int favIconResource;
    private String favItemId;

    public FavItem(String tiker, String fullname, String itemId, int icon) {
        this.favItemId = itemId;
        this.favTiker = tiker;
        this.favFullname = fullname;
        this.favIconResource = icon;
    }

    public String getFavItemId() { return this.favItemId; }

    public void setFavItemId(String favItemId) { this.favItemId = favItemId; }

    public String getFavTiker() {
        return this.favTiker;
    }

    public void setFavTiker(String favTiker) {
        this.favTiker = favTiker;
    }

    public String getFavFullname() { return this.favFullname; }

    public void setFavFullname(String favFullname) {
        this.favFullname = favFullname;
    }

    public int getFavIconResource() { return this.favIconResource; }

    public void setFavIconResource(int favIcon) {
        this.favIconResource = favIcon;
    }
}

