package ir.gooble.clinic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.instance.ClinicInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.instance.TimeInstance;
import ir.gooble.clinic.model.TimeStamp;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.PromptUtil;
import ir.gooble.clinic.util.Util;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LaunchActivity extends Activity {

    private FrameLayout layout;
    private PromptUtil promptUtil;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptUtil = new PromptUtil(this);
        setContentView(createView());
        if (!Util.isDimen(this)) {
            setObserver();
        } else {
            startApp();
        }
    }

    private View createView() {
        layout = new FrameLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return layout;
    }

    private void setObserver() {
        layout.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        Util.setDimen(new float[]{layout.getWidth(), layout.getHeight()}, LaunchActivity.this);
                        startApp();
                    }
                });
    }

    private void startApp() {
        ClinicInstance.getClinic(this, new InstanceResult() {
            @Override
            public void onResult(Object[] objects) {
                if (TimeInstance.isEmpty(LaunchActivity.this)) {
                    getTime();
                } else {
                    redirect();
                }
            }
        });
    }

    private void getTime() {
        new Rest(this, Api.GET_TIME).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                promptUtil.hide();
                try {
                    TimeStamp timeStamp = new Gson().fromJson(response, TimeStamp.class);
                    if (timeStamp != null && timeStamp.isSuccess()) {
                        long time = (timeStamp.getTimestamp() - timeStamp.getGmtOffset()) * 1000;
                        TimeInstance.setTime(LaunchActivity.this, time);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                redirect();
            }

            @Override
            public void onError(String error) {
                promptUtil.error(this, error);
            }

            @Override
            public void onInternet() {
                promptUtil.internet(this);
            }

            @Override
            public void onBefore() {
                promptUtil.progress();
            }

            @Override
            public void onClick() {
                getTime();
            }
        });
    }

    private void redirect() {
        MainActivity.start(LaunchActivity.this);
        finish();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, LaunchActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        activity.startActivity(intent);
    }
}
