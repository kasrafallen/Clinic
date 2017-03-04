package ir.gooble.clinic.application;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.DescriptionActivity;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitDescription extends BaseInit {

    private static final int TOOLBAR_ID = +54863332;
    private DescriptionActivity context;
    private int appBar;
    private int margin;

    private final static String SPACE = "   \n";

    public InitDescription(BaseActivity context) {
        super(context);
        this.context = (DescriptionActivity) context;
        this.appBar = Util.toPx(210, context);
        this.margin = Util.toPx(12, context);
    }

    @Override
    protected View create() {
        CoordinatorLayout layout = new CoordinatorLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        layout.addView(toolbar());
        layout.addView(recycler());
        layout.addView(fab());
        return layout;
    }

    private View fab() {
        FloatingActionButton fab = new FloatingActionButton(context);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-2, -2);
        params.setAnchorId(TOOLBAR_ID);
        params.anchorGravity = Gravity.LEFT | Gravity.BOTTOM;
        params.leftMargin = margin;
        params.rightMargin = margin;
        fab.setLayoutParams(params);
        fab.setImageResource(R.drawable.ic_share_white_48dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.share(context.model);
            }
        });
        return fab;
    }

    private View recycler() {
        NestedScrollView scrollView = new NestedScrollView(context);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-1, -2);
        params.setAnchorId(TOOLBAR_ID);
        params.anchorGravity = Gravity.BOTTOM;
        params.setBehavior(new AppBarLayout.ScrollingViewBehavior());
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
        layout.setMinimumHeight((int) dimen[1]);
        layout.addView(text());

        scrollView.addView(layout);
        return scrollView;
    }

    private View text() {
        AppText text = new AppText(context);
        text.setSingleLine(false);
        text.setGravity(Gravity.RIGHT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = margin * 3;
        params.bottomMargin = margin * 3;
        params.leftMargin = margin;
        params.rightMargin = margin;
        text.setLayoutParams(params);
        text.setTextSize(1, 12);
        text.setTextColor(Color.DKGRAY);

        String data = context.model.getTitle()
                + SPACE + context.model.getDate()
                + SPACE + "\n" + context.model.getDescription();
        SpannableString span = new SpannableString(data);

        span.setSpan(new ForegroundColorSpan(Color.BLACK), 0, data.indexOf(SPACE), 0);
        span.setSpan(new AbsoluteSizeSpan(15, true), 0, data.indexOf(SPACE), 0);

        span.setSpan(new ForegroundColorSpan(Color.GRAY)
                , data.indexOf(SPACE), data.indexOf(SPACE, data.indexOf(SPACE) + 1), 0);
        span.setSpan(new AbsoluteSizeSpan(10, true)
                , data.indexOf(SPACE), data.indexOf(SPACE, data.indexOf(SPACE) + 1), 0);

        text.setText(span);
        return text;
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
        imageView.setImageResource(context.model.getResource());

        AppToolbar toolbar = new AppToolbar(context, true, null, true);
        toolbar.setBackgroundResource(R.color.transparent);
        CollapsingToolbarLayout.LayoutParams params3 = new CollapsingToolbarLayout.LayoutParams(-1, -1);
        params3.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        toolbar.setLayoutParams(params3);

        collapsingToolbarLayout.addView(imageView);
        collapsingToolbarLayout.addView(toolbar);

        appBarLayout.addView(collapsingToolbarLayout);
        return appBarLayout;
    }
}
