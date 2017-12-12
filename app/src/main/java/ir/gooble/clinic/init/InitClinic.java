package ir.gooble.clinic.init;

import android.graphics.Color;
import android.graphics.Paint;
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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.ClinicActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.instance.ClinicInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.model.Address;
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

    private static final int NAME = +54187478;
    private static final int DESCRIPTION = +6433188;

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

    private HashMap<Integer, TextView> textViewHashMap = new HashMap<>();
    private ArrayList<View> cardViewHashMap = new ArrayList<>();

    private CircleImageView circleImageView;
    private LinearLayout addressBox;
    private LinearLayout socialBox;
    private ImageView imageView;
    private int icon;

    public InitClinic(BaseActivity context) {
        super(context);
        this.context = (ClinicActivity) context;

        this.appBar = Util.toPx(250, context);
        this.margin = Util.toPx(18, context);
        this.radius = Util.toPx(5, context);

        this.detail = Util.toPx(135, context);
        this.address = Util.toPx(100, context);
        this.contact = Util.toPx(100, context);

        this.top_margin = (int) (2f * appBar / 4f);
        this.space_margin = (int) (2f * appBar / 4f);
        this.logo = (int) (1f * detail / 2f);
        this.small_margin = Util.toPx(15, context);
        this.line = Util.toPx(1, context);

        this.icon = Util.toPx(20, context);
    }

    @Override
    protected View create() {
        final CoordinatorLayout layout = new CoordinatorLayout(context);
        layout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        layout.addView(toolbar());
        layout.addView(recycler());
        ClinicInstance.getClinic(context, new InstanceResult() {
            @Override
            public void onResult(Object[] objects) {
                InitClinic.this.clinic = (Clinic) objects[0];
                if (clinic != null) {
                    setData();
                }
            }
        });
        return layout;
    }

    private void setData() {
        imageView.setVisibility(View.VISIBLE);
        circleImageView.setVisibility(View.VISIBLE);
        if (clinic.getPictureURL() != null) {
            Picasso.with(context).load(clinic.getPictureURL()).fit().centerCrop().into(imageView);
        }
        if (clinic.getLogoURL() != null) {
            Picasso.with(context).load(clinic.getLogoURL()).fit().centerCrop().into(circleImageView);
        } else {
            circleImageView.setImageResource(R.mipmap.test_clinic_logo);
        }
        if (clinic.getAddresses() != null && clinic.getAddresses().length > 0) {
            int counter = 0;
            for (Address account : clinic.getAddresses()) {
                addressBox.addView(contact(account));
                counter++;
                if (counter != clinic.getAddresses().length) {
                    addressBox.addView(line());
                }
            }
        }
        if (clinic.getSocialAccounts() != null && clinic.getSocialAccounts().length > 0) {
            for (SocialAccount account : clinic.getSocialAccounts()) {
                socialBox.addView(contact(account));
            }
        }
        if (clinic.getPhoneNumbers() != null && clinic.getPhoneNumbers().length > 0) {
            for (PhoneNumber account : clinic.getPhoneNumbers()) {
                socialBox.addView(contact(account));
            }
        }
        for (int id : textViewHashMap.keySet()) {
            switch (id) {
                case NAME:
                    textViewHashMap.get(id).setText(clinic.getTitle());
                    break;
                default:
                    textViewHashMap.get(id).setText(clinic.getDescription());
                    break;
            }
        }
        for (View view : cardViewHashMap) {
            view.setVisibility(View.VISIBLE);
        }
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

        imageView = new ImageView(context);
        CollapsingToolbarLayout.LayoutParams params2 = new CollapsingToolbarLayout.LayoutParams(-1, -1);
        params2.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
        imageView.setLayoutParams(params2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.color.toolbar);
        imageView.setVisibility(View.GONE);

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

        cardView.setVisibility(View.GONE);
        cardViewHashMap.add(cardView);
        return layout;
    }

    private void fill(LinearLayout box, int id) {
        if (id == DETAIL) {
            box.addView(text("NAME"));
            box.addView(text(DESCRIPTION_ID));
        } else if (id == ADDRESS) {
            box.addView(text("نشانی کلینیک"));
            addressBox = box;
        } else {
            socialBox = box;
        }
    }

    private View line() {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, line));
        view.setBackgroundColor(Color.LTGRAY);
        return view;
    }

    private View gpsAddress() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        layout.setLayoutParams(params);
        layout.addView(gps());
        layout.addView(text());
        layout.setPadding(margin, 0, margin, 0);
        return layout;
    }

    private View text() {
        AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2, 1f);
        params.gravity = Gravity.CENTER_VERTICAL;
        text.setLayoutParams(params);
        text.setSingleLine();
        text.setGravity(Gravity.RIGHT);
        text.setTextColor(Color.GRAY);
        text.setTextSize(1, 11);
        text.setText("آدرس بر روی نقشه");
        return text;
    }

    private View gps() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(icon, icon);
        params.setMargins(0, margin / 4, margin, margin / 2);
        params.gravity = Gravity.CENTER_VERTICAL;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.ic_location_on_black_48px);
        return view;
    }

    private View contact(final SocialAccount socialAccount) {
        return contact(socialAccount, null, null);
    }

    private View contact(final PhoneNumber phoneNumber) {
        return contact(null, phoneNumber, null);
    }

    private View contact(final Address address) {
        return contact(null, null, address);
    }

    private View contact(final SocialAccount socialAccount, final PhoneNumber phoneNumber, final Address address) {
        LinearLayout layout = new LinearLayout(context);
        if (address != null) {
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(text(address));
            layout.addView(gpsAddress());
        } else {
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(logo(socialAccount));
            if (phoneNumber != null) {
                layout.addView(text(phoneNumber));
            } else {
                layout.addView(text(socialAccount));
            }
        }
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        Util.setBackground(layout, context);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address != null) {
                    context.openMap(address);
                } else if (phoneNumber != null) {
                    context.openDial(phoneNumber.getPhoneNumber());
                } else {
                    context.openLink(socialAccount.getSocialLink());
                }
            }
        });
        return layout;
    }

    private View logo(SocialAccount socialAccount) {
        CircleImageView logo = new CircleImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(icon, icon);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.setMargins(small_margin, 0, small_margin, 0);
        logo.setLayoutParams(params);
        logo.setImageResource(R.color.white);
        if (socialAccount != null && socialAccount.getSocialLogo() != null) {
            Picasso.with(context).load(socialAccount.getSocialLogo()).fit().centerCrop().into(logo);
        } else {
            logo.setImageResource(R.mipmap.x_phone_icon);
        }
        return logo;
    }

    private View text(String input) {
        AppText appText = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = small_margin;
        if (input.equals("NAME")) {
            textViewHashMap.put(NAME, appText);
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
        return appText;
    }

    private View text(final SocialAccount account) {
        return text(0, account, null, null);
    }

    private View text(final Address address) {
        return text(0, null, null, address);
    }

    private View text(final PhoneNumber phoneNumber) {
        return text(0, null, phoneNumber, null);
    }

    private View text(final int id) {
        return text(id, null, null, null);
    }

    private View text(final int id, final SocialAccount account, final PhoneNumber phoneNumber, Address address) {
        final AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        switch (id) {
            case ADDRESS_ID:
            case DESCRIPTION_ID:
                text.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                text.setTextColor(Color.GRAY);
                text.setTextSize(1, 11);
                textViewHashMap.put(DESCRIPTION, text);
                break;
            default:
                params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                text.setGravity(Gravity.RIGHT);
                text.setSingleLine();
                text.setTextColor(Color.DKGRAY);
                if (address != null) {
                    params.width = -1;
                    text.setTextSize(1, 12);
                    text.setText(address.getAddress());
                } else {
                    text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    params.width = -2;
                    text.setTextSize(1, 13);
                    if (account != null) {
                        text.setText(account.getSoccialName());
                    } else {
                        text.setText(phoneNumber.getPhoneNumber());
                    }
                }
                break;
        }
        text.setLayoutParams(params);
        if (id != ADDRESS_ID && id != DESCRIPTION_ID) {
            text.setPadding(small_margin, small_margin / 2, small_margin, 0);
        } else {
            params.bottomMargin = small_margin;
            params.leftMargin = small_margin;
            params.rightMargin = small_margin;
        }
        return text;
    }

    private View logo() {
        circleImageView = new CircleImageView(context);
        circleImageView.setId(LOGO_ID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circleImageView.setElevation(25);
        }
        circleImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(logo, logo);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        circleImageView.setLayoutParams(params);
        circleImageView.setBorderWidth(line);
        circleImageView.setBorderColor(context.getResources().getColor(R.color.circle_border));
        circleImageView.setVisibility(View.GONE);
        return circleImageView;
    }
}
