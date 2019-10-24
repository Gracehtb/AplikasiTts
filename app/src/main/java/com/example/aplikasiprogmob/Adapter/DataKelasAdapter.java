package com.example.aplikasiprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiprogmob.Model.DaftarKrs;
import com.example.aplikasiprogmob.Model.DataKelas;
import com.example.aplikasiprogmob.R;

import java.util.ArrayList;

public class DataKelasAdapter extends RecyclerView.Adapter<DataKelasAdapter.ViewHolder> {

    ArrayList<DataKelas> LihatKelas ;

    public DataKelasAdapter(ArrayList<DataKelas> LihatKelas) {
        this.LihatKelas = LihatKelas;
    }

    @NonNull
    @Override
    public DataKelasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_lihat_kelas, parent, false);
        return new DataKelasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataKelasAdapter.ViewHolder holder, int position) {
        holder.txtkode.setText(LihatKelas.get(position).getKode());
        holder.txtmatkul.setText(LihatKelas.get(position).getMatkul());
        holder.txthari.setText(LihatKelas.get(position).getHari());
        holder.txtsesi.setText(LihatKelas.get(position).getSesi());
        holder.txtdosen.setText(LihatKelas.get(position).getDosen());
        holder.txtjlhmhs.setText(LihatKelas.get(position).getJlhmhs());
    }

    @Override
    public int getItemCount() {
        return (LihatKelas !=null) ? LihatKelas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtkode;
        TextView txtmatkul;
        TextView txthari;
        TextView txtsesi;
        TextView txtdosen;
        TextView txtjlhmhs;

        public ViewHolder(View view) {
            super(view);
            txtkode = view.findViewById(R.id.txtKode);
            txtmatkul = view.findViewById(R.id.txtMatkul);
            txthari = view.findViewById(R.id.txtHari);
            txtsesi = view.findViewById(R.id.txtSesi);
            txtdosen = view.findViewById(R.id.txtDosen);
            txtjlhmhs = view.findViewById(R.id.txtJlhMhs);

        }
    }
}
