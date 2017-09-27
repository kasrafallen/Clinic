package ir.gooble.clinic.init;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.User;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class InitDrawer implements View.OnClickListener {
    public static final String LOGOUT = "خروج";

    private BaseActivity context;
    private int width;
    private int profile;
    private int image;
    private int margin;
    private int item_size;
    private int icon;

    private AppText text;
    private View sign;

    public InitDrawer(BaseActivity context, float[] dimen) {
        this.context = context;
        this.width = (int) (3f * dimen[0] / 4f);
        this.profile = Util.toPx(150, context);
        this.image = (int) (2f * profile / 3f);
        this.margin = Util.toPx(15, context);
        this.item_size = Util.toPx(60, context);
        this.icon = Util.toPx(22, context);

        int size_dp = Util.toPx(280, context);
        if (size_dp < width) {
            width = size_dp;
        }
    }

    public View create() {
        ScrollView scrollView = new ScrollView(context);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(width, -1);
        params.gravity = Gravity.RIGHT;
        scrollView.setLayoutParams(params);
        scrollView.setBackgroundResource(R.color.drawer_background);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(profile());
        fillLayout(layout);

        scrollView.addView(layout);
        return scrollView;
    }

    private void fillLayout(LinearLayout layout) {
        int counter = 0;
        for (int i = 0; i < Attributes.MAIN_FIELDS.length; i++) {
            int index = i + 1;
            if (index % 2 == 0) {
                layout.addView(item(Attributes.MAIN_FIELDS[i]));
                layout.addView(item(Attributes.MAIN_FIELDS[i - 1]));
                counter = counter + 2;
            }
        }
        if (counter < Attributes.MAIN_FIELDS.length) {
            layout.addView(item(Attributes.MAIN_FIELDS[Attributes.MAIN_FIELDS.length - 1]));
        }
        layout.addView(item(LOGOUT));
    }

    private View item(String mainField) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, item_size);
        layout.setLayoutParams(params);
        layout.setTag(mainField);
        layout.setClickable(true);
        Util.setBackground(layout, context);
        layout.setOnClickListener(this);

        layout.addView(text(mainField));
        layout.addView(icon(mainField));

        if (UserInstance.isEmpty(context) && mainField.equals(LOGOUT)) {
            sign = layout;
            updateSign();
        }
        return layout;
    }

    private View icon(String mainField) {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(icon, icon);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.setMargins(margin, 0, margin, 0);
        view.setLayoutParams(params);
        int resource = Attributes.getResource(mainField);
        if (resource == 0) {
            resource = R.mipmap.z_exit;
        }
        view.setBackgroundResource(resource);
        return view;
    }

    private View text(String mainField) {
        AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2, 1f);
        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        text.setLayoutParams(params);
        text.setSingleLine();
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        text.setText(mainField);
        return text;
    }

    private View profile() {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, profile);
        params.bottomMargin = margin;
        layout.setLayoutParams(params);

        layout.addView(top());
        layout.addView(image());
        return layout;
    }

    private View top() {
        RelativeLayout top = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            top.setElevation(20);
        }
        top.setBackgroundResource(R.color.drawer);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, (int) (2f * profile / 3f));
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        top.setLayoutParams(params);
        top.addView(name());
        return top;
    }

    private View name() {
        text = new AppText(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
        params.rightMargin = image + 2 * margin;
        params.leftMargin = margin;
        text.setLayoutParams(params);
        text.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
        text.setMaxLines(2);
        text.setEllipsize(TextUtils.TruncateAt.END);
        text.setTextSize(1, 12);
        text.setTextColor(Color.WHITE);
        updateName();
        return text;
    }

    private View image() {
        CircleImageView imageView = new CircleImageView(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(25);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(image, image);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(0, 0, margin, 0);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.y_def_user_profile);
        return imageView;
    }

    @Override
    public void onClick(View view) {
        context.run((String) view.getTag(), context, view, null);
    }

    public void update() {
        updateName();
        updateSign();
    }

    private void updateSign() {
        if (sign == null) {
            return;
        }
        if (!UserInstance.isEmpty(context)) {
            sign.setVisibility(View.VISIBLE);
        } else {
            sign.setVisibility(View.GONE);
        }
    }

    private void updateName() {
        if (text == null) {
            return;
        }
        if (!UserInstance.isEmpty(context)) {
            User user = UserInstance.getUser(context);
            text.setText(user.getName() + "\n" + user.getMobile_number());
        } else {
            text.setText("");
        }
    }
}
