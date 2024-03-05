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

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeSlotAdapter extends SectionedRecyclerViewAdapter<TimeSlotAdapter.TimeSlotViewHolder> {

    Activity activity;
    ArrayList<String> sectionList;
    HashMap<String,ArrayList<String>> itemList = new HashMap<>();
    int selectedSection = -1;
    int selectedItem = -1;
    public RecyclerViewClickListener listener;
    private Context context;

    public TimeSlotAdapter(Activity activity, ArrayList<String> sectionList, HashMap<String,ArrayList<String>> itemList,RecyclerViewClickListener listener,Context context){
        this.activity = activity;
        this.sectionList = sectionList;
        this.itemList = itemList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public int getSectionCount() {
        return sectionList.size();
    }

    @Override
    public int getItemCount(int section) {
        return itemList.get(sectionList.get(section)).size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    @Override
    public void onBindHeaderViewHolder(TimeSlotViewHolder holder, int section) {
        holder.textView.setText(sectionList.get(section));
    }

    @Override
    public void onBindViewHolder(TimeSlotViewHolder holder, int section, int relativePosition, int absolutePosition) {
        String sItem = itemList.get(sectionList.get(section)).get(relativePosition);
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
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        if (section == 1){
            return 0;
        }
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    public class TimeSlotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;

        public TimeSlotViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.timing);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    @Override
    public TimeSlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        if (viewType == VIEW_TYPE_HEADER){
            layout = R.layout.item_header;
        }else {
            layout = R.layout.item_slot;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new TimeSlotViewHolder(view);
    }
}
