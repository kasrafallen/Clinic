package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitSign;
import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.User;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;

public class SignActivity extends BaseActivity {

    private static final int REQUEST_CODE = 549;
    private InitSign initSign;

    public InitSign.Mode current_mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        initSign = (InitSign) setContentView(this);
        initSign.setMode(InitSign.Mode.NUMBER);
    }

    private void setup() {
        try {
            setFinishOnTouchOutside(false);
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception ignored) {
        }
    }

    public static void start(UserInstance.SignResult result, BaseActivity context) {
        UserInstance.getInstance().setSignResult(result);
        context.redirect(SignActivity.class, null, REQUEST_CODE);
    }

    public static void handle(int requestCode, int resultCode) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                UserInstance.getInstance().getSignResult().onDone();
            } else {
                UserInstance.getInstance().getSignResult().onDismiss();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (initSign.onBackPressed())
            super.onBackPressed();
    }

    public void next(String var) {
        if (current_mode == InitSign.Mode.NUMBER) {
            if (var.length() == 11 && var.trim().length() == 11 && var.startsWith("09")) {
                sendRequest(var);
                initSign.setError(null);
            } else {
                initSign.setError("شماره وارد شده صحیح نیست");
            }
        } else {
            if (var.length() == 0) {
                sendRequest(var);
                initSign.setError(null);
            } else {
                initSign.setError("کد تایید معتبر نیست");
            }
        }
    }

    private void sendRequest(final String var) {
        Api api = null;
        if (current_mode == InitSign.Mode.NUMBER) {
            api = Api.REGISTER;
        } else {

        }
        new Rest(this, api).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
            }

            @Override
            public void onError(String error) {
                prompt.error(this, error);
            }

            @Override
            public void onInternet() {
                prompt.internet(this);
            }

            @Override
            public void onBefore() {
                prompt.progress();
            }

            @Override
            public void onClick() {
                sendRequest(var);
            }
        }, new User("Asghar", var));
    }
}
