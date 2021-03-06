package ir.gooble.clinic.init;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;

import java.util.HashMap;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.SignActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class InitSign extends BaseInit {

    private static final int TV = +64519;
    private SignActivity context;
    private int margin;

    private HashMap<Integer, TextInputLayout> map = new HashMap<>();
    private AppText text;

    public enum Mode {
        NUMBER, VERIFY, NAME
    }

    public InitSign(BaseActivity context) {
        super(context);
        this.context = (SignActivity) context;
        this.margin = Util.toPx(20, context);
    }

    @Override
    protected View create() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        layout.addView(message());
        layout.addView(inputLayout());
        layout.addView(function());
        return layout;
    }

    private View inputLayout() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.addView(input(1));
        layout.addView(input(0));
        return layout;
    }

    private View function() {
        LinearLayout function = new LinearLayout(context);
        function.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, Util.toPx(70, context));
        function.setLayoutParams(params);
        function.addView(space(1f));
        function.addView(button(true));
        function.addView(space(2f));
        function.addView(button(false));
        function.addView(space(1f));
        return function;
    }

    private View button(final boolean isExit) {
        Button buttonItem = new Button(context);
        buttonItem.setTag(String.valueOf(isExit));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonItem.setElevation(25);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, Util.toPx(40, context));
        params.gravity = Gravity.CENTER_VERTICAL;
        buttonItem.setLayoutParams(params);
        buttonItem.setSingleLine();
        buttonItem.setTextSize(1, 15);
        buttonItem.setGravity(Gravity.CENTER);
        Util.setText(buttonItem, context);
        if (isExit) {
            buttonItem.setTextColor(Color.DKGRAY);
            buttonItem.setBackgroundResource(R.drawable.button_white_background);
            buttonItem.setText("بازگشت");
        } else {
            buttonItem.setTextColor(Color.WHITE);
            buttonItem.setBackgroundResource(R.drawable.button_theme_background);
            buttonItem.setText("ادامه");
        }
        buttonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExit) {
                    context.onBackPressed();
                } else {
                    if (context.current_mode == Mode.NAME) {
                        context.next(new String[]{((EditText) map.get(0).findViewById(TV)).getText().toString()
                                , ((EditText) map.get(1).findViewById(TV)).getText().toString()});
                    } else {
                        context.next(new String[]{((EditText) map.get(0).findViewById(TV)).getText().toString()});
                    }
                }
            }
        });
        return buttonItem;
    }

    private View space(float size) {
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, size));
        return space;
    }

    private View input(int type) {
        TextInputLayout inputLayout = new TextInputLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2, 1f);
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        inputLayout.setLayoutParams(params);
        Util.setText(inputLayout, context);

        final TextInputEditText editText = new TextInputEditText(context);
        editText.setId(TV);
        TextInputLayout.LayoutParams params1 = new TextInputLayout.LayoutParams(-1, -2);
        params1.gravity = Gravity.CENTER_VERTICAL;
        editText.setLayoutParams(params1);
        Util.setText(editText, context);
        editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        editText.setSingleLine();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        editText.setHintTextColor(Color.LTGRAY);
        editText.setTextColor(Color.DKGRAY);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable data) {
                switch (context.current_mode) {
                    case NAME:
                        editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                        editText.setTextSize(1, 15);
                        break;
                    default:
                        if (data.length() == 0) {
                            editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                            editText.setTextSize(1, 18);
                        } else {
                            editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                            editText.setTextSize(1, 18);
                        }
                        break;
                }
            }
        });
        inputLayout.addView(editText);
        if (type == 1) {
            inputLayout.setVisibility(View.GONE);
        }
        map.put(type, inputLayout);
        return inputLayout;
    }

    private View message() {
        text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        text.setLayoutParams(params);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        return text;
    }

    public void setMode(Mode mode) {
        context.current_mode = mode;
        TextInputLayout inputLayout = map.get(0);
        TextInputEditText editText = (TextInputEditText) inputLayout.findViewById(TV);

        TextInputLayout inputLayout1 = map.get(1);
        TextInputEditText editText1 = (TextInputEditText) inputLayout1.findViewById(TV);

        editText.setText("");
        inputLayout.setHintEnabled(true);
        if (mode == Mode.NUMBER) {
            inputLayout1.setVisibility(View.GONE);
            editText.setTextSize(1, 18);
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
            editText.setHint("");
            inputLayout.setHint("شماره همراه را به صورت 0912xxxxxxx وارد کنید");
            text.setText("برای ورود یا ساخت حساب کاربری خود شماره تلفن همراه خود را وارد کنید.");
        } else if (mode == Mode.VERIFY) {
            inputLayout1.setVisibility(View.GONE);
            editText.setTextSize(1, 18);
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
            editText.setHint("کد تایید");
            inputLayout.setHint("کد تایید حساب کاربری");
            text.setText("برای شما پیامکی حاوی کد تایید ارسال خواهد شد.");
        } else {
            inputLayout1.setVisibility(View.VISIBLE);
            editText.setTextSize(1, 13);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setHint("نام");
//            inputLayout.setHint("نام کامل خود را وارد کنید");

            editText1.setTextSize(1, 13);
            editText1.setInputType(InputType.TYPE_CLASS_TEXT);
            editText1.setHint("نام خانوادگی");
//            inputLayout1.setHint("نام خانوادگی خود را وارد کنید");

            text.setText("این اطلاعات برای تعیین وقت و ثبت پرونده شما ذخیره خواهد شد.");
        }
    }

    public boolean onBackPressed() {
        if (context.current_mode == Mode.NUMBER) {
            return true;
        } else if (context.current_mode == Mode.NAME) {
            context.redirect();
            return false;
        } else {
            setMode(Mode.NUMBER);
            return false;
        }
    }

    public void setError(String error) {
        TextInputLayout inputLayout = map.get(0);
        if (error == null) {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
        } else {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(error);
        }
    }
}
