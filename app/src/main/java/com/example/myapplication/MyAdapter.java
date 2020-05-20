package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    String data1[], data2[];
    int images[];
    Context context;
    Button btn;


    public MyAdapter(Context ct, String[] s1, String[] s2, int[] img){
    context = ct;
    data1 = s1;
    data2= s2;
    images = img;
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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View itemView) {
                int pos = holder.getAdapterPosition();
                Toast.makeText(itemView.getContext(), "You liked " + data1[pos], Toast.LENGTH_LONG).show();
            }
        });

        holder.text1.setText(data1[position]);
        holder.text2.setText(data2[position]);
        holder.image1.setImageResource(images[position]);


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
