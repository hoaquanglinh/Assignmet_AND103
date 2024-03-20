package com.example.assignmet_ph43159.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmet_ph43159.Model.SinhVien;
import com.example.assignmet_ph43159.R;

import java.util.List;

public class AdapterSinhVien extends RecyclerView.Adapter<AdapterSinhVien.ViewHolder>{
    private List<SinhVien> list;

    public AdapterSinhVien(List<SinhVien> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinh_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SinhVien sv = list.get(position);


        holder.txttensv.setText(sv.getTensv());
        holder.txtmasv.setText(sv.getMasv());
        holder.txtdiemtb.setText(String.valueOf(sv.getDiemtb()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensv, txtmasv, txtdiemtb;
        Button btnDelete;
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            txttensv = itemView.findViewById(R.id.tensv);
            txtmasv = itemView.findViewById(R.id.masv);
            txtdiemtb = itemView.findViewById(R.id.diemTB);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
