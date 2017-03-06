package ir.gooble.clinic.init;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.ReserveActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.util.CalendarUtil;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppButton;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitReserve extends BaseInit {
    private static final int TOOLBAR_ID = +8248114;

    private ReserveActivity context;
    private AppToolbar toolbar;
    private AppText text;
    private int tab;
    private int logo;
    private int margin;

    public InitReserve(BaseActivity context) {
        super(context);
        this.context = (ReserveActivity) context;
        this.tab = Util.toPx(50, context);
        this.logo = Util.toPx(20, context);
        this.margin = Util.toPx(10, context);
    }

    @Override
    protected View create() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        layout.addView(toolbar());
        layout.addView(tab());
        layout.addView(recycler());
        return layout;
    }

    private View recycler() {
        NestedScrollView scrollView = new NestedScrollView(context);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(item());

        scrollView.addView(layout);
        scrollView.setMinimumHeight((int) (dimen[1] - tab - toolbar.getSize()));
        return scrollView;
    }

    private View item() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        return layout;
    }

    private View tab() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, tab));
        layout.setBackgroundResource(R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(4);
        }

        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        box.setLayoutParams(params);

        text = new AppText(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-2, -2);
        params1.gravity = Gravity.CENTER_VERTICAL;
        text.setLayoutParams(params1);
        text.setSingleLine();
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        text.setText(getDate());

        View view = new View(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(logo, logo);
        params2.gravity = Gravity.CENTER_VERTICAL;
        params2.leftMargin = margin;
        view.setLayoutParams(params2);
        view.setBackgroundResource(R.mipmap.w_date);

        box.addView(text);
        box.addView(view);

        layout.addView(box);
        layout.addView(button(RelativeLayout.ALIGN_PARENT_RIGHT));
        layout.addView(button(RelativeLayout.ALIGN_PARENT_LEFT));
        return layout;
    }

    private View button(int gravity) {
        AppButton button = new AppButton(context);
        button.changeRipple();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(tab, tab);
        params.addRule(gravity);
        button.setLayoutParams(params);
        button.setScaleX(0.7f);
        button.setScaleY(0.7f);
        button.setBackgroundResource(R.drawable.ic_navigate_next);
        if (gravity == RelativeLayout.ALIGN_PARENT_LEFT) {
            button.setRotation(180);
        }
        button.setOnClickListener(context);
        return button;
    }

    private String getDate() {
        return new CalendarUtil().getDate();
    }

    private View toolbar() {
        toolbar = new AppToolbar(context, true, "تعیین وقت", true);
        toolbar.setId(TOOLBAR_ID);
        return toolbar;
    }
}
