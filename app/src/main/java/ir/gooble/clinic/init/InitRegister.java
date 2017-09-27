package ir.gooble.clinic.init;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.RegisterActivity;
import ir.gooble.clinic.adaptor.SpinAdaptor;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.util.DialogUtil;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;
import ir.gooble.clinic.view.AppToolbar;

public class InitRegister extends BaseInit {
    private static final int IMAGE = +54822525;

    private static final int NAME_ID = +5555488;
    private static final int FATHER_ID = +5555333;
    private static final int INSURANCE_ID = +5555222;
    private static final int BIRTHDAY_ID = +5555111;
    private static final int CARD_ID = +458969;
    private static final int ADDRESS_ID = +458966;
    private static final int PHONE_ID = +458965;

    private static final int FEMALE_ID = +12458;
    private static final int MALE_ID = +12459;

    private static final int TOOLBAR = +481487;
    private static final int FUNCTION = +51482;

    private RegisterActivity context;

    private int toolbar;
    private int profile;
    private int add;
    private int small_margin;
    private int function_;
    private int button;
    private int field;
    private int margin;
    private int field_with;
    private int essential;

    private LinearLayout function;
    private LinearLayout box;

    public InitRegister(BaseActivity context) {
        super(context);
        int toolbar_size = Util.getToolbarSize(context);

        this.context = (RegisterActivity) context;
        this.toolbar = toolbar_size * 3;
        this.profile = toolbar_size * 2;
        this.add = toolbar_size / 2;
        this.small_margin = Util.toPx(5, context);
        this.function_ = Util.toPx(70, context);
        this.button = Util.toPx(40, context);
        this.field = Util.toPx(60, context);
        this.margin = Util.toPx(15, context);
        this.field_with = (int) ((dimen[0] - (2 * margin)) / 2);
        this.essential = Util.toPx(12, context);
    }

    @Override
    protected View create() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        layout.addView(toolbar());
        layout.addView(function());
        layout.addView(recycler());
        return layout;
    }

    private View recycler() {
        final RelativeLayout layout = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
        params.addRule(RelativeLayout.BELOW, TOOLBAR);
        params.addRule(RelativeLayout.ABOVE, FUNCTION);
        layout.setLayoutParams(params);

        ScrollView scrollView = new ScrollView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        scrollView.setLayoutParams(layoutParams);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);

        box = new LinearLayout(context);
        box.setOrientation(LinearLayout.VERTICAL);

        scrollView.addView(box);
        layout.addView(scrollView);
        return layout;
    }

    private View function() {
        function = new LinearLayout(context);
        function.setId(FUNCTION);
        function.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, function_);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        function.setLayoutParams(params);
        function.addView(space(1f));
        function.addView(button(true));
        function.addView(space(2f));
        function.addView(button(false));
        function.addView(space(1f));
        function.setVisibility(View.INVISIBLE);
        return function;
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
        layout.setId(TOOLBAR);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(-1, toolbar));

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
        Util.setBackground(imageView, context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.requestImage();
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

    private void setImage(CircleImageView imageView) {
        imageView.setImageResource(R.mipmap.y_def_user_profile);
        if (context.user != null && context.user.getImagePath() != null && new File(context.user.getImagePath()).exists()) {
            Picasso.with(context).load(new File(context.user.getImagePath()))
                    .fit().centerCrop().error(R.mipmap.y_def_user_profile).into(imageView);
        }
    }

    private RelativeLayout getField(int gravity) {
        RelativeLayout layout = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(field_with, -2);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        if (gravity == RelativeLayout.CENTER_HORIZONTAL) {
            params.width = -1;
        } else {
            params.addRule(gravity);
        }
        layout.setLayoutParams(params);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setMinimumHeight(field);
        return layout;
    }

    private View field(int type) {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.rightMargin = margin;
        params.leftMargin = margin;
        layout.setLayoutParams(params);
        switch (type) {
            case 0:
                layout.addView(sex(RelativeLayout.ALIGN_PARENT_LEFT));
                layout.addView(edit(NAME_ID, RelativeLayout.ALIGN_PARENT_RIGHT));
                break;
            case 1:
                layout.addView(edit(FATHER_ID, RelativeLayout.ALIGN_PARENT_RIGHT));
                layout.addView(edit(BIRTHDAY_ID, RelativeLayout.ALIGN_PARENT_LEFT));
                break;
            case 2:
                layout.addView(edit(PHONE_ID, RelativeLayout.ALIGN_PARENT_LEFT));
                layout.addView(edit(CARD_ID, RelativeLayout.ALIGN_PARENT_RIGHT));
                break;
            case 3:
                layout.addView(edit(ADDRESS_ID, RelativeLayout.CENTER_HORIZONTAL));
                break;
            case 4:
                layout.addView(edit(INSURANCE_ID, RelativeLayout.ALIGN_PARENT_LEFT));
                layout.addView(spinner(RelativeLayout.ALIGN_PARENT_RIGHT));
                break;
        }
        return layout;
    }

    private View spinner(int gravity) {
        RelativeLayout layout = getField(gravity);

        Spinner spinner = new Spinner(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        spinner.setLayoutParams(params);
        spinner.setGravity(Gravity.RIGHT);
        spinner.setAdapter(new SpinAdaptor(context, Attributes.SUPPORTED_INSURANCES));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                context.user.setInsurance_type(Attributes.SUPPORTED_INSURANCES[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (context.user.getInsurance_type() != null) {
            int counter = 0;
            for (String type : Attributes.SUPPORTED_INSURANCES) {
                if (type.equals(context.user.getInsurance_type())) {
                    spinner.setSelection(counter);
                }
                counter++;
            }
        }

        layout.addView(spinner);
        setPadding(layout, gravity);
        return layout;
    }

    private void setPadding(RelativeLayout layout, int gravity) {
        switch (gravity) {
            case RelativeLayout.ALIGN_PARENT_LEFT:
                layout.setPadding(0, 0, margin / 2, 0);
                break;
            case RelativeLayout.ALIGN_PARENT_RIGHT:
                layout.setPadding(margin / 2, 0, 0, 0);
                break;
        }
    }

    private View edit(int id, int gravity) {
        RelativeLayout layout = getField(gravity);

        TextInputLayout inputLayout = new TextInputLayout(context);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(-1, -2);
        params1.addRule(RelativeLayout.CENTER_VERTICAL);
        inputLayout.setLayoutParams(params1);
        Util.setText(inputLayout, context);

        final AppCompatEditText editText = new AppCompatEditText(context);
        editText.setId(id);
        editText.setSingleLine();
        editText.setTextColor(context.getResources().getColor(R.color.toolbar));
        editText.setHintTextColor(Color.GRAY);
        TextInputLayout.LayoutParams params2 = new AppBarLayout.LayoutParams(-1, -2);
        params2.gravity = Gravity.CENTER_VERTICAL;
        editText.setLayoutParams(params2);
        editText.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        Util.setText(editText, context);

        String hint = null;
        switch (id) {
            case NAME_ID:
                hint = ("نام و نام خانوادگی");
                editText.setText(context.user.getName());
                break;
            case FATHER_ID:
                hint = ("نام پدر");
                editText.setText(context.user.getFather_name());
                break;
            case CARD_ID:
            case PHONE_ID:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                if (gravity == RelativeLayout.ALIGN_PARENT_RIGHT) {
                    hint = ("شماره ملی");
                    editText.setText(context.user.getNational_number());
                } else {
                    hint = ("تلفن همراه");
                    editText.setText(context.user.getMobile_number());
                }
                break;
            case ADDRESS_ID:
                editText.setSingleLine(false);
                editText.setMaxLines(4);
                hint = ("آدرس محل سکونت");
                editText.setText(context.user.getStreet());
                break;
            case INSURANCE_ID:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                hint = ("شماره بیمه");
                editText.setText(context.user.getInsurance_number());
                break;
            case BIRTHDAY_ID:
                editText.setFocusableInTouchMode(false);
                editText.setFocusable(false);
                editText.setLongClickable(false);
                hint = ("تاریخ تولد");
                editText.setText(context.user.getBirthday());
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.pickDate(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String format = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                                editText.setText(format);
                                context.user.setBirthday(format);
                            }
                        });
                    }
                });
                break;
        }
        editText.setHint(hint);
        setInspector(editText, id);

        inputLayout.addView(editText);
        layout.addView(inputLayout);
        if (id != FATHER_ID && id != BIRTHDAY_ID && id != INSURANCE_ID) {
            layout.addView(essential(false));
        } else if (id == BIRTHDAY_ID) {
            layout.addView(essential(true));
        }
        setPadding(layout, gravity);
        return layout;
    }

    private void setInspector(final AppCompatEditText editText, final int mode) {
        editText.setTextSize(1, 12);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getInputType() == InputType.TYPE_CLASS_NUMBER) {
                    if (editable.length() == 0) {
                        editText.setTextSize(1, 12);
                    } else {
                        editText.setTextSize(1, 14);
                    }
                }
                check(editable.toString(), mode);
            }
        });
    }

    private void check(String data, int mode) {
        if (data != null) {
            data.replaceAll("\\s", "");
        }
        if (data != null && data.length() == 0) {
            data = null;
        }
        switch (mode) {
            case NAME_ID:
                context.user.setName(data);
                break;
            case FATHER_ID:
                context.user.setFather_name(data);
                break;
            case CARD_ID:
                context.user.setNational_number(data);
                break;
            case PHONE_ID:
                context.user.setMobile_number(data);
                break;
            case ADDRESS_ID:
                context.user.setStreet(data);
                break;
            case INSURANCE_ID:
                context.user.setInsurance_number(data);
                break;
        }
    }

    private View essential(boolean isDate) {
        View view = new View(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(10);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(essential, essential);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        view.setLayoutParams(params);
        if (isDate) {
            view.setBackgroundResource(R.mipmap.w_date);
        } else {
            view.setBackgroundResource(R.drawable.essential);
            view.setScaleX(.5f);
            view.setScaleY(.5f);
        }
        return view;
    }

    private View sex(int gravity) {
        RelativeLayout layout = getField(gravity);

        AppText hint = new AppText(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.rightMargin = small_margin;
        hint.setLayoutParams(params);
        hint.setSingleLine();
        hint.setTextColor(Color.GRAY);
        hint.setTextSize(1, 11);
        hint.setText("جنسیت");

        RadioGroup group = new RadioGroup(context);
        group.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(-2, -2);
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        group.setLayoutParams(params1);

        group.addView(groupButton(true));
        group.addView(groupButton(false));

        layout.addView(group);
        layout.addView(hint);
        setPadding(layout, gravity);
        return layout;
    }

    private View groupButton(final boolean isWoman) {
        RadioButton button = new RadioButton(context);
        button.setSingleLine();
        button.setTextSize(1, 11);
        button.setTextColor(context.getResources().getColor(R.color.toolbar));
        Util.setText(button, context);
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(-2, -2);
        if (!isWoman) {
            params.leftMargin = small_margin;
            button.setId(MALE_ID);
            button.setText("مرد");
        } else {
            params.rightMargin = small_margin;
            button.setId(FEMALE_ID);
            button.setText("زن");
        }
        if (context.user.isMen() && !isWoman) {
            button.setChecked(true);
        } else if (context.user.isWomen() && isWoman) {
            button.setChecked(true);
        } else {
            button.setChecked(false);
        }
        button.setLayoutParams(params);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isWoman) {
                    context.user.setWomen(isChecked);
                } else {
                    context.user.setMen(isChecked);
                }
            }
        });
        return button;
    }

    public void fetch() {
        if (context.user == null) {
            return;
        }
        box.addView(field(0));
        box.addView(field(1));
        box.addView(field(2));
        box.addView(field(3));
        box.addView(field(4));

        updateImage();
        function.setVisibility(View.VISIBLE);
    }

    public void updateImage() {
        CircleImageView imageView = (CircleImageView) context.findViewById(IMAGE);
        if (imageView != null) {
            setImage(imageView);
        }
    }
}
