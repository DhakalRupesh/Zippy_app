package com.example.zippy.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zippy.R;
import com.example.zippy.model.Advertise;
import com.example.zippy.model.User;
import com.example.zippy.url.Url;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class YourAdvertise_Adapter extends RecyclerView.Adapter<YourAdvertise_Adapter.YourPostViewHolder>{
    Context ypContext;
    List<Advertise> yAdvertiseList;
    private static User user;
    private static User userA;
    private static final String TAG = "YourAdvertise_Adapter";

    public YourAdvertise_Adapter(Context ypContext, List<Advertise> yAdvertiseList) {
        this.ypContext = ypContext;
        this.yAdvertiseList = yAdvertiseList;
    }

    @NonNull
    @Override
    public YourPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_post_view, parent, false);
        return new YourPostViewHolder(view, ypContext, yAdvertiseList);
    }

    @Override
    public void onBindViewHolder(@NonNull YourPostViewHolder holder, int position) {
        final Advertise advertise = yAdvertiseList.get(position);

        user = advertise.getPostedby();
        userA = advertise.getAcceptedby();

        String imagePathPost = Url.imagePath + advertise.getAd_image();
        Picasso.get().load(imagePathPost).into(holder.imageYpost);

        holder.yPrice.setText(advertise.getPriceofdelivery());
        holder.yNegotiable.setText(advertise.getNegociable());
        holder.yVehicle.setText(advertise.getVehicleneed());

        holder.yPhone.setText(user.getMobile());
        holder.yEmail.setText(user.getEmail());

        if(userA != null) {
            holder.layoutAccpby.setVisibility(View.VISIBLE);
            holder.acceptedName.setText(userA.getUsername());
            holder.acceptedPhone.setText(userA.getMobile());
            holder.acceptedEmail.setText(userA.getEmail());
        }
        else{
            holder.layoutAccpby.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return yAdvertiseList.size();
    }

    public class YourPostViewHolder extends RecyclerView.ViewHolder {

        ImageView imageYpost, yEdit, yDeletePost, yPhoneIcon;
        TextView yPrice, yNegotiable, yVehicle, yPhone, yEmail,
                    acceptedName, acceptedPhone, acceptedEmail;
        LinearLayout layoutAccpby;
        List<Advertise> list;
        Context mContext;

        public YourPostViewHolder(@NonNull View itemView, Context ypContext, List<Advertise> yAdvertiseList) {
            super(itemView);

            imageYpost = itemView.findViewById(R.id.img_your_post);
            yEdit = itemView.findViewById(R.id.img_your_edit_advertise);
            yDeletePost = itemView.findViewById(R.id.img_your_delete_advertise);
            yPhoneIcon = itemView.findViewById(R.id.img_call_accepted_person);
            layoutAccpby = itemView.findViewById(R.id.layoutAccpby);
            yPrice = itemView.findViewById(R.id.tv_your_Price);
            yNegotiable = itemView.findViewById(R.id.tv_your_negociable);
            yVehicle = itemView.findViewById(R.id.tv_your_need_vehicle);
            yPhone = itemView.findViewById(R.id.tv_your_contact_phone);
            yEmail = itemView.findViewById(R.id.tv_your_contact_eamil);

            acceptedName = itemView.findViewById(R.id.tv_accepteduname);
            acceptedPhone = itemView.findViewById(R.id.tv_accepted_phone);
            acceptedEmail = itemView.findViewById(R.id.tv_accepted_email);

            this.list = yAdvertiseList;
            this.mContext = ypContext;
        }
    }
}

