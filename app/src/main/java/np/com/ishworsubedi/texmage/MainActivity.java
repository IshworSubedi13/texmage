package np.com.ishworsubedi.texmage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static class User {
        String id;
        String name;
        String imageUrl;

        User(String id, String name, String imageUrl) {
            this.id = id;
            this.name = name;
            this.imageUrl = imageUrl;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User> users = Arrays.asList(
                new User("1", "Shirish Koirala", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOuxrvcNMfGLh73uKP1QqYpKoCB0JLXiBMvA&s"),
                new User("2", "Bibek Dhakal", null),
                new User("3", "Ishwor Subedi", "https://ishworsubedi.com.np/wp-content/uploads/2025/03/Ishwor-Subedi3-scaled.jpg")
        );

        UserAdapter adapter = new UserAdapter(this, users);
        recyclerView.setAdapter(adapter);

    }
    static class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        private final Context context;
        private final List<User> users;

        UserAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;
        }

        static class UserViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView nameView;

            UserViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                nameView = itemView.findViewById(R.id.nameView);
            }
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
            return new UserViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = users.get(position);

            holder.nameView.setText(user.name);

            String initials = Utils.getInitials(user.name);
            Color bgColor = ColorGenerator.MATERIAL.getColor(user.id);

            if (user.imageUrl != null && !user.imageUrl.isEmpty()) {
                Glide.with(context)
                        .asBitmap()
                        .load(user.imageUrl)
                        .centerCrop()
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                Bitmap circularBitmap = Utils.circularCrop(bitmap);
                                holder.imageView.setImageBitmap(circularBitmap);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                IconProvider provider = new IconProvider(null, null, bgColor, initials);
                                ImageDrawable drawable = new ImageDrawable(context, provider, Shape.CIRCLE, 0, null);
                                holder.imageView.setImageDrawable(drawable);
                            }
                        });
            } else {
                IconProvider provider = new IconProvider(null, null, bgColor, initials);
                ImageDrawable drawable = new ImageDrawable(context, provider, Shape.CIRCLE, 0, null);
                holder.imageView.setImageDrawable(drawable);
            }
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}