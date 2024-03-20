
package com.example.assignmet_ph43159;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmet_ph43159.Adapter.AdapterSinhVien;
import com.example.assignmet_ph43159.Model.SinhVien;

import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterSinhVien adapter;
    List<SinhVien> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        recyclerView = findViewById(R.id.recyclerViewSinhVien);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });
    }

    void them () {
        Dialog dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.dialog_save);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}