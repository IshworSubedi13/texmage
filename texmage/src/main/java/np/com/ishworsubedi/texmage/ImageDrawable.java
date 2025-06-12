package np.com.ishworsubedi.texmage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImageDrawable extends Drawable {

    private final Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final IconProvider iconProvider;
    private final Shape shape;
    private final Rect boundsRect = new Rect();
    private final Drawable iconDrawable;
    private final int cornerRadiusPx;

    public ImageDrawable(@NonNull Context context,
                         @NonNull IconProvider iconProvider,
                         @NonNull Shape shape,
                         int cornerRadiusPx,
                         @Nullable Typeface typeface) {

        this.iconProvider = iconProvider;
        this.shape = shape;
        this.cornerRadiusPx = cornerRadiusPx;
        this.iconDrawable = iconProvider.getIconDrawable();

        backgroundPaint.setColor(iconProvider.getBackgroundColor());
        backgroundPaint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);

        if (typeface != null) {
            textPaint.setTypeface(typeface);
        }

        // Scale text size based on density (similar to sp)
        float scaledSize = 24f * context.getResources().getDisplayMetrics().scaledDensity;
        textPaint.setTextSize(scaledSize);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect rect = getBounds();
        boundsRect.set(rect);

        // Draw shape
        switch (shape) {
            case CIRCLE:
                float radius = Math.min(rect.width(), rect.height()) / 2f;
                canvas.drawCircle(rect.centerX(), rect.centerY(), radius, backgroundPaint);
                break;
            case SQUARE:
                canvas.drawRect(rect, backgroundPaint);
                break;
            case ROUNDED:
                canvas.drawRoundRect(new RectF(rect), cornerRadiusPx, cornerRadiusPx, backgroundPaint);
                break;
        }

        // Draw image or initials
        if (iconDrawable != null) {
            int iconSize = Math.min(rect.width(), rect.height()) * 2 / 3;
            int left = rect.centerX() - iconSize / 2;
            int top = rect.centerY() - iconSize / 2;
            iconDrawable.setBounds(left, top, left + iconSize, top + iconSize);
            iconDrawable.draw(canvas);
        } else {
            String text = iconProvider.getText();
            if (text != null && !text.isEmpty()) {
                // Adjust size dynamically
                textPaint.setTextSize(rect.height() * 0.5f);
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float x = rect.centerX();
                float y = rect.centerY() - (fontMetrics.ascent + fontMetrics.descent) / 2;
                canvas.drawText(text, x, y, textPaint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        backgroundPaint.setAlpha(alpha);
        textPaint.setAlpha(alpha);
        if (iconDrawable != null) {
            iconDrawable.setAlpha(alpha);
        }
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        backgroundPaint.setColorFilter(colorFilter);
        textPaint.setColorFilter(colorFilter);
        if (iconDrawable != null) {
            iconDrawable.setColorFilter(colorFilter);
        }
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}