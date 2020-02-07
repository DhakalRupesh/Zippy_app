package com.example.zippy.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zippy.R;
import com.example.zippy.activities.Bottom_nav;
import com.example.zippy.activities.Vehicle;
import com.example.zippy.api.Vehiclei;
import com.example.zippy.model.Advertise;
import com.example.zippy.model.Vehicles;
import com.example.zippy.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Advertise_Adapter extends RecyclerView.Adapter<Advertise_Adapter.PostViewHolder> {
    Context pContext;
    List<Advertise> postLists;

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
        holder.tv_goodstype.setText(advertise.getGoodstype());
        holder.tv_need_vehicle.setText(advertise.getVehicleneed());
        holder.tv_contactNo.setText(advertise.getContactno());
        holder.tv_c_email.setText(advertise.getContactemail());

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
        TextView tv_uname, tv_deliveredFrom, tv_Price, tv_negociable, tv_deliveredto, tv_postedby, tv_goodstype, tv_need_vehicle,
                tv_contactNo, tv_c_email, tv_your_postedby_id;
        ImageView imageViewPost;
        Button btnAccept, btnCancel;


        public PostViewHolder(@NonNull View itemView, Context context, List<Advertise> postLists) {
            super(itemView);

            circleImageViewProfile = itemView.findViewById(R.id.img_profile_image);
            tv_uname = itemView.findViewById(R.id.tv_uname);
            tv_deliveredFrom = itemView.findViewById(R.id.tv_subinfo);
            tv_deliveredto = itemView.findViewById(R.id.tv_subinfo1);
            tv_your_postedby_id = itemView.findViewById(R.id.tv_your_postedby_id);
            tv_goodstype = itemView.findViewById(R.id.tv_Devlivery_type);
            tv_Price = itemView.findViewById(R.id.tv_Price);
            tv_negociable = itemView.findViewById(R.id.tv_negociable);
            tv_postedby = itemView.findViewById(R.id.tv_postedby_id);
            tv_need_vehicle = itemView.findViewById(R.id.tv_need_vehicle);
            tv_contactNo = itemView.findViewById(R.id.tv_contact_phone);
            tv_c_email = itemView.findViewById(R.id.tv_contact_eamil);
            imageViewPost = itemView.findViewById(R.id.img_post);

            btnAccept = itemView.findViewById(R.id.btn_accept_delivery);
            btnCancel = itemView.findViewById(R.id.btn_cancel_delivery);

            if(validateStatus()) {
                btnAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnAccept.setVisibility(View.INVISIBLE);
                        btnCancel.setVisibility(View.VISIBLE);
                    }
                });
            }

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnCancel.setVisibility(View.INVISIBLE);
                    btnAccept.setVisibility(View.VISIBLE);
                }
            });

        }
    }

    private void UpdateStatus() {

    }

    private Boolean validateStatus() {

        final String vehicleAddedBy = Bottom_nav.user.get_id();
        final Boolean verified = true;

        Vehiclei vehiclei = Url.getInstance().create(Vehiclei.class);
        Call<Vehicles> vehicleCall = vehiclei.getVehicle();

        vehicleCall.enqueue(new Callback<Vehicles>() {
            @Override
            public void onResponse(Call<Vehicles> call, Response<Vehicles> response) {

                if(!response.isSuccessful()){
                    return;
                }

                Vehicles vehicles = response.body();
                vehicles.getVehicleAddedBy();
                vehicles.getVerified();

                if (vehicleAddedBy.matches(vehicles.getVehicleAddedBy()) && vehicles.getVerified().matches("true")){
                    Toast.makeText(pContext, "hah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Vehicles> call, Throwable t) {
                return;
            }
        });
        return true;
    }
}
