/*
package com.example.mycheckers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

class MyThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;

    //Square[] squares = new Square[32];
    Square a1 = new Square("a1", 1, false, false, true, false,false,
            false, false, false, false,
            false, false, false, false);
    Square c1 = new Square("c1", 1, false, false, false, false,false,
            true, false, true, false,
            false, false, false, false);
    Square e1 = new Square("e1", 1, false, false, false, false,false,
            false, false, false, false,
            true, true, false, false);
    Square g1 = new Square("g1", 1, false, false, false, true,false,
            false, false, false, false,
            false, false, false, false);
    Square b2 = new Square("b2", 1, false, false, true, false,false,
            false, false, true, false,
            false, false, false, false);
    Square d2 = new Square("d2", 1, false, false, false, false,false,
            true, false, false, false,
            true, false, false, false);
    Square f2 = new Square("f2", 1, false, false, false, true,false,
            false, false, false, false,
            false, true, false, false);
    Square h2 = new Square("h2", 1, false, false, false, false,true,
            false, false, false, false,
            false, false, false, false);
    Square a3 = new Square("a3", 1, false, false, false, false,false,
            false, true, true, false,
            false, false, false, false);
    Square c3 = new Square("c3", 1, false, false, true, false,false,
            false, false, false, false,
            true, false, false, false);
    Square e3 = new Square("e3", 1, false, false, false, true,false,
            true, false, false, false,
            false, false, false, false);
    Square g3 = new Square("g3", 1, false, false, false, false,true,
            false, false, false, false,
            false, true, false, false);
    Square b4 = new Square("b4", 0, false, false, false, false,false,
            false, true, false, false,
            true, false, false, false);
    Square d4 = new Square("d4", 0, false, false, true, true,false,
            false, false, false, false,
            false, false, false, false);
    Square f4 = new Square("f4", 0, false, false, false, false,true,
            true, false, false, false,
            false, false, false, false);
    Square h4 = new Square("h4", 0, false, false, false, false,false,
            false, false, false, false,
            false, true, true, false);
    Square a5 = new Square("a5", 0, false, false, false, false,false,
            false, false, false, false,
            true, false, false, true);
    Square c5 = new Square("c5", 0, false, false, false, true,false,
            false, true, false, false,
            false, false, false, false);
    Square e5 = new Square("e5", 0, false, false, true, false,true,
            false, false, false, false,
            false, false, false, false);
    Square g5 = new Square("g5", 0, false, false, false, false,false,
            true, false, false, false,
            false, false, true, false);
    Square b6 = new Square("b6", 2, false, false, false, true,false,
            false, false, false, false,
            false, false, false, true);
    Square d6 = new Square("d6", 2, false, false, false, false,true,
            false, true, false, false,
            false, false, false, false);
    Square f6 = new Square("f6", 2, false, false, true, false,false,
            false, false, false, false,
            false, false, true, false);
    Square h6 = new Square("h6", 2, false, false, false, false,false,
            true, false, false, true,
            false, false, false, false);
    Square a7 = new Square("a7", 2, false, false, false, true,false,
            false, false, false, false,
            false, false, false, false);
    Square c7 = new Square("c7", 2, false, false, false, false,true,
            false, false, false, false,
            false, false, false, true);
    Square e7 = new Square("e7", 2, false, false, false, false,false,
            false, true, false, false,
            false, false, true, false);
    Square g7 = new Square("g7", 2, false, false, true, false,false,
            false, false, false, true,
            false, false, false, false);
    Square b8 = new Square("b8", 2, false, false, false, false,true,
            false, false, false, false,
            false, false, false, false);
    Square d8 = new Square("d8", 2, false, false, false, false,false,
            false, false, false, false,
            false, false, true, true);
    Square f8 = new Square("f8", 2, false, false, false, false,false,
            false, true, false, true,
            false, false, false, false);
    Square h8 = new Square("h8", 2, false, false, true, false,false,
            false, false, false, false,
            false, false, false, false);

    public MyThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                Paint p = new Paint();
                p.setColor(Color.WHITE);
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);
                int x = 0, y = canvas.getHeight() / 2 - canvas.getWidth() / 2, a = 0;
                float x0 = x, y0 = y;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        canvas.drawRect(x, y, x + (float) canvas.getWidth() / 8, y + (float) canvas.getWidth() / 8, p);
                        a++;
                        p.setColor(a % 2 == 0 ? Color.WHITE : Color.rgb(128, 128, 128));
                        x += canvas.getWidth() / 8;
                    }
                    x = 0;
                    y += canvas.getWidth() / 8;
                    p.setColor(a % 2 == 1 ? Color.WHITE : Color.rgb(128, 128, 128));
                    a--;
                }
                p.setColor(Color.rgb(128, 128, 128));
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(x0, y0, canvas.getWidth() - x, y, p);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}*/
/*private int viewWidth, viewHeight;
        int c = 0;
        float touchX, touchY;
        boolean ca1, cc1, ce1, cg1, cb2, cd2, cf2, ch2, ca3, cc3, ce3, cg3, cb4, cd4, cf4, ch4, ca5, cc5, ce5, cg5, cb6, cd6, cf6, ch6, ca7, cc7, ce7, cg7, cb8, cd8, cf8, ch8 = false;
        boolean movea1, movec1, movee1, moveg1, moveb2, moved2, movef2, moveh2, movea3, movec3, movee3, moveg3, moveb4, moved4, movef4, moveh4, movea5, movec5, movee5, moveg5, moveb6, moved6, movef6, moveh6, movea7, movec7, movee7, moveg7, moveb8, moved8, movef8, moveh8 = false;
        Bitmap wc = BitmapFactory.decodeResource(getResources(), R.drawable.whitechecker);
        Bitmap bc = BitmapFactory.decodeResource(getResources(), R.drawable.blackchecker);
        Bitmap wq = BitmapFactory.decodeResource(getResources(), R.drawable.whitequeen);
        Bitmap bq = BitmapFactory.decodeResource(getResources(), R.drawable.blackqueen);
        Bitmap bs = BitmapFactory.decodeResource(getResources(), R.drawable.blacksquare);
        Rect ra1 = new Rect(0, getHeight() / 2 + getWidth() / 8 * 3, getWidth() / 8, getHeight() / 2 + getWidth() / 2);
        Rect rc1 = new Rect(getWidth() / 4, getHeight() / 2 + getWidth() / 8 * 3, getWidth() / 8 * 3, getHeight() / 2 + getWidth() / 2);
        Rect re1 = new Rect(getWidth() / 2, getHeight() / 2 + getWidth() / 8 * 3, getWidth() / 8 * 5, getHeight() / 2 + getWidth() / 2);
        Rect rg1 = new Rect(getWidth() / 4 * 3, getHeight() / 2 + getWidth() / 8 * 3, getWidth() / 8 * 7, getHeight() / 2 + getWidth() / 2);
        Rect rb2 = new Rect(getWidth() / 8, getHeight() / 2 + getWidth() / 4, getWidth() / 4, getHeight() / 2 + getWidth() / 8 * 3);
        Rect rd2 = new Rect(getWidth() / 8 * 3, getHeight() / 2 + getWidth() / 4, getWidth() / 2, getHeight() / 2 + getWidth() / 8 * 3);
        Rect rf2 = new Rect(getWidth() / 8 * 5, getHeight() / 2 + getWidth() / 4, getWidth() / 4 * 3, getHeight() / 2 + getWidth() / 8 * 3);
        Rect rh2 = new Rect(getWidth() / 8 * 7, getHeight() / 2 + getWidth() / 4, getWidth(), getHeight() / 2 + getWidth() / 8 * 3);
        Rect ra3 = new Rect(0, getHeight() / 2 + getWidth() / 8, getWidth() / 8, getHeight() / 2 + getWidth() / 4);
        Rect rc3 = new Rect(getWidth() / 4, getHeight() / 2 + getWidth() / 8, getWidth() / 8 * 3, getHeight() / 2 + getWidth() / 4);
        Rect re3 = new Rect(getWidth() / 2, getHeight() / 2 + getWidth() / 8, getWidth() / 8 * 5, getHeight() / 2 + getWidth() / 4);
        Rect rg3 = new Rect(getWidth() / 4 * 3, getHeight() / 2 + getWidth() / 8, getWidth() / 8 * 7, getHeight() / 2 + getWidth() / 4);
        Rect rb4 = new Rect(getWidth() / 8, getHeight() / 2, getWidth() / 4, getHeight() / 2 + getWidth() / 8);
        Rect rd4 = new Rect(getWidth() / 8 * 3, getHeight() / 2, getWidth() / 2, getHeight() / 2 + getWidth() / 8);
        Rect rf4 = new Rect(getWidth() / 8 * 5, getHeight() / 2, getWidth() / 4 * 3, getHeight() / 2 + getWidth() / 8);
        Rect rh4 = new Rect(getWidth() / 8 * 7, getHeight() / 2, getWidth(), getHeight() / 2 + getWidth() / 8);
        Rect ra5 = new Rect(0, getHeight() / 2 - getWidth() / 8, getWidth() / 8, getHeight() / 2);
        Rect rc5 = new Rect(getWidth() / 4, getHeight() / 2 - getWidth() / 8, getWidth() / 8 * 3, getHeight() / 2);
        Rect re5 = new Rect(getWidth() / 2, getHeight() / 2 - getWidth() / 8, getWidth() / 8 * 5, getHeight() / 2);
        Rect rg5 = new Rect(getWidth() / 4 * 3, getHeight() / 2 - getWidth() / 8, getWidth() / 8 * 7, getHeight() / 2);
        Rect rb6 = new Rect(getWidth() / 8, getHeight() / 2 - getWidth() / 4, getWidth() / 4, getHeight() / 2 - getWidth() / 8);
        Rect rd6 = new Rect(getWidth() / 8 * 3, getHeight() / 2 - getWidth() / 4, getWidth() / 2, getHeight() / 2 - getWidth() / 8);
        Rect rf6 = new Rect(getWidth() / 8 * 5, getHeight() / 2 - getWidth() / 4, getWidth() / 4 * 3, getHeight() / 2 - getWidth() / 8);
        Rect rh6 = new Rect(getWidth() / 8 * 7, getHeight() / 2 - getWidth() / 4, getWidth(), getHeight() / 2 - getWidth() / 8);
        Rect ra7 = new Rect(0, getHeight() / 2 - getWidth() / 8 * 3, getWidth() / 8, getHeight() / 2 - getWidth() / 4);
        Rect rc7 = new Rect(getWidth() / 4, getHeight() / 2 - getWidth() / 8 * 3, getWidth() / 8 * 3, getHeight() / 2 - getWidth() / 4);
        Rect re7 = new Rect(getWidth() / 2, getHeight() / 2 - getWidth() / 8 * 3, getWidth() / 8 * 5, getHeight() / 2 - getWidth() / 4);
        Rect rg7 = new Rect(getWidth() / 4 * 3, getHeight() / 2 - getWidth() / 8 * 3, getWidth() / 8 * 7, getHeight() / 2 - getWidth() / 4);
        Rect rb8 = new Rect(getWidth() / 8, getHeight() / 2 - getWidth() / 2, getWidth() / 4, getHeight() / 2 - getWidth() / 8 * 3);
        Rect rd8 = new Rect(getWidth() / 8 * 3, getHeight() / 2 - getWidth() / 2, getWidth() / 2, getHeight() / 2 - getWidth() / 8 * 3);
        Rect rf8 = new Rect(getWidth() / 8 * 5, getHeight() / 2 - getWidth() / 2, getWidth() / 4 * 3, getHeight() / 2 - getWidth() / 8 * 3);
        Rect rh8 = new Rect(getWidth() / 8 * 7, getHeight() / 2 - getWidth() / 2, getWidth(), getHeight() / 2 - getWidth() / 8 * 3);
        Square a1 = new Square("a1", 1, wc, false, false, true, false, false,
                false, false, false, false,
                false, false, false, false, ra1);
        Square c1 = new Square("c1", 1, wc, false, false, false, false, false,
                true, false, true, false,
                false, false, false, false, rc1);
        Square e1 = new Square("e1", 1, wc, false, false, false, false, false,
                false, false, false, false,
                true, true, false, false, re1);
        Square g1 = new Square("g1", 1, wc, false, false, false, true, false,
                false, false, false, false,
                false, false, false, false, rg1);
        Square b2 = new Square("b2", 1, wc, false, false, true, false, false,
                false, false, true, false,
                false, false, false, false, rb2);
        Square d2 = new Square("d2", 1, wc, false, false, false, false, false,
                true, false, false, false,
                true, false, false, false, rd2);
        Square f2 = new Square("f2", 1, wc, false, false, false, true, false,
                false, false, false, false,
                false, true, false, false, rf2);
        Square h2 = new Square("h2", 1, wc, false, false, false, false, true,
                false, false, false, false,
                false, false, false, false, rh2);
        Square a3 = new Square("a3", 1, wc, false, false, false, false, false,
                false, true, true, false,
                false, false, false, false, ra3);
        Square c3 = new Square("c3", 1, wc, false, false, true, false, false,
                false, false, false, false,
                true, false, false, false, rc3);
        Square e3 = new Square("e3", 1, wc, false, false, false, true, false,
                true, false, false, false,
                false, false, false, false, re3);
        Square g3 = new Square("g3", 1, wc, false, false, false, false, true,
                false, false, false, false,
                false, true, false, false, rg3);
        Square b4 = new Square("b4", 0, bs, false, false, false, false, false,
                false, true, false, false,
                true, false, false, false, rb4);
        Square d4 = new Square("d4", 0, bs, false, false, true, true, false,
                false, false, false, false,
                false, false, false, false, rd4);
        Square f4 = new Square("f4", 0, bs, false, false, false, false, true,
                true, false, false, false,
                false, false, false, false, rf4);
        Square h4 = new Square("h4", 0, bs, false, false, false, false, false,
                false, false, false, false,
                false, true, true, false, rh4);
        Square a5 = new Square("a5", 0, bs, false, false, false, false, false,
                false, false, false, false,
                true, false, false, true, ra5);
        Square c5 = new Square("c5", 0, bs, false, false, false, true, false,
                false, true, false, false,
                false, false, false, false, rc5);
        Square e5 = new Square("e5", 0, bs, false, false, true, false, true,
                false, false, false, false,
                false, false, false, false, re5);
        Square g5 = new Square("g5", 0, bs, false, false, false, false, false,
                true, false, false, false,
                false, false, true, false, rg5);
        Square b6 = new Square("b6", 2, bc, false, false, false, true, false,
                false, false, false, false,
                false, false, false, true, rb6);
        Square d6 = new Square("d6", 2, bc, false, false, false, false, true,
                false, true, false, false,
                false, false, false, false, rd6);
        Square f6 = new Square("f6", 2, bc, false, false, true, false, false,
                false, false, false, false,
                false, false, true, false, rf6);
        Square h6 = new Square("h6", 2, bc, false, false, false, false, false,
                true, false, false, true,
                false, false, false, false, rh6);
        Square a7 = new Square("a7", 2, bc, false, false, false, true, false,
                false, false, false, false,
                false, false, false, false, ra7);
        Square c7 = new Square("c7", 2, bc, false, false, false, false, true,
                false, false, false, false,
                false, false, false, true, rc7);
        Square e7 = new Square("e7", 2, bc, false, false, false, false, false,
                false, true, false, false,
                false, false, true, false, re7);
        Square g7 = new Square("g7", 2, bc, false, false, true, false, false,
                false, false, false, true,
                false, false, false, false, rg7);
        Square b8 = new Square("b8", 2, bc, false, false, false, false, true,
                false, false, false, false,
                false, false, false, false, rb8);
        Square d8 = new Square("d8", 2, bc, false, false, false, false, false,
                false, false, false, false,
                false, false, true, true, rd8);
        Square f8 = new Square("f8", 2, bc, false, false, false, false, false,
                false, true, false, true,
                false, false, false, false, rf8);
        Square h8 = new Square("h8", 2, bc, false, false, true, false, false,
                false, false, false, false,
                false, false, false, false, rh8);

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            viewWidth = w;
            viewHeight = h;
        }

        public MyCheckersDraw(Context context) {
            super(context);

        }

        Paint p = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);
            p.setColor(Color.WHITE);
            canvas.drawRect(0, 0, getWidth(), getHeight(), p);
            int x = 0, y = getHeight() / 2 - getWidth() / 2, a = 0;
            float x0 = x, y0 = y;
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
            canvas.drawBitmap(wc, 0, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(wc, (float) getWidth() / 4, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(wc, (float) getWidth() / 2, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(wc, (float) getWidth() / 4 * 3, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(wc, (float) getWidth() / 8, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            canvas.drawBitmap(wc, (float) getWidth() / 8 * 3, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            canvas.drawBitmap(wc, (float) getWidth() / 8 * 5, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            canvas.drawBitmap(wc, (float) getWidth() / 8 * 7, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            canvas.drawBitmap(wc, 0, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            canvas.drawBitmap(wc, (float) getWidth() / 4, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            canvas.drawBitmap(wc, (float) getWidth() / 2, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            canvas.drawBitmap(wc, (float) getWidth() / 4 * 3, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            canvas.drawBitmap(bs, (float) getWidth() / 8, (float) getHeight() / 2, p);
            canvas.drawBitmap(bs, (float) getWidth() / 8 * 3, (float) getHeight() / 2, p);
            canvas.drawBitmap(bs, (float) getWidth() / 8 * 5, (float) getHeight() / 2, p);
            canvas.drawBitmap(bs, (float) getWidth() / 8 * 7, (float) getHeight() / 2, p);
            canvas.drawBitmap(bs, 0, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            canvas.drawBitmap(bs, (float) getWidth() / 4, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            canvas.drawBitmap(bs, (float) getWidth() / 2, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            canvas.drawBitmap(bs, (float) getWidth() / 4 * 3, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8 * 3, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8 * 5, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8 * 7, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            canvas.drawBitmap(bc, 0, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(bc, (float) getWidth() / 4, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(bc, (float) getWidth() / 2, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(bc, (float) getWidth() / 4 * 3, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8, (float) getHeight() / 2 - (float) getWidth() / 2, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8 * 3, (float) getHeight() / 2 - (float) getWidth() / 2, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8 * 5, (float) getHeight() / 2 - (float) getWidth() / 2, p);
            canvas.drawBitmap(bc, (float) getWidth() / 8 * 7, (float) getHeight() / 2 - (float) getWidth() / 2, p);
            p.setColor(Color.GREEN);
            p.setStyle(Paint.Style.FILL);
            if (a1.isBorder())
                canvas.drawRect(0, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, (float) getWidth() / 8, (float) getHeight() / 2 + (float) getWidth() / 2, p);
            if (c1.isBorder())
                canvas.drawRect((float) getWidth() / 4, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, (float) getWidth() / 8 * 3, (float) getHeight() / 2 + (float) getWidth() / 2, p);
            if (e1.isBorder())
                canvas.drawRect((float) getWidth() / 2, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, (float) getWidth() / 8 * 5, (float) getHeight() / 2 + (float) getWidth() / 2, p);
            if (g1.isBorder())
                canvas.drawRect((float) getWidth() / 4 * 3, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, (float) getWidth() / 8 * 7, (float) getHeight() / 2 + (float) getWidth() / 2, p);
            if (b2.isBorder())
                canvas.drawRect((float) getWidth() / 8, (float) getHeight() / 2 + (float) getWidth() / 4, (float) getWidth() / 4, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            if (d2.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 3, (float) getHeight() / 2 + (float) getWidth() / 4, (float) getWidth() / 2, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            if (f2.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 5, (float) getHeight() / 2 + (float) getWidth() / 4, (float) getWidth() / 4 * 3, (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            if (h2.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 7, (float) getHeight() / 2 + (float) getWidth() / 4, getWidth(), (float) getHeight() / 2 + (float) getWidth() / 8 * 3, p);
            if (a3.isBorder())
                canvas.drawRect(0, (float) getHeight() / 2 + (float) getWidth() / 8, (float) getWidth() / 8, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            if (c3.isBorder())
                canvas.drawRect((float) getWidth() / 4, (float) getHeight() / 2 + (float) getWidth() / 8, (float) getWidth() / 8 * 3, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            if (e3.isBorder())
                canvas.drawRect((float) getWidth() / 2, (float) getHeight() / 2 + (float) getWidth() / 8, (float) getWidth() / 8 * 5, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            if (g3.isBorder())
                canvas.drawRect((float) getWidth() / 4 * 3, (float) getHeight() / 2 + (float) getWidth() / 8, (float) getWidth() / 8 * 7, (float) getHeight() / 2 + (float) getWidth() / 4, p);
            if (b4.isBorder())
                canvas.drawRect((float) getWidth() / 8, (float) getHeight() / 2, (float) getWidth() / 4, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            if (d4.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 3, (float) getHeight() / 2, (float) getWidth() / 2, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            if (f4.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 5, (float) getHeight() / 2, (float) getWidth() / 4 * 3, (float) getHeight() / 2 + (float) getWidth() / 8, p);
            if (h4.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 7, (float) getHeight() / 2, (float) getWidth(), (float) getHeight() / 2 + (float) getWidth() / 8, p);
            if (a5.isBorder())
                canvas.drawRect(0, (float) getHeight() / 2 - (float) getWidth() / 8, (float) getWidth() / 8, (float) getHeight() / 2, p);
            if (c5.isBorder())
                canvas.drawRect((float) getWidth() / 4, (float) getHeight() / 2 - (float) getWidth() / 8, (float) getWidth() / 8 * 3, (float) getHeight() / 2, p);
            if (e5.isBorder())
                canvas.drawRect((float) getWidth() / 2, (float) getHeight() / 2 - (float) getWidth() / 8, (float) getWidth() / 8 * 5, (float) getHeight() / 2, p);
            if (g5.isBorder())
                canvas.drawRect((float) getWidth() / 4 * 3, (float) getHeight() / 2 - (float) getWidth() / 8, (float) getWidth() / 8 * 7, (float) getHeight() / 2, p);
            if (b6.isBorder())
                canvas.drawRect((float) getWidth() / 8, (float) getHeight() / 2 - (float) getWidth() / 4, (float) getWidth() / 4, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            if (d6.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 3, (float) getHeight() / 2 - (float) getWidth() / 4, (float) getWidth() / 2, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            if (f6.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 5, (float) getHeight() / 2 - (float) getWidth() / 4, (float) getWidth() / 4 * 3, (float) getHeight() / 2 - (float) getWidth() / 8, p);
            if (h6.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 7, (float) getHeight() / 2 - (float) getWidth() / 4, (float) getWidth(), (float) getHeight() / 2 - (float) getWidth() / 8, p);
            if (a7.isBorder())
                canvas.drawRect(0, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, (float) getWidth() / 8, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            if (c7.isBorder())
                canvas.drawRect((float) getWidth() / 4, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, (float) getWidth() / 8 * 3, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            if (e7.isBorder())
                canvas.drawRect((float) getWidth() / 2, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, (float) getWidth() / 8 * 5, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            if (g7.isBorder())
                canvas.drawRect((float) getWidth() / 4 * 3, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, (float) getWidth() / 8 * 7, (float) getHeight() / 2 - (float) getWidth() / 4, p);
            if (b8.isBorder())
                canvas.drawRect((float) getWidth() / 8, (float) getHeight() / 2 - (float) getWidth() / 2, (float) getWidth() / 4, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            if (d8.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 3, (float) getHeight() / 2 - (float) getWidth() / 2, (float) getWidth() / 2, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            if (f8.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 5, (float) getHeight() / 2 - (float) getWidth() / 2, (float) getWidth() / 4 * 3, (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            if (h8.isBorder())
                canvas.drawRect((float) getWidth() / 8 * 7, (float) getHeight() / 2 - (float) getWidth() / 2, getWidth(), (float) getHeight() / 2 - (float) getWidth() / 8 * 3, p);
            */
/*if (touchX > 0 && touchX < (float) getWidth() / 8 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 + (float) getWidth() / 2) {
                    ca1 = true;
                } else if (touchX > (float) getWidth() / 4 && touchX < (float) getWidth() / 8 * 3 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 + (float) getWidth() / 2) {
                    cc1 = true;
                } else if (touchX > (float) getWidth() / 2 && touchX < (float) getWidth() / 8 * 5 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 + (float) getWidth() / 2) {
                    ce1 = true;
                } else if (touchX > (float) getWidth() / 4 * 3 && touchX < (float) getWidth() / 8 * 7 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 + (float) getWidth() / 2) {
                    cg1 = true;
                } else if (touchX > (float) getWidth() / 8 && touchX < (float) getWidth() / 4 && touchY > (float) getHeight() / 2 + (float) getWidth() / 4 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8 * 3) {
                    cb2 = true;
                } else if (touchX > (float) getWidth() / 8 * 3 && touchX < (float) getWidth() / 2 && touchY > (float) getHeight() / 2 + (float) getWidth() / 4 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8 * 3) {
                    cd2 = true;
                } else if (touchX > (float) getWidth() / 8 * 5 && touchX < (float) getWidth() / 4 * 3 && touchY > (float) getHeight() / 2 + (float) getWidth() / 4 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8 * 3) {
                    cf2 = true;
                } else if (touchX > (float) getWidth() / 8 * 7 && touchX < getWidth() && touchY > (float) getHeight() / 2 + (float) getWidth() / 4 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8 * 3) {
                    ch2 = true;
                } else if (touchX > 0 && touchX < (float) getWidth() / 8 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 && touchY < (float) getHeight() / 2 + (float) getWidth() / 4) {
                    ca3 = true;
                } else if (touchX > (float) getWidth() / 4 && touchX < (float) getWidth() / 8 * 3 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 && touchY < (float) getHeight() / 2 + (float) getWidth() / 4) {
                    if (c3.getBitmap() == wc) {
                        if (b4.getBitmap() == bs || b4.getBitmap() == bc && a5.getBitmap() == bs)
                            b4.setBorder(true);
                        if (d4.getBitmap() == bs || d4.getBitmap() == bc && e5.getBitmap() == bs)
                            d4.setBorder(true);
                        cc3 = true;
                    }
                } else if (touchX > (float) getWidth() / 2 && touchX < (float) getWidth() / 8 * 5 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 && touchY < (float) getHeight() / 2 + (float) getWidth() / 4) {
                    if (e3.getBitmap() == wc) {
                        if (d4.getBitmap() == bs || d4.getBitmap() == bc && c5.getBitmap() == bs)
                            d4.setBorder(true);
                        if (f4.getBitmap() == bs || f4.getBitmap() == bc && g5.getBitmap() == bs)
                            f4.setBorder(true);
                        ce3 = true;
                    }
                } else if (touchX > (float) getWidth() / 4 * 3 && touchX < (float) getWidth() / 8 * 7 && touchY > (float) getHeight() / 2 + (float) getWidth() / 8 && touchY < (float) getHeight() / 2 + (float) getWidth() / 4) {
                    if (g3.getBitmap() == wc) {
                        if (f4.getBitmap() == bs || f4.getBitmap() == bc && e5.getBitmap() == bs)
                            f4.setBorder(true);
                        if (h4.getBitmap() == bs)
                            h4.setBorder(true);
                        cg3 = true;
                    }
                } else if (touchX > (float) getWidth() / 8 && touchX < (float) getWidth() / 4 && touchY > (float) getHeight() / 2 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8) {
                    if (b4.isBorder()) {
                        if (ca3) {
                            a3.setColor(0);
                            a3.setBitmap(bs);
                        } else {
                            c3.setColor(0);
                            c3.setBitmap(bs);
                            d4.setBorder(false);
                        }
                        b4.setBorder(false);
                        b4.setColor(1);
                        b4.setBitmap(wc);
                        cb4 = true;
                    }

                } else if (touchX > (float) getWidth() / 8 * 3 && touchX < (float) getWidth() / 2 && touchY > (float) getHeight() / 2 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8) {
                    if (d4.isBorder()) {
                        if (cc3) {
                            c3.setColor(0);
                            c3.setBitmap(bs);
                            b4.setBorder(false);
                        } else {
                            e3.setColor(0);
                            e3.setBitmap(bs);
                            f4.setBorder(false);
                        }
                        d4.setBorder(false);
                        d4.setColor(1);
                        d4.setBitmap(wc);
                        cd4 = true;

                    }

                } else if (touchX > (float) getWidth() / 8 * 5 && touchX < (float) getWidth() / 4 * 3 && touchY > (float) getHeight() / 2 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8) {
                    if (f4.isBorder()) {
                        if (ce3) {
                            e3.setColor(0);
                            e3.setBitmap(bs);
                            d4.setBorder(false);
                        } else {
                            g3.setColor(0);
                            g3.setBitmap(bs);
                            h4.setBorder(false);
                        }
                        f4.setBorder(false);
                        f4.setColor(1);
                        f4.setBitmap(wc);
                        cf4 = true;
                    }

                } else if (touchX > (float) getWidth() / 8 * 7 && touchX < getWidth() && touchY > (float) getHeight() / 2 && touchY < (float) getHeight() / 2 + (float) getWidth() / 8) {
                    ch4 = true;
                } else if (touchX > 0 && touchX < (float) getWidth() / 8 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 && touchY < (float) getHeight() / 2) {
                    ca5 = true;
                } else if (touchX > (float) getWidth() / 4 && touchX < (float) getWidth() / 8 * 3 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 && touchY < (float) getHeight() / 2) {
                    cc5 = true;
                } else if (touchX > (float) getWidth() / 2 && touchX < (float) getWidth() / 8 * 5 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 && touchY < (float) getHeight() / 2) {
                    ce5 = true;
                } else if (touchX > (float) getWidth() / 4 * 3 && touchX < (float) getWidth() / 8 * 7 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 && touchY < (float) getHeight() / 2) {
                    cg5 = true;
                } else if (touchX > (float) getWidth() / 8 && touchX < (float) getWidth() / 4 && touchY > (float) getHeight() / 2 - (float) getWidth() / 4 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8) {
                    cb6 = true;
                } else if (touchX > (float) getWidth() / 8 * 3 && touchX < (float) getWidth() / 2 && touchY > (float) getHeight() / 2 - (float) getWidth() / 4 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8) {
                    cd6 = true;
                } else if (touchX > (float) getWidth() / 8 * 5 && touchX < (float) getWidth() / 4 * 3 && touchY > (float) getHeight() / 2 - (float) getWidth() / 4 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8) {
                    cf6 = true;
                } else if (touchX > (float) getWidth() / 8 * 7 && touchX < getWidth() && touchY > (float) getHeight() / 2 - (float) getWidth() / 4 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8) {
                    ch6 = true;
                } else if (touchX > 0 && touchX < (float) getWidth() / 8 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 - (float) getWidth() / 4) {
                    ca7 = true;
                } else if (touchX > (float) getWidth() / 4 && touchX < (float) getWidth() / 8 * 3 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 - (float) getWidth() / 4) {
                    cc7 = true;
                } else if (touchX > (float) getWidth() / 2 && touchX < (float) getWidth() / 8 * 5 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 - (float) getWidth() / 4) {
                    ce7 = true;
                } else if (touchX > (float) getWidth() / 4 * 3 && touchX < (float) getWidth() / 8 * 7 && touchY > (float) getHeight() / 2 - (float) getWidth() / 8 * 3 && touchY < (float) getHeight() / 2 - (float) getWidth() / 4) {
                    cg7 = true;
                } else if (touchX > (float) getWidth() / 8 && touchX < (float) getWidth() / 4 && touchY > (float) getHeight() / 2 - (float) getWidth() / 2 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8 * 3) {
                    cb8 = true;
                } else if (touchX > (float) getWidth() / 8 * 3 && touchX < (float) getWidth() / 2 && touchY > (float) getHeight() / 2 - (float) getWidth() / 2 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8 * 3) {
                    cd8 = true;
                } else if (touchX > (float) getWidth() / 8 * 5 && touchX < (float) getWidth() / 4 * 3 && touchY > (float) getHeight() / 2 - (float) getWidth() / 2 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8 * 3) {
                    cf8 = true;
                } else if (touchX > (float) getWidth() / 8 * 7 && touchX < getWidth() && touchY > (float) getHeight() / 2 - (float) getWidth() / 2 && touchY < (float) getHeight() / 2 - (float) getWidth() / 8 * 3) {
                    ch8 = true;
                }*/
/*private String name;
    private int color;
    private Bitmap bitmap;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private boolean queen;
    private boolean border;
    private boolean a1h8;
    private boolean g1a7;
    private boolean h2b8;
    private boolean c1h6;
    private boolean a3f8;
    private boolean c1a3;
    private boolean h6f8;
    private boolean e1a5;
    private boolean e1h4;
    private boolean h4d8;
    private boolean a5d8;

    public Square(String name, int color, Bitmap bitmap, int left, int top, int right, int bottom, boolean queen, boolean border, boolean a1h8, boolean g1a7, boolean h2b8,
                  boolean c1h6, boolean a3f8, boolean c1a3, boolean h6f8,
                  boolean e1a5, boolean e1h4, boolean h4d8, boolean a5d8) {
        this.name = name;
        this.color = color;
        this.bitmap = bitmap;
        this.queen = queen;
        this.border = border;
        this.a1h8 = a1h8;
        this.g1a7 = g1a7;
        this.h2b8 = h2b8;
        this.c1h6 = c1h6;
        this.a3f8 = a3f8;
        this.c1a3 = c1a3;
        this.h6f8 = h6f8;
        this.e1a5 = e1a5;
        this.e1h4 = e1h4;
        this.h4d8 = h4d8;
        this.a5d8 = a5d8;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isQueen() {
        return queen;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public boolean isA1h8() {
        return a1h8;
    }

    public void setA1h8(boolean a1h8) {
        this.a1h8 = a1h8;
    }

    public boolean isG1a7() {
        return g1a7;
    }

    public void setG1a7(boolean g1a7) {
        this.g1a7 = g1a7;
    }

    public boolean isH2b8() {
        return h2b8;
    }

    public void setH2b8(boolean h2b8) {
        this.h2b8 = h2b8;
    }

    public boolean isC1h6() {
        return c1h6;
    }

    public void setC1h6(boolean c1h6) {
        this.c1h6 = c1h6;
    }

    public boolean isA3f8() {
        return a3f8;
    }

    public void setA3f8(boolean a3f8) {
        this.a3f8 = a3f8;
    }

    public boolean isC1a3() {
        return c1a3;
    }

    public void setC1a3(boolean c1a3) {
        this.c1a3 = c1a3;
    }

    public boolean isH6f8() {
        return h6f8;
    }

    public void setH6f8(boolean h6f8) {
        this.h6f8 = h6f8;
    }

    public boolean isE1a5() {
        return e1a5;
    }

    public void setE1a5(boolean e1a5) {
        this.e1a5 = e1a5;
    }

    public boolean isE1h4() {
        return e1h4;
    }

    public void setE1h4(boolean e1h4) {
        this.e1h4 = e1h4;
    }

    public boolean isH4d8() {
        return h4d8;
    }

    public void setH4d8(boolean h4d8) {
        this.h4d8 = h4d8;
    }

    public boolean isA5d8() {
        return a5d8;
    }

    public void setA5d8(boolean a5d8) {
        this.a5d8 = a5d8;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }*/
/*if (squares[i][j].isMovew() && who == 1) {//ход белой шашки
                        drawSquare(canvas, squares[i][j], wc);
                        if (squares[i][j].isR()) {
                            drawSquare(canvas, squares[i - 1][j - 1], bs);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            drawSquare(canvas, squares[i + 1][j - 1], bs);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setMovew(false);
                        who = 0;
                        check = 0;
                    }//ход чёрной шашки
                    if (squares[i][j].isMoveb() && who == 2) {
                        drawSquare(canvas, squares[i][j], bc);
                        if (squares[i][j].isR()) {
                            drawSquare(canvas, squares[i - 1][j + 1], bs);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            drawSquare(canvas, squares[i + 1][j + 1], bs);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setMoveb(false);
                        who = 0;
                        check = 0;
                    }*/
/*if (squares[i][j].isFightw()) {
                        drawSquare(canvas, squares[i][j], wc);
                        if (squares[i][j].isR()) {
                            drawSquare(canvas, squares[i - 1][j - 1], bs);
                            drawSquare(canvas, squares[i - 2][j - 2], bs);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            drawSquare(canvas, squares[i + 1][j - 1], bs);
                            drawSquare(canvas, squares[i + 2][j - 2], bs);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setFightw(false);
                    }
                    if (squares[i][j].isFightb()) {
                        drawSquare(canvas, squares[i][j], bc);
                        if (squares[i][j].isR()) {
                            drawSquare(canvas, squares[i - 1][j + 1], bs);
                            drawSquare(canvas, squares[i - 2][j + 2], bs);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL()) {
                            drawSquare(canvas, squares[i + 1][j + 1], bs);
                            drawSquare(canvas, squares[i + 2][j + 2], bs);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setFightb(false);
                    }*/
/*if (squares[i][j].isFightw() && who == 1) {
                        drawSquare(canvas, squares[i][j], wc);
                        if (squares[i][j].isR() && check == 1) {
                            drawSquare(canvas, squares[i - 1][j - 1], bs);
                            drawSquare(canvas, squares[i - 2][j - 2], bs);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL() && check == 1) {
                            drawSquare(canvas, squares[i + 1][j - 1], bs);
                            drawSquare(canvas, squares[i + 2][j - 2], bs);
                            squares[i][j].setL(false);
                        } else if (squares[i][j].isR() && check == 2) {
                            drawSquare(canvas, squares[i - 1][j + 1], bs);
                            drawSquare(canvas, squares[i - 2][j + 2], bs);
                            squares[i][j].setL(false);
                        } else if (squares[i][j].isL() && check == 2) {
                            drawSquare(canvas, squares[i + 1][j + 1], bs);
                            drawSquare(canvas, squares[i + 2][j + 2], bs);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setFightw(false);
                        who = 0;
                        check = 0;
                    }
                    if (squares[i][j].isFightb() && who == 2) {
                        canvas.drawText("" + who, 100, 100, p);
                        drawSquare(canvas, squares[i][j], bc);
                        if (squares[i][j].isR() && check == 2) {
                            drawSquare(canvas, squares[i - 1][j + 1], bs);
                            drawSquare(canvas, squares[i - 2][j + 2], bs);
                            squares[i][j].setR(false);
                        } else if (squares[i][j].isL() && check == 2) {
                            drawSquare(canvas, squares[i + 1][j + 1], bs);
                            drawSquare(canvas, squares[i + 2][j + 2], bs);
                            squares[i][j].setL(false);
                        } else if (squares[i][j].isR() && check == 1) {
                            drawSquare(canvas, squares[i + 1][j - 1], bs);
                            drawSquare(canvas, squares[i + 2][j - 2], bs);
                            squares[i][j].setL(false);
                        } else if (squares[i][j].isL() && check == 1) {
                            drawSquare(canvas, squares[i + 1][j - 1], bs);
                            drawSquare(canvas, squares[i + 2][j - 2], bs);
                            squares[i][j].setL(false);
                        }
                        squares[i][j].setFightb(false);
                        who = 0;
                        check = 0;
                    }*/