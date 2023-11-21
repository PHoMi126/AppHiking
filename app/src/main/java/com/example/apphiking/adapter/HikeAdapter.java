package com.example.apphiking.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphiking.R;
import com.example.apphiking.UpdateActivity;
import com.example.apphiking.observation.ObservationActivity;

import java.util.ArrayList;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList hike_id, hike_location, hike_date, hike_parking, hike_length, hike_difficulty, hike_desc;
    Animation translate_anim;

    public HikeAdapter(Activity activity, Context context, ArrayList hike_id, ArrayList hike_location, ArrayList hike_date, ArrayList hike_parking, ArrayList hike_length, ArrayList hike_difficulty, ArrayList hike_desc) {
        this.activity = activity;
        this.context = context;
        this.hike_id = hike_id;
        this.hike_location = hike_location;
        this.hike_date = hike_date;
        this.hike_parking = hike_parking;
        this.hike_length = hike_length;
        this.hike_difficulty = hike_difficulty;
        this.hike_desc = hike_desc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.hike_id_txt.setText(String.valueOf(hike_id.get(position)));
        holder.hike_location_txt.setText(String.valueOf(hike_location.get(position)));
        holder.hike_date_txt.setText(String.valueOf(hike_date.get(position)));
        holder.hike_parking_txt.setText(String.valueOf(hike_parking.get(position)));
        holder.hike_length_txt.setText(String.valueOf(hike_length.get(position)));
        holder.hike_difficulty_txt.setText(String.valueOf(hike_difficulty.get(position)));
        holder.hike_desc_txt.setText(String.valueOf(hike_desc.get(position)));
//        holder.dataLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(hike_id.get(position)));
//                intent.putExtra("location", String.valueOf(hike_location.get(position)));
//                intent.putExtra("date", String.valueOf(hike_date.get(position)));
//                intent.putExtra("parking", String.valueOf(hike_parking.get(position)));
//                intent.putExtra("length", String.valueOf(hike_length.get(position)));
//                intent.putExtra("difficulty", String.valueOf(hike_difficulty.get(position)));
//                intent.putExtra("desc", String.valueOf(hike_desc.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });

        holder.dataLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ObservationActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hike_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hike_id_txt, hike_location_txt, hike_date_txt, hike_parking_txt, hike_length_txt, hike_difficulty_txt, hike_desc_txt;
        LinearLayout dataLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hike_id_txt = itemView.findViewById((R.id.hike_id_text));
            hike_location_txt = itemView.findViewById((R.id.hike_location_text));
            hike_date_txt = itemView.findViewById((R.id.hike_date_text));
            hike_parking_txt = itemView.findViewById((R.id.hike_parking_txt));
            hike_length_txt = itemView.findViewById((R.id.hike_length_txt));
            hike_difficulty_txt = itemView.findViewById((R.id.hike_difficulty_txt));
            hike_desc_txt = itemView.findViewById((R.id.hike_desc_txt));
            dataLayout = itemView.findViewById((R.id.dataLayout));
            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            dataLayout.setAnimation(translate_anim);
        }
    }
}
