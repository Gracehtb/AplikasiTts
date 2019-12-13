package com.example.aplikasiprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiprogmob.Model.DataMatkul;
import com.example.aplikasiprogmob.R;

import java.util.ArrayList;

public class DataMatkulAdapter extends RecyclerView.Adapter<DataMatkulAdapter.ViewHolder> {

    ArrayList<DataMatkul> MataKuliah ;

    public DataMatkulAdapter(ArrayList<DataMatkul> MataKuliah) {
        this.MataKuliah = MataKuliah;
    }

    @NonNull
    @Override
    public DataMatkulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_kode_matkul, parent, false);
        return new DataMatkulAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataMatkulAdapter.ViewHolder holder, int position) {
        holder.txtkode.setText(MataKuliah.get(position).getKode());
        holder.txtnama.setText(MataKuliah.get(position).getMatkul());
        holder.txthari.setText(MataKuliah.get(position).getHari());
        holder.txtsesi.setText(MataKuliah.get(position).getSesi());
        holder.txtsks.setText(MataKuliah.get(position).getSks());
    }

    @Override
    public int getItemCount() {
        return (MataKuliah !=null) ? MataKuliah.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtkode;
        TextView txtnama;
        TextView txthari;
        TextView txtsesi;
        TextView txtsks;

        public ViewHolder(View view) {
            super(view);
            txtkode = view.findViewById(R.id.txtKode);
            txtnama = view.findViewById(R.id.editNama);
            txthari = view.findViewById(R.id.txtHari);
            txtsesi = view.findViewById(R.id.txtSesi);
            txtsks = view.findViewById(R.id.txtSks);

        }
    }
}


