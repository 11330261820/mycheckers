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
    int check = 0;

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
                            squares[i][j] = new Square(null, 0, 0, 3, false, false, false, false, false, false, false, false, 0);
                            break;
                        case 1:
                            squares[i][j] = new Square(wc, i + 1, j + 1, 1, false, false, false, false, false, false, false, false, 0);
                            break;
                        case 3:
                            squares[i][j] = new Square(bs, i + 1, j + 1, 0, false, false, false, false, false, false, false, false, 0);
                            break;
                        case 5:
                        case 7:
                            squares[i][j] = new Square(bc, i + 1, j + 1, 2, false, false, false, false, false, false, false, false, 0);
                            break;
                    }
                } else {
                    switch (j) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                            squares[i][j] = new Square(null, 0, 0, 3, false, false, false, false, false, false, false, false, 0);
                            break;
                        case 0:
                        case 2:
                            squares[i][j] = new Square(wc, i + 1, j + 1, 1, false, false, false, false, false, false, false, false, 0);
                            break;
                        case 4:
                            squares[i][j] = new Square(bs, i + 1, j + 1, 0, false, false, false, false, false, false, false, false, 0);
                            break;
                        case 6:
                            squares[i][j] = new Square(bc, i + 1, j + 1, 2, false, false, false, false, false, false, false, false, 0);
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
        if (s.getB() == wc)
            s.setColor(1);
        else if (s.getB() == bc)
            s.setColor(2);
        else if (s.getB() == bs)
            s.setColor(0);
        canvas.drawBitmap(b, (s.getV() - 1) * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((8 - s.getH()) * getWidth() / 8), p);
    }

    public void drawAllow(Canvas canvas, Square s, Paint p) {
        p.setColor(Color.GREEN);
        canvas.drawRect((s.getV() - 1) * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((8 - s.getH()) * getWidth() / 8), s.getV() * getWidth() / 8, getHeight() / 2 - getWidth() / 2 + ((9 - s.getH()) * getWidth() / 8), p);
    }

    public void move(Canvas canvas, Square s1, Square s2, int color) {
        if (color == 1)
            drawSquare(canvas, s1, wc);
        else if (color == 2)
            drawSquare(canvas, s1, bc);
        drawSquare(canvas, s2, bs);
    }

    public void fight(Canvas canvas, Square s1, Square s2, Square s3, int color) {
        if (color == 1)
            drawSquare(canvas, s1, wc);
        else if (color == 2)
            drawSquare(canvas, s1, bc);
        drawSquare(canvas, s2, bs);
        drawSquare(canvas, s3, bs);
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
                    switch (j) {
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            if (squares[i][j].getColor() == 1) {
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
                            } else if (squares[i][j].getColor() == 2) {
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
                            if (squares[i][j].getColor() == 1) {
                                if (i >= 1 && squares[i - 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i - 1][j + 1], p);
                                if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i + 1][j + 1], p);
                                if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 2)
                                    drawAllow(canvas, squares[i - 2][j + 2], p);
                                if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 2)
                                    drawAllow(canvas, squares[i + 2][j + 2], p);
                            } else if (squares[i][j].getColor() == 2) {
                                if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 1)
                                    drawAllow(canvas, squares[i - 2][j + 2], p);
                                if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 1)
                                    drawAllow(canvas, squares[i + 2][j + 2], p);
                            }
                            break;
                        case 1:
                            if (squares[i][j].getColor() == 1) {
                                if (i >= 1 && squares[i - 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i - 1][j + 1], p);
                                if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i + 1][j + 1], p);
                                if (i >= 2 && squares[i - 2][j + 2].getColor() == 0 && squares[i - 1][j + 1].getColor() == 2)
                                    drawAllow(canvas, squares[i - 2][j + 2], p);
                                if (i <= 5 && squares[i + 2][j + 2].getColor() == 0 && squares[i + 1][j + 1].getColor() == 2)
                                    drawAllow(canvas, squares[i + 2][j + 2], p);
                            } else if (squares[i][j].getColor() == 2) {
                                if (i >= 1 && squares[i - 1][j - 1].getColor() == 0)
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
                            if (squares[i][j].getColor() == 1) {
                                if (i >= 1 && squares[i - 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i - 1][j + 1], p);
                                if (i <= 6 && squares[i + 1][j + 1].getColor() == 0)
                                    drawAllow(canvas, squares[i + 1][j + 1], p);
                                if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 2)
                                    drawAllow(canvas, squares[i - 2][j - 2], p);
                                if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 2)
                                    drawAllow(canvas, squares[i + 2][j - 2], p);
                            } else if (squares[i][j].getColor() == 2) {
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
                            if (squares[i][j].getColor() == 1) {//подсветка полей
                                if (i >= 2 && squares[i - 2][j - 2].getColor() == 0 && squares[i - 1][j - 1].getColor() == 2)
                                    drawAllow(canvas, squares[i - 2][j - 2], p);
                                if (i <= 5 && squares[i + 2][j - 2].getColor() == 0 && squares[i + 1][j - 1].getColor() == 2)
                                    drawAllow(canvas, squares[i + 2][j - 2], p);
                            } else if (squares[i][j].getColor() == 2) {
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
                    }
                    /*ход белой шашки*/
                    if (squares[i][j].isMovew()) {
                        if (squares[i][j].isR()) {
                            move(canvas, squares[i][j], squares[i - 1][j - 1], 1);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            move(canvas, squares[i][j], squares[i + 1][j - 1], 1);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setMovew(false);
                    }
                    /*ход чёрной шашки*/
                    if (squares[i][j].isMoveb()) {
                        if (squares[i][j].isR()) {
                            move(canvas, squares[i][j], squares[i - 1][j + 1], 2);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            move(canvas, squares[i][j], squares[i + 1][j + 1], 2);
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
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], 1);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], 1);
                                        break;
                                }
                                break;
                            case 6:
                            case 7:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], 1);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], 1);
                                        break;
                                }
                                break;
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], 1);
                                        break;
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], 1);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], 1);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], 1);
                                        break;
                                }
                                break;
                        }
                        squares[i][j].setMove(0);
                    }
                    /*взятие чёрной шашкой*/
                    else if (squares[i][j].isFightb()) {
                        switch (i) {
                            case 0:
                            case 1:
                                switch (squares[i][j].getMove()) {
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], 2);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], 2);
                                        break;
                                }
                                break;
                            case 6:
                            case 7:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], 2);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], 2);
                                        break;
                                }
                                break;
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                switch (squares[i][j].getMove()) {
                                    case 1:
                                        fight(canvas, squares[i][j], squares[i - 1][j - 1], squares[i - 2][j - 2], 2);
                                        break;
                                    case 2:
                                        fight(canvas, squares[i][j], squares[i + 1][j - 1], squares[i + 2][j - 2], 2);
                                        break;
                                    case 3:
                                        fight(canvas, squares[i][j], squares[i + 1][j + 1], squares[i + 2][j + 2], 2);
                                        break;
                                    case 4:
                                        fight(canvas, squares[i][j], squares[i - 1][j + 1], squares[i - 2][j + 2], 2);
                                        break;
                                }
                                break;
                        }
                        squares[i][j].setMove(0);
                    }
                }
                canvas.drawText("" + (squares[i][j].isD() ? 1 : 0), (8 * j + i + 1) * 16, 150, p);
                canvas.drawText("" + squares[i][j].getColor(), (8 * j + i + 1) * 16, 75, p);
                canvas.drawText("" + check, 100, 200, p);
                canvas.drawText("" + squares[i][j].isMovew(), 100, 250, p);
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
                        switch (squares[i][j].getColor()){
                            case 0:
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
                                                    if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2)
                                                        squares[i][j].setFightb(true);
                                                    else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1)
                                                        squares[i][j].setFightw(true);
                                                    squares[i][j].setR(true);
                                                    squares[i][j].setMove(1);
                                                } else if (squares[i + 2][j - 2].isD()) {
                                                    if (squares[i + 1][j - 1].getColor() == 1 && squares[i + 2][j - 2].getColor() == 2)
                                                        squares[i][j].setFightb(true);
                                                    else if (squares[i + 1][j - 1].getColor() == 2 && squares[i + 2][j - 2].getColor() == 1)
                                                        squares[i][j].setFightw(true);
                                                    squares[i][j].setL(true);
                                                    squares[i][j].setMove(2);
                                                } else if (squares[i - 2][j + 2].isD()) {
                                                    if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2)
                                                        squares[i][j].setFightb(true);
                                                    else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                        squares[i][j].setFightw(true);
                                                    squares[i][j].setR(true);
                                                    squares[i][j].setMove(4);
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
                                                if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
                                                    squares[i][j].setMoveb(true);
                                                    squares[i][j].setR(true);
                                                } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                    squares[i][j].setMoveb(true);
                                                    squares[i][j].setL(true);
                                                } else if (squares[i - 2][j + 2].isD()) {
                                                    if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2)
                                                        squares[i][j].setFightb(true);
                                                    else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                        squares[i][j].setFightw(true);
                                                    squares[i][j].setR(true);
                                                    squares[i][j].setMove(4);
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
                                                } else if (squares[i - 2][j + 2].isD()) {
                                                    if (squares[i - 1][j + 1].getColor() == 1 && squares[i - 2][j + 2].getColor() == 2)
                                                        squares[i][j].setFightb(true);
                                                    else if (squares[i - 1][j + 1].getColor() == 2 && squares[i - 2][j + 2].getColor() == 1)
                                                        squares[i][j].setFightw(true);
                                                    squares[i][j].setR(true);
                                                    squares[i][j].setMove(4);
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
                                            case 7:
                                                if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
                                                    squares[i][j].setR(true);
                                                } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
                                                    squares[i][j].setL(true);
                                                } else if (squares[i - 2][j - 2].isD()) {
                                                    if (squares[i - 1][j - 1].getColor() == 1 && squares[i - 2][j - 2].getColor() == 2)
                                                        squares[i][j].setFightb(true);
                                                    else if (squares[i - 1][j - 1].getColor() == 2 && squares[i - 2][j - 2].getColor() == 1)
                                                        squares[i][j].setFightw(true);
                                                    squares[i][j].setR(true);
                                                    squares[i][j].setMove(1);
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
                                    case 1:
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
                                                if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
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
                                            case 7:
                                                if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
                                                    squares[i][j].setR(true);
                                                } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
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
                                    case 6:
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
                                                } else if (squares[i + 1][j + 1].isD() && squares[i + 1][j + 1].getColor() == 2) {
                                                    squares[i][j].setMoveb(true);
                                                    squares[i][j].setL(true);
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
                                            case 7:
                                                if (squares[i - 1][j - 1].isD() && squares[i - 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
                                                    squares[i][j].setR(true);
                                                } else if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
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
                                            case 3:
                                            case 4:
                                            case 5:
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
                                                if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
                                                    squares[i][j].setL(true);
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
                                            case 7:
                                                if (squares[i + 1][j - 1].isD() && squares[i + 1][j - 1].getColor() == 1) {
                                                    squares[i][j].setMovew(true);
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
                                            case 2:
                                            case 3:
                                            case 4:
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
                                            case 0:
                                                if (squares[i - 1][j + 1].isD() && squares[i - 1][j + 1].getColor() == 2) {
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
                                            case 6:
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
                                }
                                for (Square[] square : squares) {
                                    for (int l = 0; l < squares[i].length; l++) {
                                        if (square[l].isD())
                                            square[l].setD(false);
                                    }
                                }
                            case 1:
                                for (Square[] square : squares) {
                                    for (int l = 0; l < squares[i].length; l++) {
                                        if (square[l].isD()) {
                                            square[l].setD(false);
                                        }
                                    }
                                }
                                squares[i][j].setAllow(true);
                                squares[i][j].setD(true);
                                check++;
                            case 2:
                                for (Square[] square : squares) {
                                    for (int l = 0; l < squares[i].length; l++) {
                                        if (square[l].isD())
                                            square[l].setD(false);
                                    }
                                }
                                squares[i][j].setAllow(true);
                                squares[i][j].setD(true);
                        }
                    }
                }
            }
        }
        invalidate();
        return true;
    }
}
//Салман Радуев, Шамиль Басаев, Руслан Гилаев, Ушат Помоев, Улов Налимов, Рекорд Надоев, Отряд Ковбоев, Подрыв Устоев,
// Погром Евреев, Поджог Сараев, Захват Покоев, Исход Изгоев, Побег Злодеев, Обвал Забоев, Угон Харлеев, Удел Плебеев,
// Камаз Отходов, Развод Супругов, Забег Дебилов, Парад Уродов, Вагон Гондонов, Отряд Кретинов, Улов Кальмаров, Рулон Обоев,
// Черёд Застоев, Квартет Гобоев, Подсуд Злодеев, Друган Братанов, Учёт Расходов, Налог Сдоходов, Разбор Полётов, Мешок Лимонов,
// Обед Лемуров, Карман Пистонов, Разгул Гормонов, Прыжок Гиббонов, Рожок Патронов, Разрез Батонов, Полёт Фазанов, Удар Морозов,
// Майор Допросов, Пробрал Поносов, Заплыв Матросов, Порвал Баянов, Расстрел Арабов, Запой Гусаров, Сачок Моллюсков,
// Поход Гераклов, Барак Монголов, Загон Баранов, Пропой Погонов, Отряд Маньяков, Тридня Запоев, Облом Пиндосов,
// Курган Отбросов, Полно Засосов, Говно Вопросов.