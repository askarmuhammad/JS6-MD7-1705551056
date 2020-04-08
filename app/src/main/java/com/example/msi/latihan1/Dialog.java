package com.example.msi.latihan1;

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

import com.example.msi.latihan1.R;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class Dialog extends AppCompatDialogFragment {

    private static final String TAG = "JanuariActivity";
    private TextView mDisplayDate2;
    private ImageView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText editText;
    private DialogListener listener;
    private Button btnphoto;
    private ImageView gallery;
    private EventListener eventListener;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "sharedpref";
    private static final String KEY_NAMAACARA = "nama_acara";
    private static final String KEY_TANGGALACARA= "tanggal_acara";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PREF= "pref";
    private static final int IMAGE_PICK_CODE = 1;
    private static final int PERMISSION_CODE = 2;
    Uri imgaeuri;


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Data acara")
                .setNegativeButton("batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nama_acara = editText.getText().toString();
                        String tanggal_acara = mDisplayDate2.getText().toString();
                        listener.applyText(nama_acara, tanggal_acara, imgaeuri);
//                        savedata();
                    }
                });
        mDisplayDate = view.findViewById(R.id.tanggal_acara);
        mDisplayDate2 = view.findViewById(R.id.textview_tanggal);
        editText = view.findViewById(R.id.nama_acara);
        gallery = view.findViewById(R.id.photo);
        btnphoto = view.findViewById(R.id.btnphoto);

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

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: " + dayOfMonth + "-" + month + "-" + year);
                month = month + 1;

                String date = dayOfMonth + "-" + month + "-" + year;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//            gallery.setImageURI(data.getData());
        }
imgaeuri = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imgaeuri);
            gallery.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.i("TAG", "Some exception " + e);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "tes");
        }
    }

    public interface DialogListener{
        void applyText(String nama_acara, String tanggal_acara, Uri uri);
    }

    public void savedata(){
        sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAMAACARA, editText.getText().toString());
        editor.putString(KEY_TANGGALACARA, mDisplayDate2.getText().toString());
        editor.apply();
    }
    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}
