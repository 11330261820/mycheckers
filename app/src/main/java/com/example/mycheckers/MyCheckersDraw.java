package com.example.mycheckers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyCheckersDraw extends View {
    Bitmap wc = BitmapFactory.decodeResource(getResources(), R.drawable.whitechecker);
    Bitmap bc = BitmapFactory.decodeResource(getResources(), R.drawable.blackchecker);
    Bitmap wq = BitmapFactory.decodeResource(getResources(), R.drawable.whitequeen);
    Bitmap bq = BitmapFactory.decodeResource(getResources(), R.drawable.blackqueen);
    Bitmap bs = BitmapFactory.decodeResource(getResources(), R.drawable.blacksquare);
    Square[][] squares = new Square[8][8];
    float touchX, touchY;
    int check2, flag, o, q, k, l, check, check1, check3 = 0;
    int who = 1;

    public MyCheckersDraw(Context context) {
        super(context);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if ((i + 1) % 2 == 0) {
                    switch (j) {
                        case 0:
                        case 2:
                        case 4:
                        case 6:
                            squares[i][j] = new Square(null, 0, 0, 3, false, false, false, false, false, false, false, false, 0, false);
                            break;
                        case 1:
                            squares[i][j] = new Square(wc, i + 1, j + 1, 1, false, false, false, false, false, false, false, false, 0, false);
                            break;
                        case 3:
                            squares[i][j] = new Square(bs, i + 1, j + 1, 0, false, false, false, false, false, false, false, false, 0, false);
                            break;
                        case 5:
                        case 7:
                            squares[i][j] = new Square(bc, i + 1, j + 1, 2, false, false, false, false, false, false, false, false, 0, false);
                            break;
                    }
                } else {
                    switch (j) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                            squares[i][j] = new Square(null, 0, 0, 3, false, false, false, false, false, false, false, false, 0, false);
                            break;
                        case 0:
                        case 2:
                            squares[i][j] = new Square(wc, i + 1, j + 1, 1, false, false, false, false, false, false, false, false, 0, false);
                            break;
                        case 4:
                            squares[i][j] = new Square(bs, i + 1, j + 1, 0, false, false, false, false, false, false, false, false, 0, false);
                            break;
                        case 6:
                            squares[i][j] = new Square(bc, i + 1, j + 1, 2, false, false, false, false, false, false, false, false, 0, false);
                            break;
                    }
                }
            }
        }
    }

    Paint p = new Paint();

    public void spawn(Canvas canvas, Square s) {
        canvas.drawBitmap(s.getB(), (s.getV() - 1) * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((8 - s.getH()) * getWidth() / 8), p);
    }

    public void drawSquare(Canvas canvas, Square s, Bitmap b) {
        s.setB(b);
        if (s.getB() == wc || s.getB() == wq)
            s.setColor(1);
        else if (s.getB() == bc || s.getB() == bq)
            s.setColor(2);
        else if (s.getB() == bs)
            s.setColor(0);
        canvas.drawBitmap(b, (s.getV() - 1) * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((8 - s.getH()) * getWidth() / 8), p);
    }

    public void drawAllow(Canvas canvas, Square s, Paint p) {
        p.setColor(Color.GREEN);
        canvas.drawRect((s.getV() - 1) * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((8 - s.getH()) * getWidth() / 8), s.getV() * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((9 - s.getH()) * getWidth() / 8), p);
    }

    public void move(Canvas canvas, Square s1, Square s2, Bitmap b) {
        drawSquare(canvas, s1, b);
        drawSquare(canvas, s2, bs);
        if (who == 1) who = 2;
        else if (who == 2) who = 1;
    }

    public void fight(Canvas canvas, Square s1, Square s2, Square s3, Bitmap b) {
        drawSquare(canvas, s1, b);
        drawSquare(canvas, s2, bs);
        drawSquare(canvas, s3, bs);
        if (s2.isQueen()) s2.setQueen(false);
        if (who == 1) who = 2;
        else if (who == 2) who = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        int x = 0, y = getHeight() / 2 - getWidth() / 2, a = 0;
        float x0 = x, y0 = y;
        /*рисование доски*/
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                canvas.drawRect(x, y, x + (float) getWidth() / 8, y + (float) getWidth() / 8, p);
                a++;
                p.setColor(a % 2 == 0 ? Color.WHITE : Color.rgb(128, 128, 128));
                x += getWidth() / 8;
            }
            x = 0;
            y += getWidth() / 8;
            p.setColor(a % 2 == 1 ? Color.WHITE : Color.rgb(128, 128, 128));
            a--;
        }
        p.setColor(Color.rgb(128, 128, 128));
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x0, y0, getWidth() - x, y, p);
        /*начальная расстановка шашек*/
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (i % 2 == j % 2)
                    spawn(canvas, squares[i][j]);
            }
        }
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(35f);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (i % 2 == j % 2 && squares[i][j].isAllow() && squares[i][j].isD()) {
                    for (int k = 0; k < squares.length; k++) {
                        for (int l = 0; l < squares[i].length; l++) {
                            if (squares[k][l].isAllow() && k == i && l == j) {
                                squares[k][l].setAllow(false);
                            }
                        }
                    }
                    /*подсветка полей*/
                    if (check1 == 1) {
                        if (!squares[i][j].isQueen())
                            switch (j) {
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                    if (squares[i][j].getColor() == 1 && who == 1) {
                                        if (i >= 1 && squares[i - 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j + 1], p);
                                        if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j + 1], p);
                                        if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 2)
                                            drawAllow(canvas, squares[i - 2][j + 2], p);
                                        if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 2)
                                            drawAllow(canvas, squares[i + 2][j + 2], p);
                                        if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 2)
                                            drawAllow(canvas, squares[i - 2][j - 2], p);
                                        if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 2)
                                            drawAllow(canvas, squares[i + 2][j - 2], p);
                                    } else if (squares[i][j].getColor() == 2 && who == 2) {
                                        if (i >= 1 && squares[i - 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j - 1], p);
                                        if (i <= 6 && squares[i + 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j - 1], p);
                                        if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 1)
                                            drawAllow(canvas, squares[i - 2][j - 2], p);
                                        if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 1)
                                            drawAllow(canvas, squares[i + 2][j - 2], p);
                                        if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 1)
                                            drawAllow(canvas, squares[i - 2][j + 2], p);
                                        if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 1)
                                            drawAllow(canvas, squares[i + 2][j + 2], p);
                                    }
                                    break;
                                case 0:
                                    if (squares[i][j].getColor() == 1 && who == 1) {
                                        if (i >= 1 && squares[i - 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j + 1], p);
                                        if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j + 1], p);
                                        if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 2)
                                            drawAllow(canvas, squares[i - 2][j + 2], p);
                                        if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 2)
                                            drawAllow(canvas, squares[i + 2][j + 2], p);
                                    } else if (squares[i][j].getColor() == 2 && who == 2) {
                                        if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 1)
                                            drawAllow(canvas, squares[i - 2][j + 2], p);
                                        if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 1)
                                            drawAllow(canvas, squares[i + 2][j + 2], p);
                                    }
                                    break;
                                case 1:
                                    if (squares[i][j].getColor() == 1 && who == 1) {
                                        if (squares[i - 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j + 1], p);
                                        if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j + 1], p);
                                        if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 2)
                                            drawAllow(canvas, squares[i - 2][j + 2], p);
                                        if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 2)
                                            drawAllow(canvas, squares[i + 2][j + 2], p);
                                    } else if (squares[i][j].getColor() == 2 && who == 2) {
                                        if (squares[i - 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j - 1], p);
                                        if (i <= 6 && squares[i + 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j - 1], p);
                                        if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 1)
                                            drawAllow(canvas, squares[i - 2][j + 2], p);
                                        if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 1)
                                            drawAllow(canvas, squares[i + 2][j + 2], p);
                                    }
                                    break;
                                case 6:
                                    if (squares[i][j].getColor() == 1 && who == 1) {
                                        if (i >= 1 && squares[i - 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j + 1], p);
                                        if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j + 1], p);
                                        if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 2)
                                            drawAllow(canvas, squares[i - 2][j - 2], p);
                                        if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 2)
                                            drawAllow(canvas, squares[i + 2][j - 2], p);
                                    } else if (squares[i][j].getColor() == 2 && who == 2) {
                                        if (i >= 1 && squares[i - 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j - 1], p);
                                        if (i <= 6 && squares[i + 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j - 1], p);
                                        if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 1)
                                            drawAllow(canvas, squares[i - 2][j - 2], p);
                                        if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 1)
                                            drawAllow(canvas, squares[i + 2][j - 2], p);
                                    }
                                    break;
                                case 7:
                                    if (squares[i][j].getColor() == 1 && who == 1) {
                                        if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 2)
                                            drawAllow(canvas, squares[i - 2][j - 2], p);
                                        if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 2)
                                            drawAllow(canvas, squares[i + 2][j - 2], p);
                                    } else if (squares[i][j].getColor() == 2 && who == 2) {
                                        if (squares[i - 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i - 1][j - 1], p);
                                        if (i <= 6 && squares[i + 1][j - 1].getColor() == 0)
                                            drawAllow(canvas, squares[i + 1][j - 1], p);
                                        if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 1)
                                            drawAllow(canvas, squares[i - 2][j - 2], p);
                                        if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 1)
                                            drawAllow(canvas, squares[i + 2][j - 2], p);
                                    }
                                    break;
                            }
                        else if (check == 0 && squares[i][j].getColor() == who) {
                            if (i + 1 < 8 && j + 1 < 8)
                                if (squares[i + 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i + 1][j + 1], p);
                                else if (squares[i + 1][j + 1].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 2 < 8 && j + 2 < 8)
                                if (squares[i + 2][j + 2].getColor() == 0 && k == 0)
                                    drawAllow(canvas, squares[i + 2][j + 2], p);
                                else if (squares[i + 2][j + 2].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 3 < 8 && j + 3 < 8)
                                if (squares[i + 3][j + 3].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 3][j + 3], p);
                                else if (squares[i + 3][j + 3].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 4 < 8 && j + 4 < 8)
                                if (squares[i + 4][j + 4].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 4][j + 4], p);
                                else if (squares[i + 4][j + 4].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 5 < 8 && j + 5 < 8)
                                if (squares[i + 5][j + 5].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 5][j + 5], p);
                                else if (squares[i + 5][j + 5].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 6 < 8 && j + 6 < 8)
                                if (squares[i + 6][j + 6].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 6][j + 6], p);
                                else if (squares[i + 6][j + 6].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 7 < 8 && j + 7 < 8)
                                if (squares[i + 7][j + 7].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 7][j + 7], p);
                            k = 0;
                            l = 0;
                            if (i - 1 > -1 && j + 1 < 8)
                                if (squares[i - 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i - 1][j + 1], p);
                                else if (squares[i - 1][j + 1].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 2 > -1 && j + 2 < 8)
                                if (squares[i - 2][j + 2].getColor() == 0)
                                    drawAllow(canvas, squares[i - 2][j + 2], p);
                                else if (squares[i - 2][j + 2].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 3 > -1 && j + 3 < 8)
                                if (squares[i - 3][j + 3].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 3][j + 3], p);
                                else if (squares[i - 3][j + 3].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 4 > -1 && j + 4 < 8)
                                if (squares[i - 4][j + 4].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 4][j + 4], p);
                                else if (squares[i - 4][j + 4].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 5 > -1 && j + 5 < 8)
                                if (squares[i - 5][j + 5].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 5][j + 5], p);
                                else if (squares[i - 5][j + 5].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 6 > -1 && j + 6 < 8)
                                if (squares[i - 6][j + 6].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 6][j + 6], p);
                                else if (squares[i - 6][j + 6].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 7 > -1 && j + 7 < 8)
                                if (squares[i - 7][j + 7].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 7][j + 7], p);
                            k = 0;
                            l = 0;
                            if (i - 1 > -1 && j - 1 > -1)
                                if (squares[i - 1][j - 1].getColor() == 0)
                                    drawAllow(canvas, squares[i - 1][j - 1], p);
                                else if (squares[i - 1][j - 1].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 2 > -1 && j - 2 > -1)
                                if (squares[i - 2][j - 2].getColor() == 0)
                                    drawAllow(canvas, squares[i - 2][j - 2], p);
                                else if (squares[i - 2][j - 2].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 3 > -1 && j - 3 > -1)
                                if (squares[i - 3][j - 3].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 3][j - 3], p);
                                else if (squares[i - 3][j - 3].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 4 > -1 && j - 4 > -1)
                                if (squares[i - 4][j - 4].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 4][j - 4], p);
                                else if (squares[i - 4][j - 4].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 5 > -1 && j - 5 > -1)
                                if (squares[i - 5][j - 5].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 5][j - 5], p);
                                else if (squares[i - 5][j - 5].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 6 > -1 && j - 6 > -1)
                                if (squares[i - 6][j - 6].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 6][j - 6], p);
                                else if (squares[i - 6][j - 6].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i - 7 > -1 && j - 7 > -1)
                                if (squares[i - 7][j - 7].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i - 7][j - 7], p);
                            k = 0;
                            l = 0;
                            if (i + 1 < 8 && j - 1 > -1)
                                if (squares[i + 1][j - 1].getColor() == 0)
                                    drawAllow(canvas, squares[i + 1][j - 1], p);
                                else if (squares[i + 1][j - 1].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 2 < 8 && j - 2 > -1)
                                if (squares[i + 2][j - 2].getColor() == 0)
                                    drawAllow(canvas, squares[i + 2][j - 2], p);
                                else if (squares[i + 2][j - 2].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 3 < 8 && j - 3 > -1)
                                if (squares[i + 3][j - 3].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 3][j - 3], p);
                                else if (squares[i + 3][j - 3].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 4 < 8 && j - 4 > -1)
                                if (squares[i + 4][j - 4].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 4][j - 4], p);
                                else if (squares[i + 4][j - 4].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 5 < 8 && j - 5 > -1)
                                if (squares[i + 5][j - 5].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 5][j - 5], p);
                                else if (squares[i + 5][j - 5].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 6 < 8 && j - 6 > -1)
                                if (squares[i + 6][j - 6].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 6][j - 6], p);
                                else if (squares[i + 6][j - 6].getColor() == squares[i][j].getColor())
                                    k++;
                                else l++;
                            if (i + 7 < 8 && j - 7 > -1)
                                if (squares[i + 7][j - 7].getColor() == 0 && k == 0 && l < 2)
                                    drawAllow(canvas, squares[i + 7][j - 7], p);
                            k = 0;
                            l = 0;
                        }
                        check1 = 0;
                    }
                    check = 0;
                    /*ход белой шашки*/
                    if (squares[i][j].isMovew()) {
                        if (squares[i][j].isR()) {
                            move(canvas, squares[i][j], squares[i - 1][j - 1], wc);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            move(canvas, squares[i][j], squares[i + 1][j - 1], wc);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setMovew(false);
                    }
                    /*ход чёрной шашки*/
                    if (squares[i][j].isMoveb()) {
                        if (squares[i][j].isR()) {
                            move(canvas, squares[i][j], squares[i - 1][j + 1], bc);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            move(canvas, squares[i][j], squares[i + 1][j + 1], bc);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setMoveb(false);
                    }
                    /*взятие белой шашкой*/
                    if (squares[i][j].isFightw()) {
                        switch (i) {
                            case 0:
                            case 1:
                                switch (squares[i][j].getMove()) {
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], wc);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], wc);
                                        break;
                                }
                                break;
                            case 6:
                            case 7:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], wc);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], wc);
                                        break;
                                }
                                break;
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], wc);
                                        break;
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], wc);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], wc);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], wc);
                                        break;
                                }
                                break;
                        }
                        squares[i][j].setMove(0);
                    }
                    /*взятие чёрной шашкой*/
                    if (squares[i][j].isFightb()) {
                        switch (i) {
                            case 0:
                            case 1:
                                switch (squares[i][j].getMove()) {
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], bc);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], bc);
                                        break;
                                }
                                break;
                            case 6:
                            case 7:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], bc);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], bc);
                                        break;
                                }
                                break;
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], bc);
                                        break;
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], bc);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], bc);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], bc);
                                        break;
                                }
                                break;
                        }
                        squares[i][j].setMove(0);
                    }
                    /*появление дамки*/
                    if (squares[i][j].isQueen()) {
                        if (j == 7 && squares[i][j].getB() == wc) squares[i][j].setB(wq);
                        else if (j == 0 && squares[i][j].getB() == bc) squares[i][j].setB(bq);
                        drawSquare(canvas, squares[i][j], squares[i][j].getB());
                    }
                    canvas.drawText("" + who + " " + check3, 100, 100, p);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares[i].length; j++) {
                    if (touchX > (squares[i][j].getV() - 1) * getWidth() / 8 &&
                            touchX < squares[i][j].getV() * getWidth() / 8 &&
                            touchY > getHeight() / 2 - getWidth() / 2 + ((8 - squares[i][j].getH()) * getWidth() / 8) &&
                            touchY < getHeight() / 2 - getWidth() / 2 + ((9 - squares[i][j].getH()) * getWidth() / 8) &&
                            i % 2 == j % 2) {
                        switch (squares[i][j].getColor()) {
                            case 0:
                                for (Square[] square : squares) {
                                    for (int l = 0; l < squares[i].length; l++) {
                                        if (square[l].isD() && square[l].getColor() != 0) {
                                            check3 = square[l].getColor();
                                        }
                                    }
                                }
                                if (who == check3) {
                                    for (int k = 0; k < squares.length; k++) {
                                        for (int l = 0; l < squares[i].length; l++) {
                                            if (squares[k][l].isD() && squares[k][l].isQueen()) {
                                                if (i > k && j > l && i - k == j - l) {
                                                    squares[k][l].setMove(1);
                                                    int m = k;
                                                    int n = l;
                                                    while (k < i && l < j) {
                                                        k++;
                                                        l++;
                                                        if (squares[k][l].getColor() == squares[m][n].getColor()) {
                                                            flag++;
                                                            break;
                                                        } else if (squares[k][l].getColor() != 0) {
                                                            check2++;
                                                            o = k;
                                                            q = l;
                                                            if (check2 > 1) {
                                                                flag++;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (flag == 0) {
                                                        if (check2 == 1) {
                                                            fight(new Canvas(), squares[i][j], squares[o][q], squares[m][n], squares[m][n].getB());
                                                        } else if (check2 == 0) {
                                                            move(new Canvas(), squares[i][j], squares[m][n], squares[m][n].getB());
                                                        }
                                                    }
                                                    squares[i][j].setQueen(true);
                                                    squares[m][n].setQueen(false);
                                                    check2 = 0;
                                                    flag = 0;
                                                    o = 0;
                                                    q = 0;
                                                    squares[m][n].setMove(0);
                                                } else if (i < k && j > l && k - i == j - l) {
                                                    squares[k][l].setMove(2);
                                                    int m = k;
                                                    int n = l;
                                                    while (i < k && l < j) {
                                                        k--;
                                                        l++;
                                                        if (squares[k][l].getColor() == squares[m][n].getColor()) {
                                                            flag++;
                                                            break;
                                                        } else if (squares[k][l].getColor() != 0) {
                                                            check2++;
                                                            o = k;
                                                            q = l;
                                                            if (check2 > 1) {
                                                                flag++;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (flag == 0) {
                                                        if (check2 == 1) {
                                                            fight(new Canvas(), squares[i][j], squares[o][q], squares[m][n], squares[m][n].getB());
                                                        } else if (check2 == 0) {
                                                            move(new Canvas(), squares[i][j], squares[m][n], squares[m][n].getB());
                                                        }
                                                    }
                                                    squares[i][j].setQueen(true);
                                                    squares[m][n].setQueen(false);
                                                    check2 = 0;
                                                    flag = 0;
                                                    o = 0;
                                                    q = 0;
                                                    squares[m][n].setMove(0);
                                                } else if (i < k && j < l && k - i == l - j) {
                                                    squares[k][l].setMove(3);
                                                    int m = k;
                                                    int n = l;
                                                    while (i < k && j < l) {
                                                        k--;
                                                        l--;
                                                        if (squares[k][l].getColor() == squares[m][n].getColor()) {
                                                            flag++;
                                                            break;
                                                        } else if (squares[k][l].getColor() != 0) {
                                                            check2++;
                                                            o = k;
                                                            q = l;
                                                            if (check2 > 1) {
                                                                flag++;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (flag == 0) {
                                                        if (check2 == 1) {
                                                            fight(new Canvas(), squares[i][j], squares[o][q], squares[m][n], squares[m][n].getB());
                                                        } else if (check2 == 0) {
                                                            move(new Canvas(), squares[i][j], squares[m][n], squares[m][n].getB());
                                                        }
                                                    }
                                                    squares[i][j].setQueen(true);
                                                    squares[m][n].setQueen(false);
                                                    check2 = 0;
                                                    flag = 0;
                                                    o = 0;
                                                    q = 0;
                                                    squares[m][n].setMove(0);
                                                } else if (i > k && j < l && i - k == l - j) {
                                                    squares[k][l].setMove(4);
                                                    int m = k;
                                                    int n = l;
                                                    while (k < i && j < l) {
                                                        k++;
                                                        l--;
                                                        if (squares[k][l].getColor() == squares[m][n].getColor()) {
                                                            flag++;
                                                            break;
                                                        } else if (squares[k][l].getColor() != 0) {
                                                            check2++;
                                                            o = k;
                                                            q = l;
                                                            if (check2 > 1) {
                                                                flag++;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    if (flag == 0) {
                                                        if (check2 == 1) {
                                                            fight(new Canvas(), squares[i][j], squares[o][q], squares[m][n], squares[m][n].getB());
                                                        } else if (check2 == 0) {
                                                            move(new Canvas(), squares[i][j], squares[m][n], squares[m][n].getB());
                                                        }
                                                    }
                                                    squares[i][j].setQueen(true);
                                                    squares[m][n].setQueen(false);
                                                    check2 = 0;
                                                    flag = 0;
                                                    o = 0;
                                                    q = 0;
                                                    squares[m][n].setMove(0);
                                                }
                                            }
                                        }
                                    }
                                    switch (i) {
                                        case 2:
                                        case 3:
                                        case 4:
                                        case 5:
                                            switch (j) {
                                                case 2:
                                                case 3:
                                                case 4:
                                                case 5:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(1);
                                                        } else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(1);
                                                        }
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(2);
                                                        } else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(2);
                                                        }
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(4);
                                                        } else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(4);
                                                        }
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(3);
                                                        } else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(3);
                                                        }
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 0:
                                                    if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setQueen(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(4);
                                                        } else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(4);
                                                        }
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setQueen(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(3);
                                                        } else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(3);
                                                        }
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 1:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(4);
                                                        } else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(4);
                                                        }
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(3);
                                                        } else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(3);
                                                        }
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 6:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(1);
                                                        } else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(1);
                                                        }
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(2);
                                                        } else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(2);
                                                        }
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 7:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(1);
                                                        } else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setQueen(true);
                                                            squares[i][j].setR(true);
                                                            squares[i][j].setMove(1);
                                                        }
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(2);
                                                        } else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setQueen(true);
                                                            squares[i][j].setL(true);
                                                            squares[i][j].setMove(2);
                                                        }
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 1:
                                            switch (j) {
                                                case 3:
                                                case 5:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(2);
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(3);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 1:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(3);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 7:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setQueen(true);
                                                        }
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(2);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 6:
                                            switch (j) {
                                                case 2:
                                                case 4:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(1);
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(4);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 0:
                                                    if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setQueen(true);
                                                        } else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(4);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 6:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(1);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 0:
                                            switch (j) {
                                                case 2:
                                                case 4:
                                                    if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(2);
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(3);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 0:
                                                    if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i + 2][j + 2].isD()) {
                                                        if (squares[i + 1][j + 1].getColor() == 1 && squares[i + 2][j + 2].getColor() == 2) {
                                                            squares[i][j].setFightb(true);
                                                            squares[i][j].setQueen(true);
                                                        } else if (squares[i + 1][j + 1].getColor() == 2 && squares[i + 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(3);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 6:
                                                    if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setL(true);
                                                    } else if (squares[i + 2][j - 2].isD()) {
                                                        if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setL(true);
                                                        squares[i][j].setMove(2);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 7:
                                            switch (j) {
                                                case 3:
                                                case 5:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(1);
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(4);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 1:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                        squares[i][j].setMoveb(true);
                                                        squares[i][j].setR(true);
                                                    } else if (squares[i - 2][j + 2].isD()) {
                                                        if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                            squares[i][j].setFightw(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(4);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 7:
                                                    if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                        squares[i][j].setMovew(true);
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setQueen(true);
                                                    } else if (squares[i - 2][j - 2].isD()) {
                                                        if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2)
                                                            squares[i][j].setFightb(true);
                                                        else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1) {
                                                            squares[i][j].setFightw(true);
                                                            squares[i][j].setQueen(true);
                                                        }
                                                        squares[i][j].setR(true);
                                                        squares[i][j].setMove(1);
                                                    } else {
                                                        for (Square[] square : squares) {
                                                            for (int l = 0; l < squares[i].length; l++) {
                                                                if (square[l].isD())
                                                                    square[l].setD(false);
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                }
                                for (Square[] square : squares) {
                                    for (int l = 0; l < squares[i].length; l++) {
                                        if (square[l].isD()) {
                                            square[l].setD(false);
                                        }
                                    }
                                }
                                check++;
                            case 1:
                            case 2:
                                for (Square[] square : squares) {
                                    for (int l = 0; l < squares[i].length; l++) {
                                        if (square[l].isD()) {
                                            square[l].setD(false);
                                        }
                                    }
                                }
                                squares[i][j].setAllow(true);
                                squares[i][j].setD(true);
                                if (who == 1 && check3 == 2 || who == 2 && check3 == 1 || check3 == 0)
                                    check1 = 1;
                        }
                    }
                }
            }
        }
        invalidate();
        return true;
    }
}