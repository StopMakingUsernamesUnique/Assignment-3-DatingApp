package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    String data1[];
    Boolean data2[];
    String images[];
    String UID[], Lat[], Lon[];
    Context context;
    Button btn;

    FirebaseMatchViewModel fb = new FirebaseMatchViewModel();



    public MyAdapter(Context ct, String[] s1, Boolean[] s2, String[] img, String[] uid, String[] lat, String[] lon) {
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
        UID = uid;

        Lat = lat;
        Lon = lon;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_layout, parent, false );

        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        btn.setOnClickListener(itemView -> {
            int pos = holder.getAdapterPosition();
            Toast.makeText(itemView.getContext(), context.getString(R.string.youlike) + data1[pos], Toast.LENGTH_LONG).show();
            fb.like(UID[position]);

        });
       /* android.location.Location one = new Location("");
        one.setLongitude(Location[0]);
        one.setLatitude(Location[1]);
        double d = Double.parseDouble(Lat[position]);

        android.location.Location two = new Location("");
        two.setLongitude(d);
        two.setLatitude(d);
        if(one.distanceTo(two) > 10){
            return;
        }
 */
        holder.text1.setText(data1[position]);

        if(data2[position] != null && data2[position]) {
            holder.text2.setText(context.getString(R.string.like));
        }
        Picasso.get().load(images[position]).into(holder.image1);

    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        ImageView image1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.MatchName);
            text2 = itemView.findViewById(R.id.MatchAge);
            image1 = itemView.findViewById(R.id.MatchImage);
            btn = itemView.findViewById(R.id.MatchButton);
        }
    }

}
