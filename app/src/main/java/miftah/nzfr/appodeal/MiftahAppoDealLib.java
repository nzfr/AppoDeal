package miftah.nzfr.appodeal;

import android.app.Activity;
import android.util.Log;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.RewardedVideoCallbacks;


public abstract class MiftahAppoDealLib {
    private Activity activity;
    private String app_key;
    private int adBanner, adReward, adInters;
    private String TAG = "Miftah Customized Appodeal library";

    public MiftahAppoDealLib(Activity activity) {
        this.activity = activity;
        app_key = activity.getResources().getString(R.string.app_key);
        adBanner = Appodeal.BANNER;
        adInters = Appodeal.INTERSTITIAL;
        adReward = Appodeal.REWARDED_VIDEO;
        Appodeal.initialize(activity, app_key, adBanner | adInters | adReward, true);
    }

    public void createBanner() {
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                Log.d(TAG, "onBannerLoaded: height = " + height + ", is precache : " + isPrecache);
                Appodeal.show(activity, adBanner);
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

    public void createInters() {
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean isPrecache) {
                Log.d(TAG, "onInterstitialLoaded: is precache : " + isPrecache);
                Appodeal.show(activity, adInters);
                interLoaded(isPrecache);
            }

            @Override
            public void onInterstitialFailedToLoad() {
                Log.d(TAG, "inter failed to load ... ");
                interFailedToLoad();
            }

            @Override
            public void onInterstitialShown() {
                Log.d(TAG, "inter has been shown ... ");
                interShown();
            }

            @Override
            public void onInterstitialClicked() {
                Log.d(TAG, "inter has been clicked ... ");
                interClicked();
            }

            @Override
            public void onInterstitialClosed() {
                Log.d(TAG, "inter has been closed ... ");
                interClosed();
            }

            @Override
            public void onInterstitialExpired() {
                Log.d(TAG, "inter has been expired ... ");
                interExpired();
            }
        });
    }

    public void createRewardVid() {
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded(boolean isPrecache) {
                Log.d(TAG, "onRewardedVideoLoaded: is preCache : "+isPrecache);
                Appodeal.show(activity,adReward);
                rewardLoaded(isPrecache);
            }

            @Override
            public void onRewardedVideoFailedToLoad() {
                Log.d(TAG, "reward failed to load ... ");
                rewardFailedToLoad();
            }

            @Override
            public void onRewardedVideoShown() {
                Log.d(TAG, "reward has been shown ... ");
                rewardShown();
            }

            @Override
            public void onRewardedVideoFinished(double amount, String name) {
                Log.d(TAG, "onRewardedVideoFinished: amount = "+amount+" name = "+name);
                rewardFinished(amount,name);
            }

            @Override
            public void onRewardedVideoClosed(boolean finished) {
                Log.d(TAG, "reward video closed,finished = "+finished);
                rewardClosed(finished);
            }

            @Override
            public void onRewardedVideoExpired() {
                Log.d(TAG, "reward has been expired ... ");
                rewardExpired();
            }
        });
    }

    // ----------------- banner ads callback functions block start ------------------ //
    public abstract void bannerLoaded(int height, boolean isPrecache);
    public abstract void bannerFailedToLoad();
    public abstract void bannerShown();
    public abstract void bannerClicked();
    public abstract void bannerExpired();
    // -----------------  banner ads callback functions block end  ------------------ //
    // ----------------- inters ads callback functions block start ------------------ //
    public abstract void interLoaded(boolean isPrecache);
    public abstract void interFailedToLoad();
    public abstract void interShown();
    public abstract void interClicked();
    public abstract void interClosed();
    public abstract void interExpired();
    // -----------------  inters ads callback functions block  end ------------------ //
    // ----------------- reward ads callback functions block start ------------------ //
    public abstract void rewardLoaded(boolean isPrecache);
    public abstract void rewardFailedToLoad();
    public abstract void rewardShown();
    public abstract void rewardFinished(double amount,String name);
    public abstract void rewardClosed(boolean finished);
    public abstract void rewardExpired();
    // -----------------  reward ads callback functions block end  ------------------ //
}
