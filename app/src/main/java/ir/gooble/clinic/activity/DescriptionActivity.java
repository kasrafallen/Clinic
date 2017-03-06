package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDescription;
import ir.gooble.clinic.model.FactModel;

public class DescriptionActivity extends BaseActivity {
    private InitDescription initDescription;
    public FactModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new Gson().fromJson(getIntent().getAction(), FactModel.class);
        initDescription = (InitDescription) setContentView(this);
    }
}
