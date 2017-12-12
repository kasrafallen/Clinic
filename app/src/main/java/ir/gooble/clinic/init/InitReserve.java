package ir.gooble.clinic.init;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.ReserveActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.util.CalendarUtil;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppButton;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitReserve extends BaseInit implements ViewPager.OnPageChangeListener {

    private static final int TOOLBAR_ID = +8248114;
    private static final int IMAGE_ID = +84848555;
    private static final int RIGHT = +8414847;
    private static final int LEFT = +54157659;
    private static final int DATE = +54157452;

    private ReserveActivity context;
    private LinearLayout layout;
    private AppToolbar toolbar;
    private int tab;
    private int logo;
    private int margin;
    private int image;
    private int detail;
    private int time;
    private int timer;

    private ViewPager pager;

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

        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        scrollView.addView(layout);
        scrollView.setMinimumHeight((int) (dimen[1] - tab - toolbar.getSize()));
        return scrollView;
    }

    private View tab() {
        pager = new ViewPager(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, tab);
        pager.setLayoutParams(params);
        pager.setAdapter(new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = createItem(position);
                container.addView(view, 0);
                return view;
            }

            @Override
            public int getCount() {
                return 1000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        pager.addOnPageChangeListener(this);
        return pager;
    }

    private View createItem(int position) {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setTag(position + "");
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        layout.setBackgroundResource(R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(4);
        }

        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        box.setLayoutParams(params);

        AppText text = new AppText(context);
        text.setId(DATE);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-2, -2);
        params1.gravity = Gravity.CENTER_VERTICAL;
        text.setLayoutParams(params1);
        text.setSingleLine();
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);

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

    private View button(final int gravity) {
        AppButton button = new AppButton(context);
        button.changeRipple();
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(tab, tab);
        params.addRule(gravity);
        button.setLayoutParams(params);
        button.setScaleX(0.7f);
        button.setScaleY(0.7f);
        button.setBackgroundResource(R.drawable.ic_navigate_next);
        if (gravity == RelativeLayout.ALIGN_PARENT_LEFT) {
            button.setRotation(180);
            button.setId(LEFT);
        } else {
            button.setId(RIGHT);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gravity == RelativeLayout.ALIGN_PARENT_RIGHT) {
                    if (pager.getCurrentItem() < (1000 - 1)) {
                        pager.setCurrentItem(pager.getCurrentItem() + 1);
                    }
                } else {
                    if (pager.getCurrentItem() != 0) {
                        pager.setCurrentItem(pager.getCurrentItem() - 1);
                    }
                }
            }
        });
        onPageSelected(0);
        return button;
    }

    private View toolbar() {
        toolbar = new AppToolbar(context, true, "تعیین وقت", true);
        toolbar.setId(TOOLBAR_ID);
        return toolbar;
    }

    private View item(Doctor doctor) {
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

        layout.addView(detail(doctor));
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

    private View detail(Doctor doctor) {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, detail);
        layout.setLayoutParams(params);
        layout.addView(image(doctor));
        layout.addView(box(doctor));
        return layout;
    }

    private View box(Doctor doctor) {
        AppText text = new AppText(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.LEFT_OF, IMAGE_ID);
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        text.setLayoutParams(params);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 15);
        String data = doctor.getFullName() + "\n" + doctor.getDoctorTitle();
        SpannableString span = new SpannableString(data);
        span.setSpan(new AbsoluteSizeSpan(11, true), data.indexOf("\n"), data.length(), 0);
        span.setSpan(new ForegroundColorSpan(Color.GRAY), data.indexOf("\n"), data.length(), 0);
        text.setText(span);
        return text;
    }

    private View image(Doctor doctor) {
        CircleImageView imageView = new CircleImageView(context);
        imageView.setId(IMAGE_ID);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(image, image);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(margin, 0, margin, 0);
        imageView.setLayoutParams(params);
        if (doctor.getPictureURL() == null) {
            imageView.setImageResource(R.mipmap.y_def_doctor_profile_2);
        } else {
            Picasso.with(context).load(doctor.getPictureURL()).fit().centerCrop().into(imageView);
        }
        return imageView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        View view = pager.findViewWithTag(position + "");
        Log.d("SHIT", "onPageSelected() returned: " + view);
        if (view == null) {
            return;
        }

        View left = view.findViewById(LEFT);
        View right = view.findViewById(RIGHT);
        TextView date = (TextView) view.findViewById(DATE);

        if (position == 0) {
            left.setVisibility(View.GONE);
        } else {
            right.setVisibility(View.VISIBLE);
        }

        date.setText(context.getDate(position));
        context.reset();
        layout.removeAllViews();
        context.sendRequest();
    }

    public void add(Doctor doctor, String response) {
        layout.addView(item(doctor));
    }
}
