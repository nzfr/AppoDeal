package miftah.nzfr.appodeal.AdTypes;

import android.app.Activity;
import android.util.Log;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;

public abstract class MiftahBanners {
    private Activity activity;
    private String app_key;
    private String TAG = "Miftah Customized Appodeal library";

    public MiftahBanners(Activity activity, String app_key) {
        this.activity = activity;
        this.app_key = app_key;
        Appodeal.initialize(activity, app_key, Appodeal.BANNER, true);
    }

    public void createBanner() {
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                Log.d(TAG, "onBannerLoaded: height = " + height + ", is precache : " + isPrecache);
                Appodeal.show(activity, Appodeal.BANNER);
                bannerLoaded(height, isPrecache);
            }

            @Override
            public void onBannerFailedToLoad() {
                Log.d(TAG, "banner failed to load ... ");
                bannerFailedToLoad();
            }

            @Override
            public void onBannerShown() {
                Log.d(TAG, "banner has been shown ... ");
                bannerShown();
            }

            @Override
            public void onBannerClicked() {
                Log.d(TAG, "banner has been clicked ... ");
                bannerClicked();
            }

            @Override
            public void onBannerExpired() {
                Log.d(TAG, "banner has been expired ... ");
                bannerExpired();
            }
        });
    }


    public abstract void bannerLoaded(int height, boolean isPrecache);
    public abstract void bannerFailedToLoad();
    public abstract void bannerShown();
    public abstract void bannerClicked();
    public abstract void bannerExpired();

}
