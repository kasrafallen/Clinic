package ir.gooble.clinic.init;

import android.view.View;

import ir.gooble.clinic.activity.SignActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;

public class InitSign extends BaseInit {
    private SignActivity context;

    public InitSign(BaseActivity context) {
        super(context);
        this.context = (SignActivity) context;
    }

    @Override
    protected View create() {
        return null;
    }
}
