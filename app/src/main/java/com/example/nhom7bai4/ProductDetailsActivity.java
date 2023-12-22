package com.example.nhom7bai4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details); // Use the correct layout file

        // Get product details from the Intent
        String maSP = getIntent().getStringExtra("maSP");
        String tenSP = getIntent().getStringExtra("tenSP");
        String soLuong = getIntent().getStringExtra("soLuong");
        String loaiSP = getIntent().getStringExtra("loaiSP");

        // Set product details to the views in the layout
        TextView tvProductDetailsMaSP = findViewById(R.id.tvProductDetailsMaSP);
        TextView tvProductDetailsTenSP = findViewById(R.id.tvProductDetailsTenSP);
        TextView tvProductDetailsSoLuong = findViewById(R.id.tvProductDetailsSoLuong);
        TextView tvProductDetailsLoaiSP = findViewById(R.id.tvProductDetailsLoaiSP);


        tvProductDetailsMaSP.setText("Mã SP: " + maSP);
        tvProductDetailsTenSP.setText("Tên SP: " + tenSP);
        tvProductDetailsSoLuong.setText("Số lượng: " + soLuong);
        tvProductDetailsLoaiSP.setText("Loại SP: " + loaiSP);



    }
}
