package com.example.msi.latihan1;

import android.net.Uri;

public class ListJanuari {
    private Uri mImageresource;
    private String mNamaacara;
    private String mTanggalacara;
    private String mDeskripsiacara;
    private String mnumberacara;

    public ListJanuari(){}

    public ListJanuari(Uri imageresource, String namaacara, String tanggalacara, String deskripsiacara, String numberacara ){
        mImageresource = imageresource;
        mNamaacara = namaacara;
        mTanggalacara = tanggalacara;
        mDeskripsiacara = deskripsiacara;
        mnumberacara = numberacara;
    }

    public void SetImageResource(Uri mImageresource){
        this.mImageresource = mImageresource;
    }

    public void SetNamaAcara(String mNamaacara){
        this.mNamaacara = mNamaacara;
    }

    public void SetTanggalAcara(String mTanggalacara){
        this.mTanggalacara = mTanggalacara;
    }

    public void SetmDeskripsiacara(String mDeskripsiacara){
        this.mDeskripsiacara = mDeskripsiacara;
    }

    public void SetMnumberacara(String mnumberacara){
        this.mnumberacara = mnumberacara;
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
    public String getMnumberacara(){
        return mnumberacara;
    }
}
