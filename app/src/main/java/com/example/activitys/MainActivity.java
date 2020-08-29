package com.example.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest.permission;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.admin_adver.R;

import classes.CustomToast;
import classes.FingerprintHandler;

public class MainActivity extends AppCompatActivity implements FingerprintHandler.callback{
    final String Admin_username="admin";
    final String Admin_password="admin";
    //private static final int REQ_intcode = 85;
    //private static final String KEY_NAME = "FingerPrintKey";
    //KeyStore keyStore;
    //Cipher cipher;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    boolean result = false;
    private AlertDialog alertDialog;
    private LottieAnimationView anim;
    private LottieAnimationView dissmisDialog;
    private ImageButton finger_print_icon;
    private TextView err_text;
    private int i=0;
    private int z=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //login
        final EditText username=(EditText)findViewById(R.id.email_ed);
        final EditText password=(EditText)findViewById(R.id.password_ed);
        Button login_btn=(Button)findViewById(R.id.btn_enter);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(Admin_password))
                    if (username.getText().toString().equals(Admin_username)) {
                        Intent intent = new Intent(MainActivity.this, show_bottom_navigation.class);
                        startActivity(intent);
                        CustomToast.showToast("خوش آمدید!",MainActivity.this);
                    }
            }
        });

        //fingerprint
        CheckFingerprintCondition();
        /*KeyguardManager keyguardManager=(KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager=(FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);

            dialog(fingerprintManager);

        }*/

        finger_print_icon=findViewById(R.id.finger_icon);
        finger_print_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                z=0;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    CheckFingerprintCondition();
                }
                else CustomToast.showToast("این قابلیت در گوشی شما پشتیبانی نمیشود",MainActivity.this);
            }
        });


    }
    private void dialog(FingerprintManager fingerprintManager) {

        //TextView res=findViewById(R.id.res_txt);
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(true);
        builder.setView(view1);
        alertDialog = builder.create();
        anim = view1.findViewById(R.id.anim_fingerprint);
        dissmisDialog = view1.findViewById(R.id.go_down);
        err_text = view1.findViewById(R.id.show_state);

        dissmisDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            FingerprintHandler fingerprintHandler= new FingerprintHandler(this ,this);
            fingerprintHandler.startAuth(fingerprintManager, null);

        }
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.background_edit_texts_user);
        alertDialog.show();
    }

    private void CheckFingerprintCondition(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager=(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            if(fingerprintManager.isHardwareDetected())
            {
                if (ContextCompat.checkSelfPermission(this, permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED)
                {
                    if(keyguardManager.isDeviceSecure()) {
                        if (fingerprintManager.hasEnrolledFingerprints()) {
                            dialog(fingerprintManager);
                        }
                        else
                        {
                            CustomToast.showToast("هیچ اثر انگشتی ثبت نشده است",this);
                        }
                    }
                    else
                    {
                        CustomToast.showToast("صفحه قفل شما با حسگر اثر انگشت ایمن نشده است",this);
                    }
                }
                else
                {
                    CustomToast.showToast("اجازه دسترسی به حسگر اثر انگشت وجود ندارد",this);
                }
            }
            else
            {
                CustomToast.showToast("سخت افزار مورد نظر یافت نشد",this);
            }
        }
    }

    @Override
    public void trueAction() {
        err_text.setText("با موفقیت تشخیص داده شد!");
        err_text.setVisibility(View.VISIBLE);
        anim.setAnimation(R.raw.finger_ok);
        anim.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,show_bottom_navigation.class));
                finish();
            }
        }, 1300);

    }

    @Override
    public void falseAction() {

        if(i<4){
            anim.setAnimation(R.raw.finger_error);
            anim.playAnimation();
            i++;
            int n = 5-i;
            err_text.setText("اثر اشتباه.تا " + n + " بار دیگر میتوانید تلاش کنید.");
            err_text.setVisibility(View.VISIBLE);
        }
        else{
            err_text.setText("شما 5 بار اثر اشتباه ثبت کردید.");
            err_text.setVisibility(View.VISIBLE);
            CustomToast.showToast("برای تلاش بعدی با اثر انگشت، باید لحظاتی انتظار بکششید",MainActivity.this);
            anim.setAnimation(R.raw.finger_error);
            anim.playAnimation();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertDialog.dismiss();
                }
            }, 1400);
        }
    }

    @Override
    public void tryAction() {
            anim.setAnimation(R.raw.finger_error);
            anim.playAnimation();
            err_text.setText("خطا در تشخیص،لطفا دوباره امتحان کنید.");
            err_text.setVisibility(View.VISIBLE);
    }

    @Override
    public void NoAction() {
        anim.setAnimation(R.raw.finger_error);
        anim.playAnimation();
        if (i == 4)
        {
            err_text.setText("شما 5 بار اثر اشتباه ثبت کردید.");
            err_text.setVisibility(View.VISIBLE);
            CustomToast.showToast("برای تلاش بعدی با اثر انگشت، باید لحظاتی انتظار بکششید",MainActivity.this);
            anim.setAnimation(R.raw.finger_error);
            anim.playAnimation();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertDialog.dismiss();
                }
            }, 1400);
        }
        else{
            err_text.setText("در حال حاضر شما نمیتوانید با اثر انگشت وارد شوید!");
            err_text.setVisibility(View.VISIBLE);
        }

    }

}