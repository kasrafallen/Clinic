package ir.gooble.clinic.init;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ir.gooble.clinic.activity.SignActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class InitSign extends BaseInit {
    private SignActivity context;
    private int margin;

    public InitSign(BaseActivity context) {
        super(context);
        this.context = (SignActivity) context;
        this.margin = Util.toPx(20, context);
    }

    @Override
    protected View create() {
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        layout.addView(message());
        return layout;
    }

    private View message() {
        AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        text.setLayoutParams(params);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 15);
        text.setText("برای ورود یا ساخت حساب کاربری خود شماره تلفن همراه خود را وارد کنید");
        return text;
    }
}
