package ir.gooble.clinic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitReserve;
import ir.gooble.clinic.instance.DoctorInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.instance.TimeInstance;
import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.Day;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.model.Reserve;
import ir.gooble.clinic.model.User;
import ir.gooble.clinic.model.Week;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.CalendarUtil;

public class ReserveActivity extends BaseActivity {

    public HashMap<long[], ArrayList<Reserve>> data = new HashMap<>();
    private HashMap<Doctor, Boolean> doctors = new HashMap<>();

    private InitReserve initReserve;
    private boolean isClosed;

    public Calendar current_calendar;
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
        startProcess();
    }

    private void startProcess() {
        if (getIntent().getAction() != null) {
            isSingle = true;
            String json = getIntent().getAction();
            Doctor doctor = new Gson().fromJson(json, Doctor.class);
            doctors.put(doctor, false);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    initReserve.setPager();
                }
            });
        } else {
            isSingle = false;
            DoctorInstance.getDoctors(this, new InstanceResult() {
                @Override
                public void onResult(Object[] objects) {
                    if (objects.length == 0) {
                        return;
                    }
                    for (Object doctor : objects) {
                        doctors.put((Doctor) doctor, false);
                    }
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            initReserve.setPager();
                        }
                    });
                }
            });
        }
    }

    public void sendRequest() {
        for (Doctor dr : doctors.keySet()) {
            if (!doctors.get(dr)) {
                sendRequest(dr);
            }
        }
    }

    private void sendRequest(final Doctor doctor) {
        if (doctor == null) {
            return;
        }
        int index_time = CalendarUtil.get(current_calendar.get(Calendar.DAY_OF_WEEK));
        final String start_time = getStartTime(current_calendar);
        rest = new Rest(this, Api.RESERVE_INFO);
        rest.connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                if (isClosed) {
                    return;
                }
                requests++;
                if (isSingle) {
                    prompt.hide();
                }
                doctors.put(doctor, true);
                if (requests == doctors.size()) {
                    sendRequest();
                }
                initReserve.add(createData(doctor, response));
            }

            @Override
            public void onError(String error) {
                requests++;
                if (isSingle || requests == doctors.size()) {
                    prompt.error(this, error);
                }
            }

            @Override
            public void onInternet() {
                requests++;
                if (isSingle || requests == doctors.size()) {
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
        }, new Reserve(doctor.getDoctorID(), start_time, index_time));
    }

    @Override
    public void finish() {
        isClosed = true;
        super.finish();
    }

    public void reset() {
        if (rest != null) {
            rest.cancel();
        }
        for (Doctor doctor : doctors.keySet()) {
            doctors.put(doctor, false);
        }
    }

    private Reserve createData(Doctor doctor, String response) {
        Reserve reserve = new Gson().fromJson(response, Reserve.class);
        reserve.setDoctor(doctor);

        long from = current_calendar.getTimeInMillis();
        long to = from + TimeUnit.DAYS.toMillis(6 - CalendarUtil.get(current_calendar.get(Calendar.DAY_OF_WEEK)));

        long[] key = new long[]{from, to};

        ArrayList<Reserve> reserves = getReserves(key);
        reserves.add(reserve);

        data.put(key, reserves);
        return reserve;
    }

    public void load(int position) {
        current_calendar.setTimeInMillis(base_calendar.getTimeInMillis() + TimeUnit.DAYS.toMillis(position));

        boolean isFound = false;
        ArrayList<Reserve> reserves = getReserves(current_calendar.getTimeInMillis());
        if (reserves.size() > 0) {
            isFound = true;
            for (Reserve reserve : reserves) {
                initReserve.add(reserve);
            }
        }
        if (!isFound) {
            reset();
            sendRequest();
        }
    }

    private ArrayList<Reserve> getReserves(long startTime) {
        ArrayList<Reserve> reserves = new ArrayList<>();
        for (long[] stamp : data.keySet()) {
            if (stamp != null) {
                if (stamp[0] <= startTime && stamp[1] >= startTime) {
                    reserves.addAll(data.get(stamp));
                }
            }
        }
        return reserves;
    }

    private ArrayList<Reserve> getReserves(long[] calendarTimes) {
        ArrayList<Reserve> reserves = new ArrayList<>();
        if (data.containsKey(calendarTimes)) {
            reserves.addAll(data.get(calendarTimes));
        }
        return reserves;
    }

    public String getStartTime(Calendar current_calendar) {
        return current_calendar.get(Calendar.YEAR)
                + "-" + (current_calendar.get(Calendar.MONTH) + 1)
                + "-" + current_calendar.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isValid(Reserve reserve) {
        if (reserve.getWeek() != null && reserve.getWeek().length > 0
                && reserve.getDays() != null && reserve.getDays().length > 0) {
            for (Week week : reserve.getWeek()) {
                if (week.isCurrentDay(CalendarUtil.get(current_calendar.get(Calendar.DAY_OF_WEEK)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Day> getDays(Reserve reserve, String time) {
        ArrayList<Day> days = new ArrayList<>();
        if (reserve.getWeek() != null && reserve.getWeek().length > 0
                && reserve.getDays() != null && reserve.getDays().length > 0) {
            for (Day day : reserve.getDays()) {
                if (day.isCurrentDate(time)) {
                    days.add(day);
                }
            }
        }
        return days;
    }

    public void sendRequest(final Day day, final Reserve reserve) {
        User user = UserInstance.getUser(this);
        if (user == null || (user.getPLastName() == null && user.getPName() == null)) {
            SignActivity.start(new UserInstance.SignResult() {
                @Override
                public void onDone() {

                }

                @Override
                public void onDismiss() {

                }
            }, this);
            return;
        }
        new Rest(this, Api.RESERVE_POST).connect(new CallBack() {
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
                sendRequest(day, reserve);
            }
        }, new Reserve(reserve.getDoctorID(), day.getDate(), day.getIndex(), day.getTime(), user));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SignActivity.handle(requestCode, resultCode);
    }
}

