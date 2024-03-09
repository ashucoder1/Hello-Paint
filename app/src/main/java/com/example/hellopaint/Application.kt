package com.example.hellopaint

import androidx.multidex.MultiDexApplication
import com.example.hellopaint.ads.AppOpenAdsManager
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.appopen.AppOpenAd

class Application:MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this )
        AppOpenAdsManager(this,"ca-app-pub-3940256099942544/9257395921")
    }
}