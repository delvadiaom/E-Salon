package com.degtu.myesalon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.degtu.myesalon.databinding.RowSalonDetailBinding;
import com.degtu.myesalon.databinding.RowServiceDetailBinding;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.SalonViewHolder> {

    private final Context context;
    private final ArrayList<Salon> salonArrayList;

    public ServiceAdapter(Context context, ArrayList<Salon> salonArrayList) {
        this.context = context;
        this.salonArrayList = salonArrayList;
    }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_service_detail,parent,false);
        return new SalonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder holder, int position) {


        final Salon salon = salonArrayList.get(position);
        Glide.with(context).load(salon.getSalonimg()).placeholder(R.drawable.profilepic3).into(holder.binding.salonimg);
        holder.binding.salonname.setText(salon.getSalonname());
        holder.binding.salonaddress.setText(salon.getCity());
        holder.binding.salontype.setText(salon.getGender());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailSalonActivity.class);
                intent.putExtra("salonname",salon.getSalonname());
                intent.putExtra("salonaddress",salon.getSalonaddress());
                intent.putExtra("city",salon.getCity());
                intent.putExtra("pincode",salon.getPincode());
                intent.putExtra("state",salon.getState());
                intent.putExtra("salonimg",salon.getSalonimg());
                intent.putExtra("ip",salon.getIp());
                intent.putExtra("service",salon.getService());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return salonArrayList.size();
    }

    public static class SalonViewHolder extends RecyclerView.ViewHolder{

        RowServiceDetailBinding binding;

        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RowServiceDetailBinding.bind(itemView);
        }
    }
}
