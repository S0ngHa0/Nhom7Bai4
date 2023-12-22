package com.example.nhom7bai4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.TextView;

import java.util.List;

public class AdapterSanPham extends ArrayAdapter {

    Context context;

    int resource;

    List<SanPham> data;

    public AdapterSanPham(@NonNull Context context, int resource, List<SanPham> data){
        super(context, resource, data);
        this.context=context;
        this.data=data;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivLoaiSP = convertView.findViewById(R.id.ivLoaiSP);
        TextView tvTenSP = convertView.findViewById(R.id.tvTenSanPham);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        SanPham sp = data.get(position);
        tvTenSP.setText(sp.getTenSP());
        if(sp.getLoaiSP().equals("Samsung")){
            ivLoaiSP.setImageResource(com.example.nhom7bai4.R.drawable.samsung);
        }
        if(sp.getLoaiSP().equals("SmartPhone")){
            ivLoaiSP.setImageResource(R.drawable.zfold);
        }


        return convertView;
    }
}
