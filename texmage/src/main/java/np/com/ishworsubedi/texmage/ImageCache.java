package np.com.ishworsubedi.texmage;

import android.util.LruCache;

public class ImageCache {
    private static final int CACHE_SIZE = 20;

    private final LruCache<String, ImageDrawable> cache = new LruCache<>(CACHE_SIZE);

    private static ImageCache instance;

    private ImageCache() {}

    public static synchronized ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    public ImageDrawable get(String key) {
        return cache.get(key);
    }

    public void put(String key, ImageDrawable avatar) {
        cache.put(key, avatar);
    }

}
