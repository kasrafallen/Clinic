package ir.gooble.clinic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Window;

import com.google.gson.Gson;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseApplication;
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
    private User user;

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
                User params = new User("Asghar", var, BaseApplication.getId(SignActivity.this));
                sendRequest(params);
                initSign.setError(null);
            } else {
                initSign.setError("شماره وارد شده صحیح نیست");
            }
        } else {
            if (var.length() == 0) {
                User params = null;
                sendRequest(params);
                initSign.setError(null);
            } else {
                initSign.setError("کد تایید معتبر نیست");
            }
        }
    }

    private void sendRequest(final User params) {
        Api api = null;
        if (current_mode == InitSign.Mode.NUMBER) {
            api = Api.REGISTER;
        } else {
            UserInstance.setUser(SignActivity.this, user);
            LocalBroadcastManager.getInstance(SignActivity.this).sendBroadcast(new Intent(BaseActivity.UPDATE));
            setResult(RESULT_OK);
            finish();
            return;
        }
        final Api finalApi = api;
        new Rest(this, api).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                if (finalApi == Api.REGISTER) {
                    user = new Gson().fromJson(response, User.class);
                    user.setName(params.getName());
                    user.setMobile_number(params.getMobile_number());
                    user.setDeviceID(params.getDeviceID());
                    initSign.setMode(InitSign.Mode.VERIFY);
                } else {
                    UserInstance.setUser(SignActivity.this, user);
                    LocalBroadcastManager.getInstance(SignActivity.this).sendBroadcast(new Intent(BaseActivity.UPDATE));
                    setResult(RESULT_OK);
                    finish();
                }
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
                sendRequest(params);
            }
        }, params);
    }
}
