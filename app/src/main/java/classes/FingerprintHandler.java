package classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    LottieAnimationView lottieAnimationView;
    private boolean result111=false;
    private callback CB;

    public FingerprintHandler(Context context,callback CB) {
        this.context = context;
        this.CB=CB;
    }

    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject)
    {
        CancellationSignal cancellationSignal=new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject, cancellationSignal ,0,this,null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        //this.update("There was an auth Erorr. " + errString , false);
        CB.NoAction();
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        //this.update("Authentication failed. "  , false);
        CB.falseAction();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        //this.update("Erorr: " + helpString , false);
        CB.tryAction();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        //update("",true);
        CB.trueAction();
        CustomToast.showToast("خوش آمدید!",context);
    }


    /*private void update(String s, boolean b) {

        if(b)
        {
            lottieAnimationView.setVisibility(View.INVISIBLE);
            lottieAnimationView_true.setVisibility(View.INVISIBLE);
            lottieAnimationView_false.setVisibility(View.VISIBLE);
            //lottieAnimationView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.));
            //lottieAnimationView.setAnimation(R.raw.finger_error);
            //lottieAnimationView.playAnimation();
            CustomToast.showToast("اثر انگشت با موفقیت ثبت شد",context);
            result111 = true;

        }
        else
        {
            lottieAnimationView.setVisibility(View.INVISIBLE);
            lottieAnimationView_true.setVisibility(View.VISIBLE);
            lottieAnimationView_false.setVisibility(View.INVISIBLE);
            //lottieAnimationView.setAnimation(R.raw.finger_ok);
            //lottieAnimationView.playAnimation();
            CustomToast.showToast("مشکل در ثبت اثر انگشت",context);
            result111 = false;

        }

    }*/
    public interface callback{
        void trueAction();
        void falseAction();
        void tryAction();
        void NoAction();
    }
}
