package com.degtu.myesalon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.degtu.myesalon.databinding.YourBookingsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private final Context context;
    private final ArrayList<Booking> bookingArrayList;

    public BookingAdapter(Context context, ArrayList<Booking> bookingArrayList) {
        this.context = context;
        this.bookingArrayList = bookingArrayList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.your_bookings,parent,false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Booking booking = bookingArrayList.get(position);
        holder.binding.username.setText(booking.getAmpm());
        holder.binding.selectedTime.setText(booking.getTimeslot());
        holder.binding.selectedService.setText(booking.getService());
        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();

        holder.binding.cancelbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Object,Object> map = new HashMap<>();
                map.put(holder.binding.selectedTime.getText().toString(),booking.getSalonname());
                DocumentReference documentReference = firebaseFirestore.collection("booking").document(String.valueOf(map));
                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Booking Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingArrayList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        YourBookingsBinding binding;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = YourBookingsBinding.bind(itemView);
        }
    }
}
