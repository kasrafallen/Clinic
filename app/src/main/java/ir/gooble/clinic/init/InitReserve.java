package ir.gooble.clinic.init;

import android.view.View;

import ir.gooble.clinic.activity.ReserveActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;

public class InitReserve extends BaseInit {
    private ReserveActivity context;

    public InitReserve(BaseActivity context) {
        super(context);
        this.context = (ReserveActivity) context;
    }

    @Override
    protected View create() {
        return null;
    }
}
