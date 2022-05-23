package com.example.mycheckers;

import android.graphics.Bitmap;

public class Square {

    private Bitmap b;
    private final int v, h;
    private int color, move;
    private boolean allow, d, queen;

    public Square(Bitmap b, int v, int h, int color, boolean allow, boolean d, int move, boolean queen) {
        this.b = b;
        this.v = v;
        this.h = h;
        this.color = color;
        this.d = d;
        this.allow = allow;
        this.move = move;
        this.queen = queen;
    }

    public Bitmap getB() {
        return b;
    }

    public void setB(Bitmap b) {
        this.b = b;
    }

    public int getV() {
        return v;
    }

    public int getH() {
        return h;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public boolean isQueen() {
        return queen;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }
}