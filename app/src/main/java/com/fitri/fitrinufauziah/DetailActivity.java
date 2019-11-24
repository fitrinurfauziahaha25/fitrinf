package com.fitri.fitrinufauziah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //definisi: cara kedua dimana objek didefinisikan langsung
        final TextView tvNama = (TextView) findViewById(R.id.tv_nama);
        final TextView tvAlamat = (TextView) findViewById(R.id.tv_alamat);
        final TextView tvProdi = (TextView) findViewById(R.id.tv_prodi);
        final TextView tvLulusan = (TextView) findViewById(R.id.tv_lulusan);

        //Tangkap si dia
        Intent i = getIntent();
        tvNama.setText(i.getStringExtra("i_nama"));
        tvAlamat.setText(i.getStringExtra("i_alamat"));
        tvProdi.setText(i.getStringExtra("i_prodi"));
        tvLulusan.setText(i.getStringExtra("i_lulusan"));


        getSupportActionBar().setTitle("Informasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
