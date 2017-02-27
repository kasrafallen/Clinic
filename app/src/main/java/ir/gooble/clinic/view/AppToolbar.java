package ir.gooble.clinic.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import ir.gooble.clinic.R;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.util.Util;

public class AppToolbar extends Toolbar {

    private final int toolbar_size;

    public AppToolbar(Context context, boolean withNavigation, String title, boolean withBack) {
        super(context);
        toolbar_size = Util.getToolbarSize(context);

        setLayoutParams(new ViewGroup.LayoutParams(-1, toolbar_size));
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        setBackgroundResource(R.color.toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(12);
        }
        if (withNavigation) {
            addView(navigationButton(context));
        }
        if (title != null) {
            addView(text(context, title));
        }
        if (withBack) {
            addView(backButton(context));
        }
    }

    private View backButton(final Context context) {
        AppButton appButton = new AppButton(context);
        Toolbar.LayoutParams params = new LayoutParams(toolbar_size, toolbar_size);
        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        appButton.setLayoutParams(params);
        appButton.setBackgroundResource(R.drawable.ic_arrow_back_white_48dp);
        appButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).onBackPressed();
            }
        });
        return appButton;
    }

    private View text(Context context, String title) {
        AppText text = new AppText(context);
        Toolbar.LayoutParams params = new LayoutParams(-2, -2);
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        text.setLayoutParams(params);
        text.setSingleLine();
        text.setTextColor(Color.WHITE);
        text.setTextSize(1, 16);
        text.setText(title);
        return text;
    }

    private View navigationButton(final Context context) {
        AppButton appButton = new AppButton(context);
        Toolbar.LayoutParams params = new LayoutParams(toolbar_size, toolbar_size);
        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        appButton.setLayoutParams(params);
        appButton.setBackgroundResource(R.drawable.ic_menu_white_48dp);
        appButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) context).openDrawer();
            }
        });
        return appButton;
    }

    public int getSize() {
        return toolbar_size;
    }
}
