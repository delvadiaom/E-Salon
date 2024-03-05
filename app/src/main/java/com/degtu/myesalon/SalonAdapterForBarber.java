package com.degtu.myesalon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.degtu.myesalon.databinding.RowSalonBinding;

import java.util.ArrayList;

public class SalonAdapterForBarber extends RecyclerView.Adapter<SalonAdapterForBarber.SalonViewHolder> {

    private final Context context;
    private final ArrayList<Salon> salonArrayList;

    public SalonAdapterForBarber(Context context, ArrayList<Salon> salonArrayList) {
        this.context = context;
        this.salonArrayList = salonArrayList;
    }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_salon_for_barber,parent,false);
        return new SalonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder holder, int position) {


        final Salon salon = salonArrayList.get(position);
        Glide.with(context).load(salon.getSalonimg()).placeholder(R.drawable.profilepic3).into(holder.binding.salonimg);
        holder.binding.salonname.setText(salon.getSalonname());
        holder.binding.salonaddress.setText(salon.getCity());

    }

    @Override
    public int getItemCount() {
        return salonArrayList.size();
    }

    public static class SalonViewHolder extends RecyclerView.ViewHolder{

        RowSalonBinding binding;

        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RowSalonBinding.bind(itemView);
        }
    }
}
