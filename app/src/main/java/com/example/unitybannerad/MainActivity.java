package com.example.unitybannerad;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class MainActivity extends AppCompatActivity implements IUnityAdsInitializationListener {
    BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UnityAds.initialize(getApplicationContext(), "YOUR_GAME_ID", true, this); // Place your game ID from unity dashboard 
        bannerView = new BannerView(this, "PLACEMENT_ID", new UnityBannerSize(320, 50)); //Paste your placement ID from unity dashboard
        RelativeLayout relativeLayout = findViewById(R.id.bannerAd);
        relativeLayout.addView(bannerView);
        bannerView.setListener(new BannerView.IListener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
                Toast.makeText(MainActivity.this, "Banner Loaded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {
                Toast.makeText(MainActivity.this, "Banner Clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                Toast.makeText(MainActivity.this, "Banner failed to load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBannerLeftApplication(BannerView bannerView) {

            }
        });
    }

    @Override
    public void onInitializationComplete() {
        bannerView.load();
    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

    }
}
