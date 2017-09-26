package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDoctor;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;

public class DoctorActivity extends BaseActivity {
    private InitDoctor initDoctor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDoctor = (InitDoctor) setContentView(this);
        sendRequest();
    }

    private void sendRequest() {
        new Rest(this, Api.DOCTOR_INFO).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
            }

            @Override
            public void onError(String error) {
                prompt.error(this, error);
            }

            @Override
            public void onInternet() {
                prompt.internet(this);
            }

            @Override
            public void onBefore() {
                prompt.progress();
            }

            @Override
            public void onClick() {
                sendRequest();
            }
        });
    }
}
