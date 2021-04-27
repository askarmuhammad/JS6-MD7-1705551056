package com.example.msi.latihan1.februari;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msi.latihan1.R;

import java.io.File;

public class DetailFebruariActivity extends AppCompatActivity {

    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_februari);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
//        uri = uri.parse(name);
        String tanggal = intent.getStringExtra("tanggal");
        String acara = intent.getStringExtra("acara");
        String deskripsi = intent.getStringExtra("deskripsi");
        String number = intent.getStringExtra("phone");
//        imgurl = intent.getStringExtra("name");
//        Bundle extras = getIntent().getExtras();
//        if (extras == null)
//        {
//            return;
//        }



        TextView acara1 = findViewById(R.id.textacara);
        acara1.setText(acara);

        TextView tanggal1 = findViewById(R.id.texttanggal);
        tanggal1.setText(tanggal);

        ImageView name1 = findViewById(R.id.textimage);

        TextView deskripsi1 = findViewById(R.id.textdeskripsi);
        deskripsi1.setText(deskripsi);

        TextView number1 = findViewById(R.id.textnumber);
        number1.setText(number);

        uri = uri.parse("image");
        name1.setImageURI(null);
        name1.setImageURI(Uri.parse(new File("/sdcard/Download/modern_poster.jpg").toString()));

        Log.e("image", String.valueOf(image));
        Log.e("tanggal", String.valueOf(tanggal));

        Log.e("acara", String.valueOf(acara));
        getData();
    }

    private void getData(){
//        if(getIntent().hasExtra("acara") && getIntent().hasExtra("tanggal")){
////            String image = getIntent().getStringExtra("name");
//            String acara = getIntent().getStringExtra("acara");
//            String tanggal = getIntent().getStringExtra("tanggal");
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String tanggal = intent.getStringExtra("tanggal");
        String acara = intent.getStringExtra("acara");
        String deskripsi = intent.getStringExtra("deskripsi");


        Log.e("image", String.valueOf(image) );
        Log.e("tanggal", String.valueOf(tanggal)  );

        Log.e("acara", String.valueOf(acara));
//            setImage( acara, tanggal, res);

    }




//        ImageView imageView = findViewById(R.id.textimage);
//        imageView.setImageURI(name);
//        Glide.with(this)
//                .asB
//                .load()

}
