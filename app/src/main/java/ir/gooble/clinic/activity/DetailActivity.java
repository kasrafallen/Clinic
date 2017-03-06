package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDetail;
import ir.gooble.clinic.instance.Attributes;

public class DetailActivity extends BaseActivity {
    private InitDetail initDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDetail = (InitDetail) setContentView(this);
    }

    public void redirect() {
        run(Attributes.FIELD_RESERVE, this, null, null);
    }
}
