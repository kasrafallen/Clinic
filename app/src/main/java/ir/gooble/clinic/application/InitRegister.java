package ir.gooble.clinic.application;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Space;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.RegisterActivity;
import ir.gooble.clinic.util.ImageUtil;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppToolbar;

public class InitRegister extends BaseInit {
    private static final int IMAGE = +54822525;

    private RegisterActivity context;
    private int toolbar;
    private int profile;
    private int add;
    private int small_margin;
    private int function;
    private int button;

    public InitRegister(BaseActivity context) {
        super(context);
        this.context = (RegisterActivity) context;
        int toolbar_size = Util.getToolbarSize(context);
        this.toolbar = toolbar_size * 3;
        this.profile = toolbar_size * 2;
        this.add = toolbar_size / 2;
        this.small_margin = Util.toPx(5, context);
        this.function = Util.toPx(70, context);
        this.button = Util.toPx(40, context);
    }

    @Override
    protected View create() {
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(toolbar());
        layout.addView(recycler());
        layout.addView(function());
        return layout;
    }

    private View recycler() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1f));

        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);

        layout.addView(scrollView);
        return layout;
    }

    private View function() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, function);
        layout.setLayoutParams(params);
        layout.addView(space(1f));
        layout.addView(button(true));
        layout.addView(space(2f));
        layout.addView(button(false));
        layout.addView(space(1f));
        return layout;
    }

    private View button(boolean isExit) {
        Button buttonItem = new Button(context);
        buttonItem.setTag(String.valueOf(isExit));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonItem.setElevation(25);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, button);
        params.gravity = Gravity.CENTER_VERTICAL;
        buttonItem.setLayoutParams(params);
        buttonItem.setSingleLine();
        buttonItem.setTextSize(1, 15);
        buttonItem.setGravity(Gravity.CENTER);
        Util.setText(buttonItem, context);
        if (isExit) {
            buttonItem.setTextColor(Color.DKGRAY);
            buttonItem.setBackgroundResource(R.drawable.button_white_background);
            buttonItem.setText("انصراف");
        } else {
            buttonItem.setTextColor(Color.WHITE);
            buttonItem.setBackgroundResource(R.drawable.button_theme_background);
            buttonItem.setText("ثبت");
        }
        buttonItem.setOnClickListener(context);
        return buttonItem;
    }

    private View space(float size) {
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, size));
        return space;
    }

    private View toolbar() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, toolbar));

        AppToolbar toolbar = new AppToolbar(context, true, "ثبت پرونده", true);
        toolbar.setMaximize();
        toolbar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));

        CircleImageView imageView = new CircleImageView(context);
        imageView.setId(IMAGE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(20);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(profile, profile);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.y_def_user_profile);
        Util.setBackground(imageView, context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtil.pickImage(context);
            }
        });

        View addView = new View(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addView.setElevation(21);
        }
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(add, (int) (67f * add / 63f));
        params1.addRule(RelativeLayout.ALIGN_BOTTOM, IMAGE);
        params1.addRule(RelativeLayout.ALIGN_RIGHT, IMAGE);
        params1.bottomMargin = small_margin;
        params1.rightMargin = small_margin;
        addView.setLayoutParams(params1);
        addView.setBackgroundResource(R.mipmap.y_add_image);

        layout.addView(toolbar);
        layout.addView(imageView);
        layout.addView(addView);
        return layout;
    }
}
