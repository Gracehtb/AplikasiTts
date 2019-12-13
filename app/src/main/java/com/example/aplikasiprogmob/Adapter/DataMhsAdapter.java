package com.example.aplikasiprogmob.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiprogmob.Model.DataMahasiswa;
import com.example.aplikasiprogmob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataMhsAdapter extends RecyclerView.Adapter<DataMhsAdapter.ViewHolder> {

    ArrayList<DataMahasiswa> DataMhs ;
    Context context;

    public DataMhsAdapter(ArrayList<DataMahasiswa> DataMhs) {
        this.DataMhs = DataMhs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_data_mhs, parent, false);
        context = parent.getContext();
        return new DataMhsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fotomhs.getLayoutParams().width = 100;
        holder.fotomhs.getLayoutParams().height = 100;
        if(DataMhs.get(position).getFotomhs() !=null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/" + DataMhs.get(position).getFotomhs())
                    .into(holder.fotomhs);
        }
        holder.txtnim.setText(DataMhs.get(position).getNim());
        holder.txtnamamhs.setText(DataMhs.get(position).getNamamhs());
        holder.txtemailmhs.setText(DataMhs.get(position).getEmailmhs());
        holder.txtalamatmhs.setText(DataMhs.get(position).getAlamatmhs());
    }

    @Override
    public int getItemCount() {
        return (DataMhs !=null) ? DataMhs.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    implements  View.OnCreateContextMenuListener{
        private  TextView txtTitle, txtSubTitle;
        private  ImageView imgViewMhs;
        ImageView fotomhs;
        TextView txtnim;
        TextView txtnamamhs;
        TextView txtemailmhs;
        TextView txtalamatmhs;

        public ViewHolder(View view) {
            super(view);
            fotomhs = view.findViewById(R.id.imgMhs);
            txtnim = view.findViewById(R.id.nimMhs);
            txtnamamhs = view.findViewById(R.id.editNama);
            txtemailmhs = view.findViewById(R.id.emailMhs);
            txtalamatmhs = view.findViewById(R.id.alamatMhs);
            view.setOnCreateContextMenuListener(this);

        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Mahaisswa");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Mahasiswa");
        }


    }
}
