package com.example.aplikasiprogmob.Adapter;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiprogmob.Model.DataMahasiswa;
import com.example.aplikasiprogmob.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DataMhsAdapter extends RecyclerView.Adapter<DataMhsAdapter.ViewHolder> {

    ArrayList<DataMahasiswa> DataMhs ;

    public DataMhsAdapter(ArrayList<DataMahasiswa> DataMhs) {
        this.DataMhs = DataMhs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_data_mhs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtnim.setText(DataMhs.get(position).getNim());
        holder.txtnama.setText(DataMhs.get(position).getNama());
        holder.txtemail.setText(DataMhs.get(position).getEmail());
        holder.txtalamat.setText(DataMhs.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return (DataMhs !=null) ? DataMhs.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnim;
        TextView txtnama;
        TextView txtemail;
        TextView txtalamat;

        public ViewHolder(View view) {
            super(view);
            txtnim = view.findViewById(R.id.txtNim);
            txtnama = view.findViewById(R.id.txtNama);
            txtemail = view.findViewById(R.id.txtEmail);
            txtalamat = view.findViewById(R.id.txtAlamat);

        }
    }
}
