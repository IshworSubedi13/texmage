package np.com.ishworsubedi.texmage;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

public class IconProvider {
    @Nullable
    private final Drawable iconDrawable;
    @Nullable
    private final String imageUrl;
    private final int backgroundColor;
    private final String text;

    public IconProvider(@Nullable Drawable iconDrawable, @Nullable String imageUrl, int backgroundColor, String text) {
        this.iconDrawable = iconDrawable;
        this.imageUrl = imageUrl;
        this.backgroundColor = backgroundColor;
        this.text = text;
    }

    @Nullable
    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getText() {
        return text;
    }
}
