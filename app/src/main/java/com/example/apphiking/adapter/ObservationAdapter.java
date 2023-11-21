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
import com.example.apphiking.observation.UpdateObservationActivity;

import java.util.ArrayList;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.MyViewHolder>{

    Context context;
    Activity activity;
    ArrayList observe_id, observe_observation, observe_date, observe_comment;
    Animation translate_anim;

    public ObservationAdapter(Activity activity, Context context, ArrayList observe_id, ArrayList observe_observation, ArrayList observe_date, ArrayList observe_comment) {
        this.activity = activity;
        this.context = context;
        this.observe_id = observe_id;
        this.observe_observation = observe_observation;
        this.observe_date = observe_date;
        this.observe_comment = observe_comment;
    }

    @NonNull
    @Override
    public ObservationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_observation_row, parent, false);
        return new ObservationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationAdapter.MyViewHolder holder, final int position) {
        holder.observe_id_txt.setText(String.valueOf(observe_id.get(position)));
        holder.observe_observation_txt.setText(String.valueOf(observe_observation.get(position)));
        holder.observe_date_txt.setText(String.valueOf(observe_date.get(position)));
        holder.observe_comment_txt.setText(String.valueOf(observe_comment.get(position)));
        holder.observeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateObservationActivity.class);
                intent.putExtra("id", String.valueOf(observe_id.get(position)));
                intent.putExtra("observation", String.valueOf(observe_observation.get(position)));
                intent.putExtra("date", String.valueOf(observe_date.get(position)));
                intent.putExtra("comment", String.valueOf(observe_comment.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return observe_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView observe_id_txt, observe_observation_txt, observe_date_txt, observe_comment_txt;
        LinearLayout observeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            observe_id_txt = itemView.findViewById((R.id.observe_id_txt));
            observe_observation_txt = itemView.findViewById((R.id.observe_observation_txt));
            observe_date_txt = itemView.findViewById((R.id.observe_date_txt));
            observe_comment_txt = itemView.findViewById((R.id.observe_comment_txt));
            observeLayout = itemView.findViewById((R.id.observeLayout));
            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            observeLayout.setAnimation(translate_anim);
        }
    }
}
