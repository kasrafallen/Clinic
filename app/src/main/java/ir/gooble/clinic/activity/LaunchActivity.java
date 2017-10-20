package ir.gooble.clinic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.instance.ClinicInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.util.Util;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LaunchActivity extends Activity {

    private FrameLayout layout;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
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

            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
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
