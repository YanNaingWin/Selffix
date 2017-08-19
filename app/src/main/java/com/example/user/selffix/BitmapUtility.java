package com.example.user.selffix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Dell on 12/11/2016.
 */

public class BitmapUtility {

    private static BitmapUtility bitmapUtil;

    public static BitmapUtility newInstance() {
        return new BitmapUtility();
    }

    public String convetStringFromBitmap(String filePath) {

        Bitmap bitmap = scaleDownBitMap(filePath);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream);
        byte[] bytearray = outputStream.toByteArray();
        String imageString = Base64.encodeToString(bytearray, Base64.NO_WRAP);
        return imageString;
    }

    public Bitmap scaleDownBitMap(String filePath) {
        Bitmap reusltbitmap = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            reusltbitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() / 6), (int) (bitmap.getHeight() / 6), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reusltbitmap;
    }

    public Bitmap convertBitmapFromString(String imageString) {
        Bitmap bitmap = null;
        try {
            byte[] imageShow = Base64.decode(imageString, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(imageShow, 0, imageShow.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public  Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
