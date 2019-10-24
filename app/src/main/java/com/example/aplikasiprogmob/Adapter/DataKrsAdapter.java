package com.example.aplikasiprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiprogmob.Model.DaftarKrs;
import com.example.aplikasiprogmob.Model.DataMatkul;
import com.example.aplikasiprogmob.R;

import java.util.ArrayList;

public class DataKrsAdapter extends RecyclerView.Adapter<DataKrsAdapter.ViewHolder> {

    ArrayList<DaftarKrs> DataKrs ;

    public DataKrsAdapter(ArrayList<DaftarKrs> DataKrs) {
        this.DataKrs = DataKrs;
    }

    @NonNull
    @Override
    public DataKrsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_data_krs, parent, false);
        return new DataKrsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataKrsAdapter.ViewHolder holder, int position) {
        holder.txtkode.setText(DataKrs.get(position).getKode());
        holder.txtmatkul.setText(DataKrs.get(position).getMatkul());
        holder.txthari.setText(DataKrs.get(position).getHari());
        holder.txtsesi.setText(DataKrs.get(position).getSesi());
        holder.txtsks.setText(DataKrs.get(position).getSks());
        holder.txtdosen.setText(DataKrs.get(position).getDosen());
        holder.txtjlhmhs.setText(DataKrs.get(position).getJlhmhs());
    }

    @Override
    public int getItemCount() {
        return (DataKrs !=null) ? DataKrs.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtkode;
        TextView txtmatkul;
        TextView txthari;
        TextView txtsesi;
        TextView txtsks;
        TextView txtdosen;
        TextView txtjlhmhs;

        public ViewHolder(View view) {
            super(view);
            txtkode = view.findViewById(R.id.txtKode);
            txtmatkul = view.findViewById(R.id.txtMatkul);
            txthari = view.findViewById(R.id.txtHari);
            txtsesi = view.findViewById(R.id.txtSesi);
            txtsks = view.findViewById(R.id.txtSks);
            txtdosen = view.findViewById(R.id.txtDosen);
            txtjlhmhs = view.findViewById(R.id.txtJlhMhs);

        }
    }
}
