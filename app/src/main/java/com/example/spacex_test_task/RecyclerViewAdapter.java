package com.example.spacex_test_task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacex_test_task.models.RetrofitModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
RecyclerView Adapter to download a images used Picasso,
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<RetrofitModel> retrofitModels;
    private ItemClickListener mClickListener;
    private Context mContext;

    public RecyclerViewAdapter(List<RetrofitModel> list, Context context) {
        this.retrofitModels = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list__item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Name.setText(retrofitModels.get(i).getMissionName());
        viewHolder.Status.setText(retrofitModels.get(i).getLaunchSuccess() ? "Launch Success":"Launch not Success");
        Picasso.with(mContext)
                .load(retrofitModels.get(i).getLinks().getMissionPatch())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return retrofitModels.size();
    }

    public void setmClickListener(ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public RetrofitModel getItem(int position){
        return retrofitModels.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView Name,Status;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = (TextView) itemView.findViewById(R.id.item_name);
            Status = (TextView) itemView.findViewById(R.id.item_status);
            img = (ImageView) itemView.findViewById(R.id.item_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           if(mClickListener!=null){
                mClickListener.OnItemClick(v,getAdapterPosition());
           }
        }
    }
}
