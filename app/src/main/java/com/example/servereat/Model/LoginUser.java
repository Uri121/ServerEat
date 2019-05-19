package com.example.servereat.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class LoginUser {
    public static User mCurrentUser;
    public static Requset mCurrentOrder;


    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHight)
    {
        Bitmap scaleBitmap = Bitmap.createBitmap(newWidth,newHight,Bitmap.Config.ARGB_8888);
        float scaleX = newWidth/(float)bitmap.getWidth();
        float scaley = newHight/(float)bitmap.getHeight();

        float pivotX = 0, pivotY = 0;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaley,pivotX,pivotY);

        Canvas canvas= new Canvas(scaleBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaleBitmap;
    }
}
