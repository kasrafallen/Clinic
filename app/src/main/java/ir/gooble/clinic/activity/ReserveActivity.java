package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.Gson;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitReserve;
import ir.gooble.clinic.instance.DoctorInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;

public class ReserveActivity extends BaseActivity implements View.OnClickListener {

    private InitReserve initReserve;
    private int current_progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReserve = (InitReserve) setContentView(this);
        if (getIntent().getAction() != null) {
            String json = getIntent().getAction();
            Doctor doctor = new Gson().fromJson(getIntent().getAction(), Doctor.class);
            sendRequest(new Doctor[]{doctor});
        } else {
            DoctorInstance.getDoctors(this, new InstanceResult() {
                @Override
                public void onResult(Object[] objects) {
                    sendRequest((Doctor[]) objects);
                }
            });
        }
    }

    private void sendRequest(final Doctor[] objects) {
        if(current_progress >= objects.length){
            return;
        }
        new Rest(this, Api.RESERVE_INFO).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                sendRequest(objects);
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onInternet() {
                prompt.internet(this);
            }

            @Override
            public void onBefore() {

            }

            @Override
            public void onClick() {
                sendRequest(objects);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
