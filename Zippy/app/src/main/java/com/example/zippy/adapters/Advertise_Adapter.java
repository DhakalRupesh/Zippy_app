package com.example.zippy.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zippy.R;
import com.example.zippy.fragments.Home_details;
import com.example.zippy.model.Advertise;
import com.example.zippy.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Advertise_Adapter extends RecyclerView.Adapter<Advertise_Adapter.PostViewHolder> {
    Context pContext;
    List<Advertise> postLists;

//    public Advertise_Adapter(List<Advertise> advertisesList, Context context) {
//        this.postLists = advertisesList;
//    }

    public Advertise_Adapter(List<Advertise> advertisesList) {
        this.postLists = advertisesList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_view, parent, false);
        return new PostViewHolder(view, pContext, postLists);
    }

    public void Mode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Advertise advertise = postLists.get(position);
        String imagePathPost = Url.imagePath + advertise.getAd_image();
        Mode();
        try {
            URL url;
            url = new URL(imagePathPost);
            holder.imageViewPost.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tv_uname.setText(advertise.getPostedby());
        holder.tv_deliveredFrom.setText(advertise.getSendfrom());
        holder.tv_deliveredto.setText(advertise.getDestinationofdelivery());
        holder.tv_Price.setText(advertise.getPriceofdelivery());
        holder.tv_negociable.setText(advertise.getNegociable());

    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }

    public void FilterPlaces(ArrayList<Advertise> filteredList){
        postLists=filteredList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageViewProfile;
        TextView tv_uname, tv_deliveredFrom, tv_Price, tv_negociable, tv_deliveredto;
        ImageView imageViewPost;

        public PostViewHolder(@NonNull View itemView, Context context, List<Advertise> postLists) {
            super(itemView);

            circleImageViewProfile = itemView.findViewById(R.id.img_profile_image);
            tv_uname = itemView.findViewById(R.id.tv_uname);
            tv_deliveredFrom = itemView.findViewById(R.id.tv_subinfo);
            tv_deliveredto = itemView.findViewById(R.id.tv_subinfo1);
            tv_Price = itemView.findViewById(R.id.tv_Price);
            tv_negociable = itemView.findViewById(R.id.tv_negociable);
            imageViewPost = itemView.findViewById(R.id.img_post);
        }
    }
}
