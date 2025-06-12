package np.com.ishworsubedi.texmage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Utils {
    public static String getInitials(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }

        String[] parts = fullName.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (int i = 0; i < parts.length && i < 2; i++) {
            if (!parts[i].isEmpty()) {
                initials.append(Character.toUpperCase(parts[i].charAt(0)));
            }
        }

        return initials.toString();
    }

    public static Bitmap circularCrop(Bitmap bitmap) {
        if (bitmap == null) return null;
        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap squareBitmap = Bitmap.createBitmap(bitmap,
                (bitmap.getWidth() - size) / 2,
                (bitmap.getHeight() - size) / 2,
                size,
                size);
        Bitmap mask = circle(size, size, size / 2);
        return applyMask(squareBitmap, mask);
    }

    public static Bitmap circle(int width, int height, int radius) {
        Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(mask);
        android.graphics.Paint paint = new android.graphics.Paint();
        paint.setAntiAlias(true);
        paint.setColor(android.graphics.Color.WHITE);
        canvas.drawCircle(width / 2f, height / 2f, radius, paint);
        return mask;
    }
    public static Bitmap applyMask(Bitmap source, Bitmap mask) {
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        paint.setXfermode(null);
        return result;
    }


}
