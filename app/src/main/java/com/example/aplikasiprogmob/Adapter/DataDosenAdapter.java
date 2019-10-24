package com.example.aplikasiprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiprogmob.Model.DataDosen;
import com.example.aplikasiprogmob.Model.DataMahasiswa;
import com.example.aplikasiprogmob.R;

import java.util.ArrayList;

public class DataDosenAdapter extends RecyclerView.Adapter<DataDosenAdapter.ViewHolder> {

    ArrayList<DataDosen> DaftarDosen  ;

    public DataDosenAdapter(ArrayList<DataDosen> DaftarDosen) {
        this.DaftarDosen = DaftarDosen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_daftar_dosen, parent, false);
        return new DataDosenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtnidn.setText(DaftarDosen.get(position).getNidn());
        holder.txtnama.setText(DaftarDosen.get(position).getNamadosen());
        holder.txtgelar.setText(DaftarDosen.get(position).getGelar());
        holder.txtemail.setText(DaftarDosen.get(position).getEmail());
        holder.txtalamat.setText(DaftarDosen.get(position).getAlamat());

    }

    @Override
    public int getItemCount() {
        return (DaftarDosen !=null) ? DaftarDosen.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnidn;
        TextView txtnama;
        TextView txtgelar;
        TextView txtemail;
        TextView txtalamat;

        public ViewHolder(View view) {
            super(view);
            txtnidn = view.findViewById(R.id.nidn);
            txtnama = view.findViewById(R.id.namaDsn);
            txtgelar = view.findViewById(R.id.gelarDsn);
            txtemail = view.findViewById(R.id.emailDsn);
            txtalamat = view.findViewById(R.id.alamatDsn);

        }
    }
}
