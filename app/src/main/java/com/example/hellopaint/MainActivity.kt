package com.example.hellopaint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hellopaint.ads.AdMobBanner
import com.example.hellopaint.ads.interstitialAds
import com.example.hellopaint.ads.rewardedAds
import com.example.hellopaint.ui.theme.HelloPaintTheme
import com.example.hellopaint.uiScreens.DrawScreen
import com.farimarwat.composenativeadmob.nativead.BannerAdAdmobMedium
import com.farimarwat.composenativeadmob.nativead.BannerAdAdmobSmall
import com.farimarwat.composenativeadmob.nativead.rememberNativeAdState
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        setContent {
            val adstate = rememberNativeAdState(context = this@MainActivity, adUnitId = "ca-app-pub-3940256099942544/2247696110")
            HelloPaintTheme {
            //DrawScreen()

                //Test Ads Here
                //AdMobBanner(modifier = Modifier.padding(10.dp).fillMaxWidth())
                Column {
                    Button(onClick = { interstitialAds(this@MainActivity) }) {
                        Text(text = "Click for Interstitial Ad")
                    }
                    Button(onClick = { rewardedAds(this@MainActivity) }) {
                        Text(text = "Click for Rewarded Ad")
                    }
                    Text(text = "2 Native Ads Below")
                        //Small Banner
                        BannerAdAdmobSmall(loadedAd = adstate)
                        Spacer(modifier = Modifier.fillMaxWidth())
                        //Medium Banner
                        BannerAdAdmobMedium(loadedAd = adstate)
                }
            }
        }
    }
}

