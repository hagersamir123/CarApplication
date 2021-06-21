package com.example.softxpertcarapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softxpertcarapplication.Pojo.CarData;

public class CarAdapter extends PagedListAdapter<CarData, CarAdapter.ItemViewHolder> {

    private Context activity;

    CarAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.activity = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_car_rv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CarData item = getItem(position);

        if (item != null) {
            holder.brand_tv.setText(activity.getResources().getString(R.string.brand) + item.getBrand());
            if(item.isUsed()) {
                holder.isUsed_tv.setText(activity.getResources().getString(R.string.used));
            }else {
                holder.isUsed_tv.setText(activity.getResources().getString(R.string.new_car));
            }
            if (item.getConstractionYear() != null) {
                holder.year_tv.setText(item.getConstractionYear());
            }else{
                holder.year_tv.setVisibility(View.GONE);
            }
            Glide.with(activity)
                    .load(item.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.place_holder)
                    .into(holder.imageView);


        }else{
            Toast.makeText(activity, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<CarData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CarData>() {
                @Override
                public boolean areItemsTheSame(CarData oldItem, CarData newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(CarData oldItem, CarData newItem) {
                    return oldItem.getBrand() .equals(newItem.getBrand());
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView brand_tv;
        TextView isUsed_tv;
        TextView year_tv;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            brand_tv = itemView.findViewById(R.id.car_brand);
            isUsed_tv = itemView.findViewById(R.id.is_used);
            year_tv = itemView.findViewById(R.id.car_year);
            imageView = itemView.findViewById(R.id.car_img);
        }
    }
}

