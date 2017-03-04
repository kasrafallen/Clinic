package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.InitRegister;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private InitRegister initRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRegister = (InitRegister) setContentView(this);
    }

    @Override
    public void onClick(View v) {

    }
}
