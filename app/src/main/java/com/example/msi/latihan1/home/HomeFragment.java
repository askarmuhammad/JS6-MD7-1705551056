package com.example.msi.latihan1.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.msi.latihan1.JanuariActivity;
import com.example.msi.latihan1.MainActivity;
import com.example.msi.latihan1.OrganicActivity;
import com.example.msi.latihan1.R;

import java.util.Calendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

//    private Context context;
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context=context;
//    }
    private HomeViewModel homeViewModel;
    ImageButton imageButton;
    private TextClock currenttime;
    private ImageView Januari;
//    String s1[];
//    RecyclerView recyclerView;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View tombol = inflater.inflate(R.layout.fragment_home, container, false);
//        s1 = getResources().getStringArray(R.array.list_tahun_lagu);
//        recyclerView = root.findViewById(R.id.recycleviewlist);

        currenttime = root.findViewById(R.id.text_clock);
//        Calendar cal = Calendar.getInstance();
//        TimeZone tz = cal.getTimeZone();
//        Log.d("Time zone","="+tz.getDisplayName());

//        MyAdapter myAdapter = new MyAdapter(this, s1);
        Januari = (ImageView) root.findViewById(R.id.januari);
        final TextView textView = root.findViewById(R.id.ayo);
        organicButton(tombol);

        String text = "ayo, tambahkan event mu!";
        Spannable ss = new SpannableString(text);
        StyleSpan bold = new StyleSpan(Typeface.BOLD);
        UnderlineSpan underlineSpan = new UnderlineSpan();

        ss.setSpan(bold, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(underlineSpan, 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        Januari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JanuariActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private void organicButton(View tombol) {
    }

}