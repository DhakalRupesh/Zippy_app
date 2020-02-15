package com.example.zippy.adapters;

import android.content.Context;
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
import com.example.zippy.model.Advertise;
import com.example.zippy.model.Postm;
import com.example.zippy.model.User;
import com.example.zippy.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YourBooked_Adapter extends RecyclerView.Adapter<YourBooked_Adapter.YourBookedViewHolder> {
    Context ybContext;
    List<Postm> ybAdvertiseList;
    private static User user;
    private static User userA;
    private static final String TAG = "YourBooked_Adapter";

    public YourBooked_Adapter(Context ybContext, List<Postm> ybAdvertiseList){
        this.ybContext = ybContext;
        this.ybAdvertiseList = ybAdvertiseList;
    }

    @NonNull
    @Override
    public YourBookedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_booked_advertise, parent, false);
        return new YourBookedViewHolder(view, ybContext, ybAdvertiseList);
    }

    @Override
    public void onBindViewHolder(@NonNull YourBooked_Adapter.YourBookedViewHolder holder, int position) {
        final Postm advertise = ybAdvertiseList.get(position);

        holder.imageBooked.setImageResource(advertise.getAd_image());

        holder.price.setText(advertise.getPriceofdelivery());
        holder.Negotiable.setText(advertise.getNegociable());
        holder.vType.setText(advertise.getVehicleneed());

        holder.cNumber.setText(advertise.getGoodstype());
        holder.cEamil.setText(advertise.getSendfrom());
    }

    @Override
    public int getItemCount() {
        return ybAdvertiseList.size();
    }

    public class YourBookedViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBooked;
        TextView price, Negotiable, vType, cNumber, cEamil;
        Button btnCancel;
        List<Advertise> list;
        Context mContext;

        public YourBookedViewHolder(@NonNull View itemView, final Context ybContext, final List<Postm> ybAdvertiseList) {
            super(itemView);
            imageBooked = itemView.findViewById(R.id.img_yourbooked_post);
            price = itemView.findViewById(R.id.tv_yourbooked_Price);
            Negotiable = itemView.findViewById(R.id.tv_yourbooked_negociable);
            vType = itemView.findViewById(R.id.tv_yourbooked_need_vehicle);
            cNumber = itemView.findViewById(R.id.tv_yourbooked_contact_phone);
            cEamil = itemView.findViewById(R.id.tv_yourbooked_contact_eamil);

            btnCancel = itemView.findViewById(R.id.btn_yourbooked_delete_advertise);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ybAdvertiseList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Toast.makeText(ybContext, "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
