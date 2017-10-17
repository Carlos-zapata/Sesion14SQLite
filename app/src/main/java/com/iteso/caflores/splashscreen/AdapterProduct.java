package com.iteso.caflores.splashscreen;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.caflores.splashscreen.beans.Tool;

import java.util.ArrayList;


public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private ArrayList<ItemProduct> mDataSet;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterProduct(Context context, ArrayList<ItemProduct> myDataSet) {
        mDataSet = myDataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button mShare;
        public Button mDetail;
        public TextView mProductTitle;
        public TextView mProductStore;
        public TextView mProductLocation;
        public TextView mProductPhone;
        public ImageView mProductImage;
        public ImageView mProductThumbnail;
        public RelativeLayout mEventLayout;

        public ViewHolder(View v) {
            super(v);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_product_layout);
            mDetail = (Button) v.findViewById(R.id.item_product_detail);
            mShare = (Button) v.findViewById(R.id.item_product_share);
            mProductTitle = (TextView) v.findViewById(R.id.item_product_title);
            mProductStore = (TextView) v.findViewById(R.id.item_product_store);
            mProductLocation = (TextView) v.findViewById(R.id.item_product_location);
            mProductPhone = (TextView) v.findViewById(R.id.item_product_phone);
            mProductImage = (ImageView) v.findViewById(R.id.item_product_image);
            mProductThumbnail = (ImageView) v.findViewById(R.id.item_product_thumbnail);
        }
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getTitle());
        holder.mProductStore.setText(mDataSet.get(position).getStore().getName());
        holder.mProductLocation.setText(mDataSet.get(position).getStore().getCity().getName() + ", Jalisco");
        holder.mProductPhone.setText(mDataSet.get(position).getStore().getPhone());
        switch (mDataSet.get(position).getImage()) {
            case 0:
                holder.mProductImage.setImageResource(R.drawable.mac);
                break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.alienware);
                break;
        }
        Bitmap bitmap = ((BitmapDrawable) holder.mProductThumbnail.getDrawable()).getBitmap();
        holder.mProductThumbnail.setImageBitmap(Tool.getRoundedBitmap(bitmap));

        holder.mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.send_to)));
            }
        });



        holder.mProductPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mDataSet.get(position).getStore().getPhone()));
                context.startActivity(intent);
            }


        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}