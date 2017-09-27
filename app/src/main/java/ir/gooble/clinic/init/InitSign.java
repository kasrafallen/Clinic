package ir.gooble.clinic.init;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ir.gooble.clinic.activity.SignActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class InitSign extends BaseInit {
    private SignActivity context;
    private Mode current_mode;
    private int margin;

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
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        layout.addView(message());
        layout.addView(input());
        return layout;
    }

    private View input() {
        TextInputLayout inputLayout = new TextInputLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, Util.toPx(60, context));
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        inputLayout.setLayoutParams(params);
        Util.setText(inputLayout, context);

        editText = new TextInputEditText(context);
        TextInputLayout.LayoutParams params1 = new TextInputLayout.LayoutParams(-1, -1);
        editText.setLayoutParams(params1);
        editText.setTextSize(1, 15);
        editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        editText.setSingleLine();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        editText.setHintTextColor(Color.LTGRAY);
        editText.setTextColor(Color.GRAY);
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
                    editText.setTextSize(1, 15);
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
        text.setTextSize(1, 15);
        return text;
    }

    public void setMode(Mode mode) {
        this.current_mode = mode;
        this.editText.setText("");
        if (mode == Mode.NUMBER) {
            editText.setHint("شماره همراه را به صورت 0912xxxxxxx وارد کنید");
            text.setText("برای ورود یا ساخت حساب کاربری خود شماره تلفن همراه خود را وارد کنید.");
        } else {
            editText.setHint("کد تایید حساب کاربری");
            text.setText("برای شما پیامکی حاوی کد تایید ارسال خواهد شد.");
        }
    }

    public boolean onBackPressed() {
        if(current_mode == Mode.NUMBER){
            return true;
        }
        setMode(Mode.NUMBER);
        return false;
    }
}
