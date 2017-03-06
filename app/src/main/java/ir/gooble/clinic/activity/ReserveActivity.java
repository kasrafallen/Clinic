package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitReserve;

public class ReserveActivity extends BaseActivity {

    private InitReserve initReserve;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReserve = (InitReserve) setContentView(this);
    }
}
