    package com.example.msi.latihan1;

    import android.annotation.SuppressLint;
    import android.app.DatePickerDialog;
    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.drawable.ColorDrawable;
    import android.net.Uri;
    import android.os.PersistableBundle;
    import android.preference.PreferenceManager;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.DividerItemDecoration;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.inputmethod.EditorInfo;
    import android.widget.ArrayAdapter;
    import android.widget.DatePicker;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.support.v7.widget.SearchView;
    import android.widget.TextView;
    import android.app.SearchManager;
    import android.widget.SearchView.OnQueryTextListener;
    import android.widget.Toast;

    import com.android.volley.AuthFailureError;
    import com.android.volley.Request;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.StringRequest;
    import com.example.msi.latihan1.februari.ListFebruari;
    import com.google.gson.Gson;
    import com.google.gson.JsonArray;
    import com.google.gson.reflect.TypeToken;
    import com.mysql.cj.Constants;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import org.w3c.dom.Text;

    import java.io.File;
    import java.lang.reflect.Type;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class JanuariActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterJanuari.OnClickListener{

        private static final String TAG = "JanuariActivity";
        private TextView textView;
        private TextView textView2;
        private ImageView imageView;
        private static final String KEY_NAMAACARA = "nama_acara";
        private static final String KEY_TANGGALACARA= "tanggal_acara";
        private RecyclerView mRecyclerView;
        public static RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        public static  File imgFile;
        public static Uri uriimage;
        List<ModelData> mList;
        public static ArrayList<String> list;
        public static ArrayAdapter<String> adapter;
        ArrayList<ModelData> arrayJanuari = new ArrayList<>();
        AdapterJanuari adapterJanuari;
        ProgressDialog pd;
        String Sbulan="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_januari);

            mRecyclerView = findViewById(R.id.recycleviewjanuari);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            pd = new ProgressDialog(JanuariActivity.this);

            Sbulan= getIntent().getStringExtra("bulan");
            mList = new ArrayList<>();
            loadJson();

            mAdapter = new AdapterJanuari(mList, JanuariActivity.this);
            mRecyclerView.setAdapter(mAdapter);
            pd = new ProgressDialog(JanuariActivity.this);

            textView = findViewById(R.id.textrecycleviewacara);
            textView2 = findViewById(R.id.textrecycleviewtanggal);

            imgFile = new  File("/sdcard/Download/modern_poster.jpg");
            mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.activity_search, menu);

            return true;
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
        }

        public void buttonplus(View view){
            Dialog dialog = new Dialog();
            Bundle args = new Bundle();
            args.putString("bulan", Sbulan);
            dialog.show(getSupportFragmentManager(), "example dialog");

        }

        @Override
        public void onJanuariClick(int position) {
            Intent intent = new Intent(this, DetailJanuariActivity.class);
            intent.putExtra("acara", arrayJanuari.get(position).getnAcara());
            intent.putExtra("tanggal", arrayJanuari.get(position).getnTanggal());
            intent.putExtra("deskripsi", arrayJanuari.get(position).getnDeskripsi());
            intent.putExtra("phone", arrayJanuari.get(position).getnTelp());
            startActivity(intent);
        }

        private void loadJson(){
            StringRequest reqData = new StringRequest(Request.Method.POST, ServerAPI.URL_TAMPIL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.cancel();
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
                        pd.cancel();
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


        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            return super.onOptionsItemSelected(item);
        }


        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {

            return true;
        }
    }