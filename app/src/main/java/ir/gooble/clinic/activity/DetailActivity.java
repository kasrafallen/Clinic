package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDetail;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.model.Doctor;

public class DetailActivity extends BaseActivity {
    private InitDetail initDetail;
    public Doctor doctor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctor = new Gson().fromJson(getIntent().getAction(), Doctor.class);
        initDetail = (InitDetail) setContentView(this);
    }

    public void redirect() {
        redirect(ReserveActivity.class, new Gson().toJson(doctor));
    }
}
