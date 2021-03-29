package com.example.btm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphModel {
    @SerializedName("c")
    @Expose
    float[] c;
    @SerializedName("h")
    @Expose
    float[] h;
    @SerializedName("l")
    @Expose
    float[] l;
    @SerializedName("o")
    @Expose
    float[] o;
    @SerializedName("s")
    @Expose
    String s;
    @SerializedName("t")
    @Expose
    long[] t;
    @SerializedName("v")
    @Expose
    long[] v;

    public GraphModel(float[] c, float[] h, float[] l, float[] o, String s, long[] t, long[] v) {
        this.c = c;
        this.h = h;
        this.l = l;
        this.o = o;
        this.s = s;
        this.t = t;
        this.v = v;
    }

    public float getC(int index) { return c[index]; }

    public void setC(float[] c) {
        this.c = c;
    }

    public float getH(int index) {
        return h[index];
    }

    public void setH(float[] h) {
        this.h = h;
    }

    public float getL(int index) {
        return l[index];
    }

    public void setL(float[] l) {
        this.l = l;
    }

    public float getO(int index) { return o[index]; }

    public void setO(float[] o) {
        this.o = o;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public long getT(int index) {
        return t[index];
    }

    public void setT(long[] t) {
        this.t = t;
    }

    public long getV(int index) { return v[index]; }

    public void setV(long[] v) {
        this.v = v;
    }
}
