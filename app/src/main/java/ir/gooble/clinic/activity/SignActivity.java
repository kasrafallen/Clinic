package ir.gooble.clinic.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Window;

import com.google.gson.Gson;

import ir.gooble.clinic.R;
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
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        initSign = (InitSign) setContentView(this);
        user = UserInstance.getUser(this);
        if (user != null && user.getPLastName() == null && user.getPName() == null) {
            initSign.setMode(InitSign.Mode.NAME);
        } else {
            initSign.setMode(InitSign.Mode.NUMBER);
        }
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

    public void next(String[] vars) {
        String var = vars[0];
        if (current_mode == InitSign.Mode.NUMBER) {
            if (var.length() == 11 && var.trim().length() == 11 && var.startsWith("09")) {
                sendRequest(new User(var));
                initSign.setError(null);
            } else {
                initSign.setError("شماره وارد شده صحیح نیست");
            }
        } else if (current_mode == InitSign.Mode.VERIFY) {
            if (checkResult(var)) {
                initSign.setError(null);
            } else {
                initSign.setError("کد تایید معتبر نیست");
            }
        } else {
            for (String data : vars) {
                if (data.length() <= 3 || data.trim().length() <= 3) {
                    initSign.setError("نام یا نام خانوادگی صحیح نیست");
                    return;
                }
            }
            sendEditRequest(vars);
            initSign.setError(null);
        }
    }

    private void sendRequest(final User params) {
        new Rest(this, Api.REGISTER).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                user = new Gson().fromJson(response, User.class);
                user.setMobile_number(params.getMobile_number());
                initSign.setMode(InitSign.Mode.VERIFY);
                notifyCode(user.getSms_code());
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

    private boolean checkResult(String var) {
        if (user != null && user.getSms_code() != null && user.getSms_code().equals(var)) {
            verify(user);
            return true;
        }
        return false;
    }

    private void verify(final User user) {
        new Rest(this, Api.VERIFY).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                User signed = new Gson().fromJson(response, User.class);
                UserInstance.setUser(SignActivity.this, signed);
                if (signed.getPName() == null && signed.getPLastName() == null) {
                    initSign.setMode(InitSign.Mode.NAME);
                    return;
                }
                redirect();
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
                verify(user);
            }
        }, user);
    }

    private void sendEditRequest(final String var[]) {
        user.setPName(var[0]);
        user.setPLastName(var[1]);
        new Rest(this, Api.PROFILE_POST).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                UserInstance.setUser(SignActivity.this, user);
                redirect();
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
                sendEditRequest(var);
            }
        }, user);
    }

    private void notifyCode(String smsCode) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentText("سلام، خوش آمدید\nکد ورود: " + smsCode);
        builder.setAutoCancel(true);
        builder.setVibrate(new long[]{500, 300, 100});
        builder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    public void redirect() {
        LocalBroadcastManager.getInstance(SignActivity.this).sendBroadcast(new Intent(BaseActivity.UPDATE));
        setResult(RESULT_OK);
        finish();
    }
}
