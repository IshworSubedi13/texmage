package np.com.ishworsubedi.texmage;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userId = "1234";
        String userName = "Ishwor Subedi";
        String imageUrl = "...";
        int bgColor = ColorGenerator.MATERIAL.getRandomColor();
        String initials = Utils.getInitials(userName);
        IconProvider provider = new IconProvider(null, imageUrl, bgColor, initials);
        ImageView imageView = findViewById(R.id.imageView);
        ImageLoader.loadImage(this, imageView, provider,
                Shape.CIRCLE, 0, null, userId);

    }
}