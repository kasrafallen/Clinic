package ir.gooble.clinic.init;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.ClinicActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.instance.ClinicInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.model.Clinic;
import ir.gooble.clinic.model.PhoneNumber;
import ir.gooble.clinic.model.SocialAccount;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitClinic extends BaseInit {
    private static final int TOOLBAR_ID = +8248484;
    private static final int CONTACT = +4874747;
    private static final int ADDRESS = +4874748;
    private static final int DETAIL = +4874749;

    private static final int LOGO_ID = +2847774;

    private static final int DESCRIPTION_ID = +2424886;
    private static final int ADDRESS_ID = +215487856;

    private ClinicActivity context;
    public Clinic clinic;
    private int appBar;
    private int margin;
    private int radius;

    private int contact;
    private int detail;
    private int address;

    private int top_margin;
    private int space_margin;

    private int logo;
    private int small_margin;
    private int line;

    private int icon;

    public InitClinic(BaseActivity context) {
        super(context);
        this.context = (ClinicActivity) context;

        this.appBar = Util.toPx(250, context);
        this.margin = Util.toPx(18, context);
        this.radius = Util.toPx(5, context);

        this.detail = Util.toPx(135, context);
        this.address = Util.toPx(120, context);
        this.contact = Util.toPx(130, context);

        this.top_margin = (int) (2f * appBar / 4f);
        this.space_margin = (int) (2f * appBar / 4f);
        this.logo = (int) (1f * detail / 2f);
        this.small_margin = Util.toPx(15, context);
        this.line = Util.toPx(1, context);

        this.icon = Util.toPx(20, context);
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

        final LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        ClinicInstance.getClinic(context, new InstanceResult() {
            @Override
            public void onResult(Object[] objects) {
                InitClinic.this.clinic = (Clinic) objects[0];
                layout.addView(item(DETAIL));
                layout.addView(item(CONTACT));
                layout.addView(item(ADDRESS));
            }
        });

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
        imageView.setImageResource(R.mipmap.test_clinic_banner);

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

        params.setMargins(margin, 0, margin, 0);
        layout.setLayoutParams(params);

        CardView cardView = new CardView(context);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(-1, -2);
        params1.setMargins(margin, 0, margin, margin / 2);
        switch (id) {
            case DETAIL:
                cardView.setMinimumHeight(detail);
                break;
            case ADDRESS:
                cardView.setMinimumHeight(address);
                break;
            case CONTACT:
                cardView.setMinimumHeight(contact);
                break;
        }
        if (id == DETAIL) {
            params1.addRule(RelativeLayout.BELOW, LOGO_ID);
            params1.topMargin = (int) (-1f * logo / 2f);
        }
        cardView.setLayoutParams(params1);
        cardView.setCardBackgroundColor(Color.WHITE);
        cardView.setCardElevation(5);
        cardView.setRadius(radius);

        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setLayoutParams(new CardView.LayoutParams(-1, -2));
        fill(box, id);

        cardView.addView(box);
        layout.addView(cardView);
        if (id == DETAIL) {
            layout.addView(logo());
        }
        return layout;
    }

    private void fill(LinearLayout box, int id) {
        if (id == DETAIL) {
            box.addView(text(get(NAME)));
            box.addView(text(DESCRIPTION_ID, null, null));
        } else if (id == ADDRESS) {
            box.addView(text("نشانی کلینیک"));
            box.addView(text(ADDRESS_ID, null, null));
            box.addView(gpsAddress());
        } else {
            if (clinic.getSocialAccounts() != null && clinic.getSocialAccounts().length > 0) {
                for (SocialAccount account : clinic.getSocialAccounts()) {
                    box.addView(contact(account, null));
                }
            }
            if (clinic.getPhoneNumbers() != null && clinic.getPhoneNumbers().length > 0) {
                for (PhoneNumber account : clinic.getPhoneNumbers()) {
                    box.addView(contact(null, account));
                }
            }
        }
    }

    private View gpsAddress() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        layout.setLayoutParams(params);
        layout.addView(gps());
        layout.addView(text());
        Util.setBackground(layout, context);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToMap();
            }
        });
        layout.setPadding(margin, 0, margin, 0);
        return layout;
    }

    private void redirectToMap() {
        try {
            double lat = 35.7395263;
            double lng = 51.3774143;
            float zoom = 16f;
            String label = get(NAME);
            String uriBegin = "geo:" + lat + "," + lng;
            String query = lat + "," + lng + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=" + zoom;
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View text() {
        AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2, 1f);
        params.gravity = Gravity.CENTER_VERTICAL;
        text.setLayoutParams(params);
        text.setSingleLine();
        text.setGravity(Gravity.RIGHT);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        text.setText("آدرس بر روی نقشه");
        return text;
    }

    private View gps() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(icon, icon);
        params.setMargins(0, margin, margin, margin);
        params.gravity = Gravity.CENTER_VERTICAL;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.ic_location_on_black_48px);
        return view;
    }

    private View contact(SocialAccount socialAccount, PhoneNumber phoneNumber) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));

        layout.addView(logo(socialAccount));
        layout.addView(text(0, socialAccount, phoneNumber));
        return layout;
    }

    private View logo(SocialAccount socialAccount) {
        CircleImageView logo = new CircleImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(icon, icon);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.setMargins(small_margin, 0, small_margin, 0);
        logo.setLayoutParams(params);
        logo.setImageResource(R.color.white);
        Picasso.with(context).load(socialAccount.getSocialLogo()).fit().centerCrop().into(logo);
        return logo;
    }

    private View text(String input) {
        AppText appText = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = small_margin;
        if (input.equals(get(NAME))) {
            params.topMargin = (int) (2f * logo / 3f);
        } else {
            params.topMargin = small_margin;
        }
        appText.setLayoutParams(params);
        appText.setGravity(Gravity.CENTER_HORIZONTAL);
        appText.setTextColor(Color.DKGRAY);
        appText.setTextSize(1, 14);
        appText.setSingleLine();
        appText.setShadowLayer(1, line, line, Color.LTGRAY);
        appText.setText(input);
        return appText;
    }

    private View text(final int id, final SocialAccount account, final PhoneNumber phoneNumber) {
        final AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        switch (id) {
            case ADDRESS_ID:
            case DESCRIPTION_ID:
                text.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                text.setTextColor(Color.GRAY);
                text.setTextSize(1, 11);
                if (id == DESCRIPTION_ID) {
                    text.setText(get(DESCRIPTION));
                } else {
                    text.setCompoundDrawables(null, null, getDrawable(), null);
                    text.setText(get(ADDRESS_BOOK));
                }
                break;
            default:
                params.width = -2;
                params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                text.setGravity(Gravity.RIGHT);
                text.setTextColor(Color.DKGRAY);
                text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                text.setSingleLine();
                Util.setBackground(text, context);
                text.setTextSize(1, 12);
                text.setTypeface(text.getTypeface(), Typeface.BOLD);
                if (account != null) {
                    text.setText(account.getSoccialName());
                } else {
                    text.setText(phoneNumber.getPhoneNumber());
                }
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (phoneNumber != null) {
                            context.openDial(phoneNumber.getPhoneNumber());
                        } else {
                            context.openLink(account.getSocialLink());
                        }
                    }
                });
                break;
        }
        text.setLayoutParams(params);

        if (id != ADDRESS_ID && id != DESCRIPTION_ID) {
            text.setPadding(0, small_margin / 2, 0, small_margin / 2);
        } else {
            params.bottomMargin = small_margin;
            params.leftMargin = small_margin;
            params.rightMargin = small_margin;
        }
        return text;
    }

    private Drawable getDrawable() {
        return context.getResources().getDrawable(R.drawable.ic_location_on_black_48px);
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
        imageView.setImageResource(R.mipmap.test_clinic_logo);
        return imageView;
    }

    private static final int NAME = +54187478;
    private static final int DESCRIPTION = +6433188;
    private static final int ADDRESS_BOOK = +54187479;

    private String get(int id) {
        switch (id) {
            case NAME:
                return clinic.getTitle();
            case ADDRESS_BOOK:
                return clinic.getAddressesString();
            default:
                return clinic.getDescription();
        }
    }
}
