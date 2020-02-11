package com.example.zippy.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
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
import com.example.zippy.activities.EditProfile;
import com.example.zippy.activities.Vehicle;
import com.example.zippy.api.Posti;
import com.example.zippy.api.Vehiclei;
import com.example.zippy.model.Advertise;
import com.example.zippy.model.User;
import com.example.zippy.model.Vehicles;
import com.example.zippy.url.Url;
import com.squareup.picasso.Picasso;

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
    private static User userme;
    private static final String TAG = "Advertise_Adapter";

    public Advertise_Adapter(Context pContext, List<Advertise> postLists) {
        this.pContext = pContext;
        this.postLists = postLists;
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
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        final Advertise advertise = postLists.get(position);
        Mode();
        userme = advertise.getPostedby();
        String imagePathPost = Url.imagePath + advertise.getAd_image();
        Picasso.get().load(imagePathPost).into(holder.imageViewPost);

        String imageUser = Url.imagePath + userme.getUserimage();
        Picasso.get().load(imageUser).into(holder.circleImageViewProfile);

        holder.tv_uname.setText(userme.getUsername());
        holder.tv_contactNo.setText(userme.getMobile());
        holder.tv_c_email.setText(userme.getEmail());

        holder.tv_deliveredFrom.setText(advertise.getSendfrom());
        holder.tv_deliveredto.setText(advertise.getDestinationofdelivery());
        holder.tv_Price.setText(advertise.getPriceofdelivery());
        holder.tv_negociable.setText(advertise.getNegociable());
        holder.tv_goodstype.setText(advertise.getGoodstype());
        holder.tv_need_vehicle.setText(advertise.getVehicleneed());

        holder.tv_postedby_id.setText(userme.get_id());

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
        ImageView imageViewPost;
        TextView tv_uname, tv_deliveredFrom, tv_Price, tv_negociable,
                tv_deliveredto, tv_goodstype, tv_need_vehicle,
                tv_contactNo, tv_c_email, tv_postedby_id;
        Button btnAccept, btnCancel;
        List<Advertise> list;
        Context mContext;


        public PostViewHolder(@NonNull View itemView, Context context, List<Advertise> postLists) {
            super(itemView);

            circleImageViewProfile = itemView.findViewById(R.id.img_profile_image);
            imageViewPost = itemView.findViewById(R.id.img_post);

            tv_uname=itemView.findViewById(R.id.tv_uname);
            tv_contactNo = itemView.findViewById(R.id.tv_contact_phone);
            tv_c_email = itemView.findViewById(R.id.tv_contact_eamil);

            tv_deliveredFrom = itemView.findViewById(R.id.tv_subinfo);
            tv_deliveredto = itemView.findViewById(R.id.tv_subinfo1);
            tv_goodstype = itemView.findViewById(R.id.tv_Devlivery_type);
            tv_negociable = itemView.findViewById(R.id.tv_negociable);
            tv_need_vehicle = itemView.findViewById(R.id.tv_need_vehicle);
            tv_Price = itemView.findViewById(R.id.tv_Price);

            tv_postedby_id = itemView.findViewById(R.id.tv_postedby_Uid);

            btnAccept = itemView.findViewById(R.id.btn_accept_delivery);
            btnCancel = itemView.findViewById(R.id.btn_cancel_delivery);
            this.list = postLists;
            this.mContext = context;
        }
    }

    private boolean validateUserVehicle() {
        if(Bottom_nav.user.getVehicleOfUser() == null){

        }
        return true;
    }

    private void UpdateStatus() {
        Boolean Status = true;

        Advertise advertiseStatusUpdate = new Advertise(Status);
        Posti posti = Url.getInstance().create(Posti.class);
        Call<Advertise> advertiseCallUpdate = posti.updateStatus(Url.token,advertiseStatusUpdate);

        advertiseCallUpdate.enqueue(new Callback<Advertise>() {
            @Override
            public void onResponse(Call<Advertise> call, Response<Advertise> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(pContext, "Code " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }

                Toast.makeText(pContext, "Delivery accepted successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Advertise> call, Throwable t) {
                Toast.makeText(pContext, "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
