package com.example.msi.latihan1.februari;

import android.net.Uri;

public class ListFebruari {
    private Uri mImageresource;
    private String mNamaacara;
    private String mTanggalacara;
    private String mDeskripsiacara;
    private String mnumberacara;

    public ListFebruari(Uri imageresource, String namaacara, String tanggalacara, String deskripsiacara, String numberacara ){
        mImageresource = imageresource;
        mNamaacara = namaacara;
        mTanggalacara = tanggalacara;
        mDeskripsiacara = deskripsiacara;
        mnumberacara = numberacara;
    }

    public Uri getImageResource() {
        return mImageresource;
    }

    public String getNamaAcara() {
        return mNamaacara;
    }

    public String getTanggalAcara(){
        return mTanggalacara;
    }
    public String getmDeskripsiacara(){
        return mDeskripsiacara;
    }
    public String getmNumberacara(){
        return mnumberacara;
    }
}
