package com.degtu.myesalon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    int VIEW_TYPE_HEADER = -2;
    Activity activity;
    ArrayList<String> itemList;
    int selectedSection = -1;
    int selectedItem = -1;
    public int section = 0;
    int relativePosition = 1;
    public RecyclerViewClickListener listener;
    private Context context;

    public TimeAdapter(Activity activity,ArrayList<String> itemList,RecyclerViewClickListener listener,Context context){
        this.activity = activity;
        this.itemList = itemList;
        this.listener = listener;
        this.context = context;

    }

    @NonNull
    @Override
    public TimeAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if (viewType == VIEW_TYPE_HEADER){
            layout = R.layout.item_header;
        }else {
            layout = R.layout.item_slot;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new TimeAdapter.TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.TimeViewHolder holder, int position) {

        String sItem = itemList.get(position);
        holder.textView.setText(sItem);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                String IP;
                Salon salon;
                FirebaseFirestore firebaseFirestore;
                firebaseFirestore = FirebaseFirestore.getInstance();
                Map<String,Object> map = new HashMap<>();
                map.put("time",sItem);
                firebaseFirestore.collection("booking").document("hi").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(activity, "Booking Done", Toast.LENGTH_SHORT).show();
                    }
                });
                selectedSection = section;
                selectedItem = relativePosition;
                notifyDataSetChanged();
            }
        });
        if (selectedSection == section && selectedItem == relativePosition){
            holder.textView.setBackground(ContextCompat.getDrawable(activity,R.drawable.rectangle_fill));
            holder.textView.setTextColor(Color.WHITE);
        }else {
            holder.textView.setBackground(ContextCompat.getDrawable(activity,R.drawable.rectangle_outline));
            holder.textView.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public TimeViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.timing);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
