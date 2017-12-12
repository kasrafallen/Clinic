package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitReserve;
import ir.gooble.clinic.instance.DoctorInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.instance.TimeInstance;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.model.Reserve;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.CalendarUtil;

public class ReserveActivity extends BaseActivity {

    private HashMap<Doctor, Boolean> list = new HashMap<>();
    private InitReserve initReserve;
    private boolean isClosed;

    private Calendar current_calendar;
    private Calendar base_calendar;
    private boolean isSingle;

    private int requests;
    private Rest rest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_calendar = TimeInstance.getCalendar(this);
        current_calendar = TimeInstance.getCalendar(this);
        initReserve = (InitReserve) setContentView(this);
        continueProcess();
    }

    private void continueProcess() {
        if (getIntent().getAction() != null) {
            isSingle = true;
            String json = getIntent().getAction();
            Doctor doctor = new Gson().fromJson(json, Doctor.class);
            list.put(doctor, false);
            sendRequest();
        } else {
            isSingle = false;
            DoctorInstance.getDoctors(this, new InstanceResult() {
                @Override
                public void onResult(Object[] objects) {
                    if (objects.length == 0) {
                        return;
                    }
                    for (Object doctor : objects) {
                        list.put((Doctor) doctor, false);
                    }
                    sendRequest();
                }
            });
        }
    }

    public void sendRequest() {
        for (Doctor dr : list.keySet()) {
            if (!list.get(dr)) {
                sendRequest(dr);
            }
        }
    }

    private void sendRequest(final Doctor doctor) {
        if (doctor == null) {
            return;
        }
        rest = new Rest(this, Api.RESERVE_INFO);
        rest.connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                if (isClosed) {
                    return;
                }
                if (isSingle) {
                    prompt.hide();
                }
                list.put(doctor, true);
                initReserve.add(doctor, response);
                requests++;
                if (requests == list.size()) {
                    sendRequest();
                }
            }

            @Override
            public void onError(String error) {
                requests++;
                if (isSingle || requests == list.size()) {
                    prompt.error(this, error);
                }
            }

            @Override
            public void onInternet() {
                requests++;
                if (isSingle || requests == list.size()) {
                    prompt.internet(this);
                }
            }

            @Override
            public void onBefore() {
                if (isSingle) {
                    prompt.progress();
                }
            }

            @Override
            public void onClick() {
                requests--;
                sendRequest(doctor);
            }
        }, new Reserve(doctor.getDoctorID(), getStartTime(), current_calendar.get(Calendar.DAY_OF_WEEK)));
    }

    private String getStartTime() {
        return current_calendar.get(Calendar.YEAR)
                + "-" + (current_calendar.get(Calendar.MONTH) + 1)
                + "-" + current_calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void finish() {
        isClosed = true;
        super.finish();
    }

    public String getDate(int position) {
        current_calendar.setTimeInMillis(base_calendar.getTimeInMillis() + TimeUnit.DAYS.toMillis(position));
        return CalendarUtil.getDate(current_calendar.getTime());
    }

    public void reset() {
        if (rest != null) {
            rest.cancel();
        }
        for (Doctor doctor : list.keySet()) {
            list.put(doctor, false);
        }
    }
}
