package ir.gooble.clinic.init;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.DetailActivity;
import ir.gooble.clinic.adaptor.DetailAdaptor;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitDetail extends BaseInit {

    private static final int TOOLBAR_ID = +8248484;
    private static final int LOGO_ID = +2847774;
    private static final int DESCRIPTION_ID = +2424886;

    private PagerSlidingTabStrip tabStrip;

    private DetailActivity context;
    private int margin;
    private int radius;
    private int detail;
    private int top_margin;

    private int logo;
    private int small_margin;
    private int indicator;
    private int line;
    private int function;
    private int icon;
    private int tab;
    private int pager;

    public InitDetail(BaseActivity context) {
        super(context);
        this.context = (DetailActivity) context;

        this.margin = Util.toPx(18, context);
        this.radius = Util.toPx(5, context);

        this.detail = Util.toPx(135, context);
        this.logo = (int) (2f * detail / 3f);

        this.top_margin = Util.toPx(20, context);
        this.small_margin = Util.toPx(15, context);
        this.line = Util.toPx(1, context);
        this.function = Util.toPx(60, context);
        this.icon = Util.toPx(20, context);
        this.tab = Util.toPx(40, context);
        this.indicator = Util.toPx(1, context);
    }

    @Override
    protected View create() {
        CoordinatorLayout layout = new CoordinatorLayout(context);
        layout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        layout.addView(toolbar());
        layout.addView(recycler());
        return layout;
    }

    private View recycler() {
        LinearLayout layout = new LinearLayout(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(20);
        }
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-1, -2);
        params.topMargin = top_margin;
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(detail(layout));
        return layout;
    }

    private View tabs() {
        tabStrip = new PagerSlidingTabStrip(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabStrip.setElevation(2);
        }
        tabStrip.setLayoutParams(new LinearLayout.LayoutParams(-1, tab));
        tabStrip.setBackgroundColor(Color.WHITE);
        tabStrip.setDividerColor(Color.TRANSPARENT);
        tabStrip.setIndicatorColorResource(R.color.toolbar);
        tabStrip.setIndicatorHeight(indicator);
        tabStrip.setAllCaps(false);
        tabStrip.setUnderlineColor(Color.TRANSPARENT);
        tabStrip.setShouldExpand(true);
        return tabStrip;
    }

    private View pager() {
        ViewPager viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, pager));
        viewPager.setAdapter(new DetailAdaptor(context, dimen));
        setPaging(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPaging(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return viewPager;
    }

    private void setPaging(int position) {
        LinearLayout layout = ((LinearLayout) tabStrip.getChildAt(0));
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof TextView) {
                if (i == position) {
                    ((TextView) view).setTextColor(context.getResources().getColor(R.color.toolbar));
                } else {
                    ((TextView) view).setTextColor(Color.LTGRAY);
                }
            }
        }
    }

    private void setPaging(ViewPager viewPager) {
        tabStrip.setViewPager(viewPager);
        LinearLayout layout = ((LinearLayout) tabStrip.getChildAt(0));
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof TextView) {
                Util.setText((TextView) view, context);
                ((TextView) view).setTextColor(Color.LTGRAY);
                ((TextView) view).setTextSize(1, 13);
            }
        }
    }

    private View toolbar() {
        AppToolbar toolbar = new AppToolbar(context, true, true);
        toolbar.setId(TOOLBAR_ID);
        toolbar.setMaximize();
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-2, -2);
        params.gravity = Gravity.TOP;
        toolbar.setLayoutParams(params);
        return toolbar;
    }

    private View detail(final LinearLayout all) {
        final RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);

        params.setMargins(margin, 0, margin, 0);
        layout.setLayoutParams(params);

        CardView cardView = new CardView(context);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(-1, -2);
        params1.setMargins(margin, 0, margin, margin);
        cardView.setMinimumHeight(detail);
        params1.addRule(RelativeLayout.BELOW, LOGO_ID);
        params1.topMargin = (int) (-1f * logo / 2f);
        cardView.setLayoutParams(params1);
        cardView.setCardBackgroundColor(Color.WHITE);
        cardView.setCardElevation(5);
        cardView.setRadius(radius);

        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setLayoutParams(new CardView.LayoutParams(-1, -2));
        fill(box);

        cardView.addView(box);
        layout.addView(cardView);
        layout.addView(logo());
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                InitDetail.this.pager = (int) (dimen[1] - layout.getHeight() - top_margin - tab);

                all.addView(tabs());
                all.addView(pager());
            }
        });
        return layout;
    }

    private void fill(LinearLayout box) {
        box.addView(text(Attributes.DOCTOR_NAME));
        box.addView(text(DESCRIPTION_ID));
        box.addView(line(false));
        box.addView(functions());
    }

    private View functions() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, function));
        layout.addView(field(false));
        layout.addView(line(true));
        layout.addView(field(true));
        return layout;
    }

    private View field(final boolean isReserve) {
        final LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1, 1f));
        Util.setBackground(layout, context);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(icon, icon);
        params1.gravity = Gravity.CENTER_HORIZONTAL;

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(-2, -2);
        params2.gravity = Gravity.CENTER_HORIZONTAL;

        View view = new View(context);
        view.setLayoutParams(params1);
        if (!isReserve) {
            view.setBackgroundResource(R.mipmap.w_phone);
        } else {
            view.setBackgroundResource(R.mipmap.w_date);
        }

        AppText text = new AppText(context);
        text.setLayoutParams(params2);
        text.setSingleLine();
        text.setTextColor(context.getResources().getColor(R.color.toolbar));
        text.setTextSize(1, 11);
        if (isReserve) {
            text.setText("  تعیین  وقت  ");
        } else {
            text.setText("تماس با کلینیک");
        }

        layout.addView(space(2f));
        layout.addView(space(1f));
        layout.addView(view);
        layout.addView(space(1f));
        layout.addView(text);
        layout.addView(space(1f));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReserve) {
                    context.redirect();
                } else {
                    context.openDial(Attributes.PHONE_INFO);
                }
            }
        });
        return layout;
    }

    private View space(float i) {
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, i));
        return space;
    }

    private View line(boolean isVertical) {
        View view = new View(context);
        view.setBackgroundResource(R.color.base);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        if (isVertical) {
            params.height = function;
            params.width = line;
        } else {
            params.width = -1;
            params.height = line;
        }
        view.setLayoutParams(params);
        return view;
    }

    private View text(String input) {
        AppText appText = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = (int) (4f * logo / 7f);
        appText.setLayoutParams(params);
        appText.setGravity(Gravity.CENTER_HORIZONTAL);
        appText.setTextColor(Color.DKGRAY);
        appText.setTextSize(1, 15);
        appText.setSingleLine();
        appText.setShadowLayer(1, line, line, Color.LTGRAY);
        appText.setText(input);
        return appText;
    }

    private View text(final int id) {
        final AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        switch (id) {
            case DESCRIPTION_ID:
                text.setGravity(Gravity.CENTER);
                text.setTextColor(Color.GRAY);
                text.setTextSize(1, 11);
                text.setText(Attributes.DOC_DESCRIPTION);
                break;
            default:
                params.width = -2;
                params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                text.setGravity(Gravity.RIGHT);
                text.setTextColor(Color.DKGRAY);
                text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                text.setSingleLine();
                Util.setBackground(text, context);
                text.setTextSize(1, 14);
                break;
        }
        text.setLayoutParams(params);

        if (id != DESCRIPTION_ID) {
            text.setPadding(0, small_margin / 2, 0, small_margin / 2);
        } else {
            params.bottomMargin = small_margin;
            params.leftMargin = small_margin;
            params.rightMargin = small_margin;
        }
        return text;
    }

    private View logo() {
        CircleImageView imageView = new CircleImageView(context);
        imageView.setId(LOGO_ID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(25);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(logo, logo);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView.setLayoutParams(params);
        imageView.setBorderWidth(line);
        imageView.setBorderColor(context.getResources().getColor(R.color.circle_border));
        imageView.setImageResource(R.mipmap.y_def_doctor_profile);
        return imageView;
    }
}
