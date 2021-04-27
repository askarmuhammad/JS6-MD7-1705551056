package com.example.msi.latihan1.februari;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.msi.latihan1.AdapterJanuari;
import com.example.msi.latihan1.DetailJanuariActivity;
import com.example.msi.latihan1.ModelData;
import com.example.msi.latihan1.R;
import com.example.msi.latihan1.ServerAPI;

import java.util.ArrayList;
import java.util.List;

public class Adapterfebruari extends RecyclerView.Adapter<Adapterfebruari.FebruariHolder>{
    private Context context;
    private List<ModelData> mList;


    public Adapterfebruari(List<ModelData> list, Context context ) {

        this.mList = list;
        this.context = context;
    }

    public static class FebruariHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextAcara;
        public TextView mTextTanggal;
        private TextView mTextDeskripsi;
        private TextView mNumber;
        private TextView mGambar;
        public RelativeLayout relativeLayout;

        Adapterfebruari.OnClickListener onClickListener;

        public FebruariHolder(@NonNull final View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageviewrecycleviewjanuari);
            mTextAcara = itemView.findViewById(R.id.textrecycleviewacara);
            mTextTanggal = itemView.findViewById(R.id.textrecycleviewtanggal);
            mTextDeskripsi = itemView.findViewById(R.id.textrecyclerviewdeskripsi);
            mGambar = itemView.findViewById(R.id.textimage);
            mNumber = itemView.findViewById(R.id.number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailFebruariActivity.class);
                    intent.putExtra("nama_acara", mTextAcara.getText().toString());
                    intent.putExtra("tanggal_acara", mTextTanggal.getText().toString());
                    intent.putExtra("no_telp", mNumber.getText().toString());
                    intent.putExtra("image", mGambar.getText().toString());
                    intent.putExtra("deskripsi_acara", mTextDeskripsi.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });

            relativeLayout = itemView.findViewById(R.id.relative);

            this.onClickListener = onClickListener;

        }

    }

    public void addItem(ModelData ModelData) {
        mList.add(0, ModelData);

    }

    @NonNull
    @Override
    public FebruariHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        FebruariHolder evh = new FebruariHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull FebruariHolder februariHolder, int i) {

        final ModelData currentItem = mList.get(i);

        String img = ServerAPI.URL_IMAGES + currentItem.getnGambar();
        februariHolder.mTextAcara.setText(currentItem.getnAcara());
        februariHolder.mTextTanggal.setText(currentItem.getnTanggal());
        februariHolder.mTextDeskripsi.setText(currentItem.getnDeskripsi());
        februariHolder.mNumber.setText(currentItem.getnTelp());
        februariHolder.mGambar.setText(currentItem.getnGambar());
        Glide.with(context).load(img).thumbnail(0.5f).crossFade().diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL).into(februariHolder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setFilter(ArrayList<ModelData> newlist){
        mList = new ArrayList<>();
        mList.addAll(newlist);
        notifyDataSetChanged();
    }

    interface OnClickListener {
        public void onJanuariClick(int position);
    }
}


