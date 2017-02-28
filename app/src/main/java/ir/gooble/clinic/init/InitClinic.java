package ir.gooble.clinic.init;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.ClinicActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppToolbar;

public class InitClinic extends BaseInit {
    private static final int TOOLBAR_ID = +8248484;
    private static final int CONTACT = +4874747;
    private static final int ADDRESS = +4874748;
    private static final int DETAIL = +4874749;

    private ClinicActivity context;
    private int appBar;
    private int margin;
    private int radius;

    private int contact;
    private int detail;
    private int address;

    private int top_margin;
    private int space_margin;

    public InitClinic(BaseActivity context) {
        super(context);
        this.context = (ClinicActivity) context;
        this.appBar = Util.toPx(250, context);
        this.margin = Util.toPx(25, context);
        this.radius = Util.toPx(5, context);

        this.detail = Util.toPx(170, context);
        this.address = Util.toPx(120, context);
        this.contact = detail;

        this.top_margin = (int) (1f * appBar / 4f);
        this.space_margin = (int) (2f * appBar / 4f);
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
        NestedScrollView scrollView = new NestedScrollView(context);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-1, -2);
        params.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        params.setAnchorId(TOOLBAR_ID);
        params.anchorGravity = Gravity.BOTTOM;
        params.topMargin = -top_margin;
        scrollView.setLayoutParams(params);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setSmoothScrollingEnabled(false);
        scrollView.setClipToPadding(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scrollView.setElevation(20);
        }

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(item(DETAIL));
        layout.addView(item(CONTACT));
        layout.addView(item(ADDRESS));

        scrollView.addView(layout);
        return scrollView;
    }

    private View toolbar() {
        AppBarLayout appBarLayout = new AppBarLayout(context);
        appBarLayout.setId(TOOLBAR_ID);
        appBarLayout.setOrientation(LinearLayout.VERTICAL);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-1, appBar);
        appBarLayout.setLayoutParams(params);

        CollapsingToolbarLayout collapsingToolbarLayout = new CollapsingToolbarLayout(context);
        AppBarLayout.LayoutParams params1 = new AppBarLayout.LayoutParams(-1, -1);
        params1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        collapsingToolbarLayout.setLayoutParams(params1);
        collapsingToolbarLayout.setStatusBarScrimResource(R.color.toolbar);
        collapsingToolbarLayout.setContentScrimResource(R.color.toolbar);
        collapsingToolbarLayout.setBackgroundResource(R.color.toolbar);

        ImageView imageView = new ImageView(context);
        CollapsingToolbarLayout.LayoutParams params2 = new CollapsingToolbarLayout.LayoutParams(-1, -1);
        params2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
        imageView.setLayoutParams(params2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.image_clinic_default);

        AppToolbar toolbar = new AppToolbar(context, true, null, true);
        toolbar.setMaximize();
        toolbar.setBackgroundResource(R.color.transparent);
        CollapsingToolbarLayout.LayoutParams params3 = new CollapsingToolbarLayout.LayoutParams(-1, toolbar.getSize() + space_margin);
        params3.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        toolbar.setLayoutParams(params3);

        collapsingToolbarLayout.addView(imageView);
        collapsingToolbarLayout.addView(toolbar);

        appBarLayout.addView(collapsingToolbarLayout);
        return appBarLayout;
    }

    private View item(int id) {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        switch (id) {
            case DETAIL:
                params.height = detail;
                break;
            case ADDRESS:
                params.height = address;
                break;
            case CONTACT:
                params.height = contact;
                break;
        }
        params.setMargins(margin, 0, margin, 0);
        layout.setLayoutParams(params);

        CardView cardView = new CardView(context);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(-1, -1);
        params1.setMargins(margin, 0, margin, margin / 2);
        cardView.setLayoutParams(params1);
        cardView.setCardBackgroundColor(Color.WHITE);
        cardView.setCardElevation(5);
        cardView.setRadius(radius);

        layout.addView(cardView);
        return layout;
    }
}
