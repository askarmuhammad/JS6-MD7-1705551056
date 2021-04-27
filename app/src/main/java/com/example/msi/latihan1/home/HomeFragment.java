package com.example.msi.latihan1.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.msi.latihan1.AdapterJanuari;
import com.example.msi.latihan1.AppController;
import com.example.msi.latihan1.EventActivity;
import com.example.msi.latihan1.JanuariActivity;
import com.example.msi.latihan1.ModelData;
import com.example.msi.latihan1.R;
import com.example.msi.latihan1.ServerAPI;
import com.example.msi.latihan1.februari.FebruariActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ImageView event;
    private ImageView Januari,Februari,Maret,April,Mei,Juni,Juli,Agustus,September,Oktober,November,Desember;
    TextView tv_januari,tv_februari,tv_maret,tv_april,tv_mei,tv_juni,tv_juli,tv_agustus,tv_september,tv_oktober,tv_november,tv_desember;
    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<ModelData> mList;
    public static ArrayList<String> list;
    public static ArrayAdapter<String> adapter;
    String Sbulan="Januari";

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View tombol = inflater.inflate(R.layout.fragment_home, container, false);

        tv_januari = root.findViewById(R.id.tv_januari);
        tv_februari = root.findViewById(R.id.tv_februari);
        tv_maret = root.findViewById(R.id.tv_maret);
        tv_april = root.findViewById(R.id.tv_april);
        tv_mei = root.findViewById(R.id.tv_mei);
        tv_juni = root.findViewById(R.id.tv_juni);
        tv_juli = root.findViewById(R.id.tv_juli);
        tv_agustus = root.findViewById(R.id.tv_agustus);
        tv_september = root.findViewById(R.id.tv_september);
        tv_oktober = root.findViewById(R.id.tv_oktober);
        tv_november = root.findViewById(R.id.tv_november);
        tv_desember = root.findViewById(R.id.tv_desember);

        mRecyclerView = tombol.findViewById(R.id.recycleevent);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterJanuari(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        event = (ImageView) root.findViewById(R.id.eventcoming);
        Januari = (ImageView) root.findViewById(R.id.januari);
        Februari = (ImageView) root.findViewById(R.id.februari);
        Maret = (ImageView) root.findViewById(R.id.maret);
        April = (ImageView) root.findViewById(R.id.april);
        Mei = (ImageView) root.findViewById(R.id.mei);
        Juni = (ImageView) root.findViewById(R.id.juni);
        Juli = (ImageView) root.findViewById(R.id.juli);
        Agustus = (ImageView) root.findViewById(R.id.agustus);
        September = (ImageView) root.findViewById(R.id.september);
        Oktober = (ImageView) root.findViewById(R.id.oktober);
        November = (ImageView) root.findViewById(R.id.november);
        Desember = (ImageView) root.findViewById(R.id.desember);

        mList = new ArrayList<>();
        loadJson();

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
            }
        });

        Januari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_januari.getText().toString());
                startActivity(intent);
            }
        });
        Februari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_februari.getText().toString());
                startActivity(intent);
            }
        });
        Maret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_maret.getText().toString());
                startActivity(intent);
            }
        });
        April.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_april.getText().toString());
                startActivity(intent);
            }
        });
        Mei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_mei.getText().toString());
                startActivity(intent);
            }
        });
        Juni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_juni.getText().toString());
                startActivity(intent);
            }
        });
        Juli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_juli.getText().toString());
                startActivity(intent);
            }
        });
        Agustus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_agustus.getText().toString());
                startActivity(intent);
            }
        });
        September.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_september.getText().toString());
                startActivity(intent);
            }
        });
        Oktober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_oktober.getText().toString());
                startActivity(intent);
            }
        });
        November.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_november.getText().toString());
                startActivity(intent);
            }
        });
        Desember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                intent.putExtra("bulan", tv_desember.getText().toString());
                startActivity(intent);
            }
        });

        return root;

    }

    private void loadJson(){
        StringRequest reqData = new StringRequest(Request.Method.POST, ServerAPI.URL_TAMPIL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response : " + response.toString());
                for(int i=0; i<response.length(); i++){
                    try{
                        JSONArray array = new JSONArray(response);
                        JSONObject data = array.getJSONObject(i);
                        ModelData lj = new ModelData();
                        lj.setIdacara(data.getString("id_acara"));
                        lj.setnAcara(data.getString("nama_acara"));
                        lj.setnTanggal(data.getString("tanggal_acara"));
                        lj.setnTelp(data.getString("no_telp"));
                        lj.setnDeskripsi(data.getString("deskripsi_acara"));
                        lj.setnGambar(data.getString("gambar"));
                        mList.add(lj);
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley", "response : "+ error.getMessage());
                    }}
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> form=new HashMap<>();
                form.put("bulan", Sbulan);

                return form;
            }
        };
        AppController.getInstance().addToRequestQueue(reqData);
    }
}