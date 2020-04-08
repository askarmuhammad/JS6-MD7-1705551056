package com.example.msi.latihan1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class JanuariActivity extends AppCompatActivity implements Dialog.DialogListener {

    private static final String TAG = "JanuariActivity";
    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    private Bitmap bitmap;
    private String uri;
    private static final String KEY_NAMAACARA = "nama_acara";
    private static final String KEY_TANGGALACARA= "tanggal_acara";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_januari);

        textView2 = findViewById(R.id.texttanggal);
        textView = findViewById(R.id.textacara);
        imageView = findViewById(R.id.imagefoto);

        Intent intent = new Intent();

        intent.putExtra("imageUri", getIntent().toString());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int photo = bundle.getInt("image");
            imageView.setImageResource(photo);
        }
//        String path = prefs.getString(KEY, "");
//        if (path != ""){
//            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(path));
//        }
        loadsavedata();

//        if (savedInstanceState !=null){
//            String savednamacara = savedInstanceState.getString(KEY_NAMAACARA);
//            textView.setText(savednamacara);
//            String savedtanggalacara = savedInstanceState.getString(KEY_TANGGALACARA);
//            textView2.setText(savedtanggalacara);
//        }
//        else{
//            Toast.makeText(this, "halo", Toast.LENGTH_SHORT).show();
//        }
    }

    public void savedata(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAMAACARA, textView.getText().toString());
        editor.putString(KEY_TANGGALACARA, textView2.getText().toString());
        editor.apply();
    }
    private void loadsavedata(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textView.setText(sharedPreferences.getString("nama_acara",""));
        textView2.setText(sharedPreferences.getString("tanggal_acara",""));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstance) {
        super.onSaveInstanceState(savedInstance);
        savedInstance.putString(KEY_NAMAACARA, textView.getText().toString());
        savedInstance.putString(KEY_TANGGALACARA, textView2.getText().toString());
    }

    @Override
    public void onBackPressed() {
        savedata();
        super.onBackPressed();
    }

    public void buttonplus(View view){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyText(String nama_acara, String tanggal_acara, Uri uri) {
        textView2.setText(tanggal_acara);
        textView.setText(nama_acara);
        imageView.setImageURI(uri);
    }
}
