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

import com.example.aplikasiprogmob.Model.DataDosen;
import com.example.aplikasiprogmob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataDosenAdapter extends RecyclerView.Adapter<DataDosenAdapter.ViewHolder> {

    ArrayList<DataDosen> DaftarDosen  ;
    Context context;

    public DataDosenAdapter(ArrayList<DataDosen> DaftarDosen) {
        this.DaftarDosen = DaftarDosen;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_daftar_dosen, parent, false);
        context = parent.getContext();
        return new DataDosenAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.foto.setText(DaftarDosen.get(position).getFoto());
        //URL url = new URL("http://image10.bizrate-images.com/resize?sq=60&uild=2216744464");
       // Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
       // imageView.setImage(bmp);
        holder.foto.getLayoutParams().width = 100;
        holder.foto.getLayoutParams().height = 100;
        if(DaftarDosen.get(position).getFoto() !=null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/" + DaftarDosen.get(position).getFoto())
                    .into(holder.foto);
        }
        holder.txtnidn.setText(DaftarDosen.get(position).getNidn());
        holder.txtnamadosen.setText(DaftarDosen.get(position).getNamadosen());
        holder.txtgelar.setText(DaftarDosen.get(position).getGelar());
        holder.txtemail.setText(DaftarDosen.get(position).getEmail());
        holder.txtalamat.setText(DaftarDosen.get(position).getAlamat());

    }

    @Override
    public int getItemCount() {
        return (DaftarDosen !=null) ? DaftarDosen.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnCreateContextMenuListener{
        private TextView txtTitle, txtSubtitle;
        private ImageView imgViewDosen;
        ImageView foto;
        TextView txtnidn;
        TextView txtnamadosen;
        TextView txtgelar;
        TextView txtemail;
        TextView txtalamat;

        public ViewHolder(View view) {
            super(view);
            foto = view.findViewById(R.id.dsnImage);
            txtnidn = view.findViewById(R.id.nidn);
            txtnamadosen = view.findViewById(R.id.namadosen);
            txtgelar = view.findViewById(R.id.gelarDsn);
            txtemail = view.findViewById(R.id.emailDsn);
            txtalamat = view.findViewById(R.id.alamatDsn);
            view.setOnCreateContextMenuListener(this);

        }



        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Dosen");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Dosen");
        }
    }
}
