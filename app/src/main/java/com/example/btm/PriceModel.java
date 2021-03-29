package com.example.btm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceModel {

        @SerializedName("c")
        @Expose
        private String c;
        @SerializedName("h")
        @Expose
        private String h;
        @SerializedName("l")
        @Expose
        private String l;
        @SerializedName("o")
        @Expose
        private String o;
        @SerializedName("pc")
        @Expose
        private String pc;
        @SerializedName("t")
        @Expose
        private String t;

    public PriceModel(String c, String h, String l, String o, String pc, String t) {
        this.c = c;
        this.h = h;
        this.l = l;
        this.o = o;
        this.pc = pc;
        this.t = t;
    }

    public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getL() {
            return l;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getO() {
            return o;
        }

        public void setO(String o) {
            this.o = o;
        }

        public String getPc() {
            return pc;
        }

        public void setPc(String pc) {
            this.pc = pc;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }
}

