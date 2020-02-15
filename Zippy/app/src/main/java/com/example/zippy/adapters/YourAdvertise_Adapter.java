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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zippy.R;
import com.example.zippy.api.Posti;
import com.example.zippy.fragments.YourAdvertise;
import com.example.zippy.model.Advertise;
import com.example.zippy.model.User;
import com.example.zippy.url.Url;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(@NonNull final YourPostViewHolder holder, int position) {
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

        holder.AdvertiseID.setText(advertise.get_id());

        holder.yDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Posti postiDelete = Url.getInstance().create(Posti.class);
                Call<Void> advertiseCallDelete = postiDelete.deleteAdvertise(Url.token, advertise.get_id());

                advertiseCallDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(ypContext, "Error"+ response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(ypContext, "Deleted Successfully" , Toast.LENGTH_SHORT).show();
//                        YourAdvertise y = new YourAdvertise();
//                        y.GetLoggedPosts();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ypContext, "Error"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return yAdvertiseList.size();
    }

    public class YourPostViewHolder extends RecyclerView.ViewHolder {

        ImageView imageYpost, yEdit, yDeletePost, yPhoneIcon;
        TextView yPrice, yNegotiable, yVehicle, yPhone, yEmail,
                    acceptedName, acceptedPhone, acceptedEmail, AdvertiseID;
        LinearLayout layoutAccpby;
        List<Advertise> list;
        Context mContext;

        public YourPostViewHolder(@NonNull View itemView, final Context ypContext, List<Advertise> yAdvertiseList) {
            super(itemView);

            imageYpost = itemView.findViewById(R.id.img_your_post);
            yDeletePost = itemView.findViewById(R.id.img_your_delete_advertise);
            yPrice = itemView.findViewById(R.id.tv_your_Price);
            yNegotiable = itemView.findViewById(R.id.tv_your_negociable);
            yVehicle = itemView.findViewById(R.id.tv_your_need_vehicle);
            yPhone = itemView.findViewById(R.id.tv_your_contact_phone);
            yEmail = itemView.findViewById(R.id.tv_your_contact_eamil);

            AdvertiseID = itemView.findViewById(R.id.tv_your_advertiseID);

            this.list = yAdvertiseList;
            this.mContext = ypContext;

//            yDeletePost.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Posti postiDelete = Url.getInstance().create(Posti.class);
//                    Call<Void> advertiseCallDelete = postiDelete.deleteAdvertise(Url.token, AdvertiseID.toString());
//
//                    advertiseCallDelete.enqueue(new Callback<Advertise>() {
//                        @Override
//                        public void onResponse(Call<Advertise> call, Response<Advertise> response) {
//                            if (!response.isSuccessful()) {
//                                Toast.makeText(ypContext, "Cde " + response.code(), Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//
//                            Toast.makeText(ypContext, "Property Deleted", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Advertise> call, Throwable t) {
//                            Toast.makeText(ypContext, "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            });
        }
    }
}

