package np.com.ishworsubedi.texmage;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {
    public static void loadImage(Context context, ImageView imageView,
                                 IconProvider iconProvider,
                                 Shape shape,
                                 int cornerRadiusPx,
                                 Typeface typeface,
                                 String cacheKey) {

        ImageDrawable cachedAvatar = ImageCache.getInstance().get(cacheKey);
        if (cachedAvatar != null) {
            imageView.setImageDrawable(cachedAvatar);
            return;
        }

        ImageDrawable fallbackDrawable = new ImageDrawable(context, iconProvider, shape, cornerRadiusPx, typeface);

        ImageCache.getInstance().put(cacheKey, fallbackDrawable);

        String url = iconProvider.getImageUrl();

        if (url != null && !url.isEmpty()) {
            Glide.with(context)
                    .load(url)
                    .apply(new RequestOptions()
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(fallbackDrawable)
                            .placeholder(fallbackDrawable)
                    )
                    .into(imageView);
        } else {
            imageView.setImageDrawable(fallbackDrawable);
        }
    }
}
