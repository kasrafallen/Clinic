package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDetail;

public class DetailActivity extends BaseActivity {
    private InitDetail initDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDetail = (InitDetail) setContentView(this);
    }

    public void share() {

    }

    public void redirect() {
//        run(ReserveActivity.class.getSimpleName(),this,null,null);
    }
}
