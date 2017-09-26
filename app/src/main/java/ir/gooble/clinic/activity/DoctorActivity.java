package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDoctor;
import ir.gooble.clinic.instance.DoctorInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.model.Doctor;

public class DoctorActivity extends BaseActivity {
    private InitDoctor initDoctor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDoctor = (InitDoctor) setContentView(this);
        DoctorInstance.getDoctors(this, new InstanceResult() {
            @Override
            public void onResult(Object[] objects) {
                initDoctor.update((Doctor[]) objects);
            }
        });
    }
}
