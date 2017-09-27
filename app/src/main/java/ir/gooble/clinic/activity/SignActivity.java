package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitSign;
import ir.gooble.clinic.instance.UserInstance;

public class SignActivity extends BaseActivity {

    private static final int REQUEST_CODE = 549;
    private InitSign initSign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception ignored) {

        }
        initSign = (InitSign) setContentView(this);
        initSign.setMode(InitSign.Mode.NUMBER);
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
}
