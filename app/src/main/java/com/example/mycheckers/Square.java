package com.example.mycheckers;

import android.graphics.Bitmap;

public class Square {

    private Bitmap b;
    private final int v, h;
    private int color, move;
    private boolean allow, movew, moveb, d, l, r, fightw, fightb;

    public Square(Bitmap b, int v, int h, int color, boolean allow, boolean d, boolean movew, boolean moveb, boolean l, boolean r, boolean fightw, boolean fightb, int move) {
        this.b = b;
        this.v = v;
        this.h = h;
        this.color = color;
        this.d = d;
        this.allow = allow;
        this.movew = movew;
        this.moveb = moveb;
        this.l = l;
        this.r = r;
        this.fightw = fightw;
        this.fightb = fightb;
        this.move = move;
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

    public boolean isMovew() {
        return movew;
    }

    public void setMovew(boolean movew) {
        this.movew = movew;
    }

    public boolean isMoveb() {
        return moveb;
    }

    public void setMoveb(boolean moveb) {
        this.moveb = moveb;
    }

    public boolean isL() {
        return l;
    }

    public void setL(boolean l) {
        this.l = l;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean isFightw() {
        return fightw;
    }

    public void setFightw(boolean fightw) {
        this.fightw = fightw;
    }

    public boolean isFightb() {
        return fightb;
    }

    public void setFightb(boolean fightb) {
        this.fightb = fightb;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}