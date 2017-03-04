package ir.gooble.clinic.view;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import ir.gooble.clinic.util.Util;

public class AppButton extends RelativeLayout {

    private final int toolbar_size;
    private boolean isMaterial;
    private RelativeLayout mid;
    private View view;

    private Context context;

    public AppButton(Context context) {
        super(context);
        this.context = context;

        toolbar_size = Util.getToolbarSize(context);
        setLayoutParams(new ViewGroup.LayoutParams(toolbar_size, toolbar_size));

        isMaterial = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        if (isMaterial) {
            addView(mid(context));
            Util.setRipple(mid, context, true);
        } else {
            addView(view(context));
            Util.setBackground(this, context);
        }
    }

    @Override
    public void setBackgroundResource(int resid) {
        view.setBackgroundResource(resid);
    }

    private View view(Context context) {
        view = new View(context);
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        float scale = 0.65f;
        if (!isMaterial) {
            view.setScaleX((5f / 6f) * scale);
            view.setScaleY((5f / 6f) * scale);
        } else {
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
        return view;
    }

    private View mid(Context context) {
        int mid_size = (int) (5f * toolbar_size / 6f);
        mid = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mid_size, mid_size);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mid.setLayoutParams(params);
        mid.addView(view(context));
        return mid;
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        if (isMaterial) {
            mid.setOnClickListener(listener);
        } else {
            super.setOnClickListener(listener);
        }
    }

    public void changeRipple() {
        if (isMaterial) {
            Util.setRipple(mid, context, false);
        }
    }
}
