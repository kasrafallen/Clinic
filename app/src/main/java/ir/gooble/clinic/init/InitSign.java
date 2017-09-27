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
import android.widget.LinearLayout;
import android.widget.Space;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.SignActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class InitSign extends BaseInit {
    private SignActivity context;
    private int margin;

    private TextInputLayout inputLayout;
    private TextInputEditText editText;
    private AppText text;

    public enum Mode {
        NUMBER, VERIFY
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
        layout.addView(input());
        layout.addView(function());
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
                    context.next(editText.getText().toString());
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

    private View input() {
        inputLayout = new TextInputLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        inputLayout.setLayoutParams(params);
        Util.setText(inputLayout, context);

        editText = new TextInputEditText(context);
        TextInputLayout.LayoutParams params1 = new TextInputLayout.LayoutParams(-1, -2);
        params1.gravity = Gravity.CENTER_VERTICAL;
        editText.setLayoutParams(params1);
        editText.setTextSize(1, 18);
        Util.setText(editText, context);
        editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        editText.setSingleLine();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        editText.setHintTextColor(Color.LTGRAY);
        editText.setTextColor(Color.DKGRAY);
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable data) {
                if (data.length() == 0) {
                    editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                    editText.setTextSize(1, 18);
                } else {
                    editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                    editText.setTextSize(1, 18);
                }
            }
        });

        inputLayout.addView(editText);
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
        editText.setText("");
        inputLayout.setHintEnabled(true);
        if (mode == Mode.NUMBER) {
            editText.setHint("");
            inputLayout.setHint("شماره همراه را به صورت 0912xxxxxxx وارد کنید");
            text.setText("برای ورود یا ساخت حساب کاربری خود شماره تلفن همراه خود را وارد کنید.");
        } else {
            editText.setHint("کد تایید");
            inputLayout.setHint("کد تایید حساب کاربری");
            text.setText("برای شما پیامکی حاوی کد تایید ارسال خواهد شد.");
        }
    }

    public boolean onBackPressed() {
        if (context.current_mode == Mode.NUMBER) {
            return true;
        }
        setMode(Mode.NUMBER);
        return false;
    }

    public void setError(String error) {
        if (error == null) {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
        } else {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(error);
        }
    }
}
