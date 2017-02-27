package ir.gooble.clinic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import ir.gooble.clinic.util.Util;

public class LaunchActivity extends Activity {

    private FrameLayout layout;

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

    @Override
    public void finish() {
        super.finish();
    }
}
