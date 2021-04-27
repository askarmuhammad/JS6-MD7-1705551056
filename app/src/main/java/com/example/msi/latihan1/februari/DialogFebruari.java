package com.example.msi.latihan1.februari;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingTextHelper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.telephony.PhoneNumberUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.msi.latihan1.R;
import com.example.msi.latihan1.ServerAPI;
import com.example.msi.latihan1.ui.login.LoginActivity;
import com.google.android.gms.common.api.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.msi.latihan1.JanuariActivity.adapter;
import static com.example.msi.latihan1.JanuariActivity.imgFile;
import static com.example.msi.latihan1.JanuariActivity.list;
import static com.example.msi.latihan1.JanuariActivity.mAdapter;
import static com.example.msi.latihan1.JanuariActivity.uriimage;

public class DialogFebruari extends AppCompatDialogFragment {

    private static final String TAG = "JanuariActivity";
    private EditText deskripsi;
    private TextView mDisplayDate2;
    private ImageView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText editText;
    private EditText tlp;
    //    private DialogListener listener;
    private Button btnphoto;
    private Button btnconfirm;
    private ImageView gallery;
    public static Bitmap bitmap_gambar;
    String foto_gambar;
    private EventListener eventListener;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "sharedpref";
    private static final String KEY_NAMAACARA = "nama_acara";
    private static final String KEY_TANGGALACARA= "tanggal_acara";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PREF= "pref";
    private static final int IMAGE_PICK_CODE = 1;
    private static final int PERMISSION_CODE = 2;
    Uri gambar;
    String bulans="";

    private Adapterfebruari adapterJanuari;
    private ArrayList<ListFebruari> mList = new ArrayList<>();


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        mDisplayDate = view.findViewById(R.id.tanggal_acara);
        mDisplayDate2 = view.findViewById(R.id.textview_tanggal);
        editText = view.findViewById(R.id.nama_acara);
        deskripsi = view.findViewById(R.id.deskripsi_acara);
        gallery = view.findViewById(R.id.photo);
        tlp = view.findViewById(R.id.number_acara);
        btnphoto = view.findViewById(R.id.btnphoto);
        btnconfirm = view.findViewById(R.id.btnconfirm);



        builder.setView(view)
                .setTitle("Data acara");

        btnphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        ImagePick();
                    }
                }
                else{
                    ImagePick();
                }
            }
        });
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String nama_acara = editText.getText().toString();
//                String tanggal_acara = mDisplayDate2.getText().toString();
//                String deskripsi_acara = deskripsi.getText().toString();
//                String number = tlp.getText().toString();
//                Log.e("nama", nama_acara);
//                Log.e("tgl", tanggal_acara);
//                Log.e("uri", String.valueOf(uriimage));
//                listener.applyText(nama_acara, tanggal_acara, number, deskripsi_acara, gambar);
                insertData();
//                Intent intent = new Intent(getActivity(), JanuariActivity.class);
//                startActivity(intent);
                startActivity(new Intent(getActivity(), FebruariActivity.class));
                getActivity().finish();

            }
        });


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.YEAR, 2020);
//                calendar.add(calendar.YEAR, -1);
                calendar.set(calendar.MONTH,calendar.JANUARY);
                calendar.set(calendar.DAY_OF_MONTH, 31);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
//                dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dialog.getDatePicker().findViewById(getResources().getIdentifier("month","id","android")).setVisibility(View.GONE);
                dialog.getDatePicker().findViewById(getResources().getIdentifier("year","id","android")).setVisibility(View.GONE);
//                dialog.show();

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: " + dayOfMonth + "-" + month + "-" + year);
                month = month + 1;

                String date = year + "-" + month + "-" + dayOfMonth;
                mDisplayDate2.setText(date);
            }
        };
        return builder.create();
    }

    private void ImagePick(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ImagePick();
                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void BitmapToString(Bitmap bitmapImage){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]arr=baos.toByteArray();
        foto_gambar = Base64.encodeToString(arr,Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//            bitmap_gambar = (Bitmap) data.getExtras().get("data");
//            BitmapToString(bitmap_gambar);
//            gallery.setImageBitmap(bitmap_gambar);
        }
        gambar = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), gambar);
            BitmapToString(bitmap);
            gallery.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.i("TAG", "Some exception " + e);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            listener = (DialogListener) context;
//        }catch (ClassCastException e){
//            throw new ClassCastException(context.toString() + "tes");
//        }
//    }

//    public interface DialogListener{
//        void applyText(String nama_acara, String tanggal_acara, String no_telp, String deskripsi_acara, Uri gambar);
//
//    }

    //    public void savedata(){
//        sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(KEY_NAMAACARA, editText.getText().toString());
//        editor.putString(KEY_TANGGALACARA, mDisplayDate2.getText().toString());
//        editor.apply();
//    }
//    public static String encodeToBase64(Bitmap image) {
//        Bitmap immage = image;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
//
//        Log.d("Image Log:", imageEncoded);
//        return imageEncoded;
//    }
    private void insertData(){
        String url= ServerAPI.URL_INSERT;
        StringRequest respon=new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Sukses Tambah Data", Toast.LENGTH_SHORT).show();

//                try{
//                    JSONObject data = new JSONObject(response);
//                    String status_respon;
//                    status_respon = data.getString("status");
//
//                   if(status_respon.equals("berhasil")){
//                       Toast.makeText(getContext(), "Sukses Tambah Data", Toast.LENGTH_SHORT).show();
//                   }else {
//                       Toast.makeText(getContext(), "Gagal Tambah Data", Toast.LENGTH_SHORT).show();
//
//                    }
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Kesalahan Server", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Mendeklarasikan Form untuk mendapatkan method agar bisa mengirim menggunakan metode POST
                Map<String, String> form=new HashMap<>();
                form.put("nama", editText.getText().toString());
                form.put("tanggal", mDisplayDate2.getText().toString());
                form.put("no_telp", tlp.getText().toString());
                form.put("deskripsi", deskripsi.getText().toString());
                form.put("gambar", foto_gambar);

                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(respon);
    }
}
