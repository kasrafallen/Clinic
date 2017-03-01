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
import ir.gooble.clinic.activity.DetailActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.util.Util;

public class AppToolbar extends Toolbar {
    private final static int SHARE = 102;
    private final static int BACK = 103;
    private final static int MENU = 104;

    private int toolbar_size;
    private boolean maximize;

    public AppToolbar(Context context) {
        super(context);
        toolbar_size = Util.getToolbarSize(context);

        setLayoutParams(new ViewGroup.LayoutParams(-1, toolbar_size));
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        setBackgroundResource(R.color.toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(12);
        }
    }

    public AppToolbar(Context context, boolean withShare, boolean withBack) {
        this(context);
        if (withShare) {
            addView(button(context, SHARE));
        }
        if (withBack) {
            addView(button(context, BACK));
        }
    }

    public AppToolbar(Context context, boolean withNavigation, String title, boolean withBack) {
        this(context);
        if (withNavigation) {
            addView(button(context, MENU));
        }
        if (title != null) {
            addView(text(context, title));
        }
        if (withBack) {
            addView(button(context, BACK));
        }
    }

    private View button(final Context context, final int mode) {
        AppButton appButton = new AppButton(context);
        final Toolbar.LayoutParams params = new LayoutParams(toolbar_size, toolbar_size);
        switch (mode) {
            case SHARE:
                appButton.setBackgroundResource(R.drawable.ic_share_white_48dp);
                params.gravity = Gravity.RIGHT | Gravity.TOP;
                break;
            case MENU:
                appButton.setBackgroundResource(R.drawable.ic_menu_white_48dp);
                params.gravity = Gravity.RIGHT | Gravity.TOP;
                break;
            case BACK:
                appButton.setBackgroundResource(R.drawable.ic_arrow_back_white_48dp);
                params.gravity = Gravity.LEFT | Gravity.TOP;
                break;
        }
        appButton.setLayoutParams(params);
        appButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mode) {
                    case SHARE:
                        if (context instanceof DetailActivity) {
                            ((DetailActivity) context).share(context);
                        }
                        break;
                    case MENU:
                        if (context instanceof BaseActivity) {
                            ((BaseActivity) context).openDrawer();
                        }
                        break;
                    case BACK:
                        if (context instanceof Activity) {
                            ((Activity) context).onBackPressed();
                        }
                        break;
                }
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

    public int getSize() {
        return toolbar_size;
    }


    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        if (maximize) {
            params.height = toolbar_size * 3;
        } else {
            params.height = toolbar_size;
        }
        params.width = -1;
        super.setLayoutParams(params);
    }

    public void setMaximize() {
        maximize = true;
    }
}
