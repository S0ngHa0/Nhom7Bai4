package com.example.nhom7bai4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtMaSP, edtTenSP, edtSoLuong;
    Spinner spLoaiSP;
    List<String> data_lsp = new ArrayList<>();
    ArrayAdapter<String> adapter_lsp;
    Button btnThem, btnXoa, btnSua, btnHienThi,btnVeTrangChu;
    ListView lvDanhSach;
    ImageView imageView;
    public DBSanPham dbSanPham;
    List<SanPham> danhSachSanPham = new ArrayList<>();
    ArrayAdapter<SanPham> adapterSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        dbSanPham = new DBSanPham(this);
        DocDL();
    }

    private void setEvent() {
        data_lsp.add("samsam");
        data_lsp.add("samsung");

        adapter_lsp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_lsp);
        adapter_lsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoaiSP.setAdapter(adapter_lsp);

        adapterSanPham = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, danhSachSanPham);
        lvDanhSach.setAdapter(adapterSanPham);

        spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                hienThiHinhAnhSanPham(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        btnVeTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MainTrangChu (home page)
                Intent intent = new Intent(MainActivity.this, MainTrangChu.class);
                startActivity(intent);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPham();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sp = new SanPham();
                sp.setMaSP(edtMaSP.getText().toString());
                dbSanPham.XoaDL(sp);
                DocDL();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(edtMaSP.getText().toString());
                sanPham.setTenSP(edtTenSP.getText().toString());
                sanPham.setSoLuong(edtSoLuong.getText().toString());
                sanPham.setLoaiSP(spLoaiSP.getSelectedItem().toString());
                dbSanPham.SuaDL(sanPham);
                DocDL();
            }
        });
        btnHienThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiDanhSachSanPham();
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hienThiThongTinSanPham(position);
            }
        });

    }

    private void DocDL() {
        danhSachSanPham.clear();
        danhSachSanPham.addAll(dbSanPham.DocDL());
        adapterSanPham.notifyDataSetChanged();
    }

    private void themSanPham() {
        String maSP = edtMaSP.getText().toString();
        String tenSP = edtTenSP.getText().toString();
        String soLuongStr = edtSoLuong.getText().toString();
        String loaiSP = spLoaiSP.getSelectedItem().toString();

        if (!maSP.isEmpty() && !tenSP.isEmpty() && !soLuongStr.isEmpty()) {
            int soLuong = Integer.parseInt(soLuongStr);
            SanPham sanPham = new SanPham(maSP, tenSP, soLuong, loaiSP);
            dbSanPham.ThemDL(sanPham);
            clearEditTexts();
            Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            DocDL(); // Assuming DocDL() is a method to update your UI with the new data
        } else {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }

    private void hienThiDanhSachSanPham() {
        // Hiển thị danh sách sản phẩm trong ListView
        DocDL();
        Toast.makeText(this, "Danh sách sản phẩm", Toast.LENGTH_SHORT).show();
    }

    private void hienThiThongTinSanPham(int position) {
        SanPham sanPham = danhSachSanPham.get(position);
        edtMaSP.setText(sanPham.getMaSP());
        edtTenSP.setText(sanPham.getTenSP());
        edtSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
        spLoaiSP.setSelection(adapter_lsp.getPosition(sanPham.getLoaiSP()));
    }
    private void hienThiThongTinSanPhamUI(SanPham sanPham) {
        // Create an Intent to open the ProductDetailsActivity
        Intent intent = new Intent(this, ProductDetailsActivity.class);

        // Pass the product details to the ProductDetailsActivity
        intent.putExtra("maSP", sanPham.getMaSP());
        intent.putExtra("tenSP", sanPham.getTenSP());
        intent.putExtra("soLuong", String.valueOf(sanPham.getSoLuong()));
        intent.putExtra("loaiSP", sanPham.getLoaiSP());

        // Start the ProductDetailsActivity
        startActivity(intent);
    }

    private void hienThiHinhAnhSanPham(int position) {
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.zfold);
                break;
            case 1:
                imageView.setImageResource(R.drawable.samsung);
                break;
        }
    }

    private void clearEditTexts() {
        edtMaSP.setText("");
        edtTenSP.setText("");
        edtSoLuong.setText("");
        spLoaiSP.setSelection(0);
    }

    private void setControl() {
        edtMaSP = findViewById(R.id.edtMaSP);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        spLoaiSP = findViewById(R.id.spLoaiSP);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnHienThi = findViewById(R.id.btnHienThi);
        btnVeTrangChu = findViewById(R.id.btnThoat);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        imageView = findViewById(R.id.imageView);
    }

}
