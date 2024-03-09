package com.example.hellopaint.ads

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner(modifier: Modifier=Modifier){
    AndroidView(modifier=modifier,factory ={
        AdView(it).apply{
            setAdSize(AdSize.BANNER)
            adUnitId="ca-app-pub-3940256099942544/9214589741"
            loadAd(AdRequest.Builder().build())
        }
    } )
}

@Composable
@Preview(showBackground = true)
fun AdMobBannerPreview(){
    AdMobBanner()
}