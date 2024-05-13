package com.example.homerental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homerental.Activities.DetailActivity;
import com.example.homerental.Activities.StaticVariables;
import com.example.homerental.Annonce.Annonce;
import com.example.homerental.Domain.ItemsDomain;
import com.example.homerental.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    ArrayList<Annonce> items;
    DecimalFormat formatter;
    Context context;

    public ItemsAdapter(ArrayList<Annonce> items) {
        this.items = items;
        formatter=new DecimalFormat("###,###,###,###.00");
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewholder,parent,false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitre());
        holder.addressTxt.setText(items.get(position).getLocalisation());
        holder.priceTxt.setText(String.format("%s DT", formatter.format(items.get(position).getPrix())));




        Glide.with(holder.itemView.getContext())
                .load(Base64.decode(items.get(position).getImageData(),0))
                .into(holder.pic);






        holder.itemView.setOnClickListener (v->{
            Intent intent = new Intent(context, DetailActivity.class);
            StaticVariables.selectedAnnonce = items.get(position);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, addressTxt, priceTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            addressTxt=itemView.findViewById(R.id.addressTxt);
            priceTxt=itemView.findViewById(R.id.priceTxt);
            pic=itemView.findViewById(R.id.pic);

        }
    }
}