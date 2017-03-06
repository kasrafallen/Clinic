package ir.gooble.clinic.init;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.ReserveActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.util.CalendarUtil;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppButton;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitReserve extends BaseInit {
    private static final int TOOLBAR_ID = +8248114;
    private static final int IMAGE_ID = +84848555;

    private ReserveActivity context;
    private AppToolbar toolbar;
    private AppText text;
    private int tab;
    private int logo;
    private int margin;
    private int image;
    private int detail;
    private int time;
    private int timer;

    public InitReserve(BaseActivity context) {
        super(context);
        this.context = (ReserveActivity) context;
        this.tab = Util.toPx(50, context);
        this.logo = Util.toPx(20, context);
        this.margin = Util.toPx(10, context);
        this.detail = Util.toPx(80, context);
        this.image = Util.toPx(55, context);
        this.time = Util.toPx(60, context);
        this.timer = Util.toPx(40, context);
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

    private View item() {
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundResource(R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(15);
        }
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = margin;
        params.bottomMargin = margin;
        layout.setLayoutParams(params);

        layout.addView(detail());
        layout.addView(time());
        return layout;
    }

    private View time() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, time));

        HorizontalScrollView view = new HorizontalScrollView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(params);
        view.setHorizontalScrollBarEnabled(false);
        view.setVerticalScrollBarEnabled(false);

        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < 6; i++) {
            box.addView(time(i));
        }

        view.addView(box);
        layout.addView(view);
        return layout;
    }

    private View time(int index) {
        Button text = new Button(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, timer);
        params.gravity = Gravity.CENTER_VERTICAL;
        if (index == 0 || index == 5) {
            if (index == 5) {
                params.rightMargin = margin;
                params.leftMargin = margin / 2;
            } else {
                params.leftMargin = margin;
                params.rightMargin = margin / 2;
            }
        } else {
            params.rightMargin = margin / 2;
            params.leftMargin = margin / 2;
        }
        text.setLayoutParams(params);
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 15);
        Util.setText(text, context);
        text.setBackgroundResource(R.drawable.timer_background);

        if (index == 2 || index == 3 || index == 5) {
            text.setAlpha(0.5f);
        }

        text.setText((index + 9) + " - " + (index + 10));
        return text;
    }

    private View detail() {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, detail);
        layout.setLayoutParams(params);
        layout.addView(image());
        layout.addView(box());
        return layout;
    }

    private View box() {
        AppText text = new AppText(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.LEFT_OF, IMAGE_ID);
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        text.setLayoutParams(params);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 15);
        text.setText(getNameSpan());
        return text;
    }

    private SpannableString getNameSpan() {
        String text = Attributes.DOCTOR_NAME + "\n" + Attributes.DOC_DESCRIPTION.replaceAll("\n", "");
        SpannableString span = new SpannableString(text);
        span.setSpan(new AbsoluteSizeSpan(11, true), text.indexOf("\n"), text.length(), 0);
        span.setSpan(new ForegroundColorSpan(Color.GRAY), text.indexOf("\n"), text.length(), 0);
        return span;
    }

    private View image() {
        CircleImageView imageView = new CircleImageView(context);
        imageView.setId(IMAGE_ID);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(image, image);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(margin, 0, margin, 0);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.mipmap.y_def_doctor_profile_2);
        return imageView;
    }
}
