package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitMain;

public class MainActivity extends BaseActivity {

    private InitMain initMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMain = (InitMain) setContentView(this);
    }
}
