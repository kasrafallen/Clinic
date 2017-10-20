package ir.gooble.clinic.instance;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.PromptUtil;
import ir.gooble.clinic.util.Util;

public class DoctorInstance {

    public static void getDoctors(Activity context, InstanceResult resultCall) {
        SharedPreferences preferences = Util.get(context);
        String data = preferences.getString(DoctorInstance.class.getSimpleName(), null);
        if (data == null) {
            sendRequest(context, resultCall);
        } else {
            if (shouldUpdate(context)) {
                sendRequest(context, resultCall);
            } else {
                decompile(data, resultCall);
            }
        }
    }

    private static boolean shouldUpdate(Activity context) {
        SharedPreferences preferences = Util.get(context);
        long lastUpdate = preferences.getLong(DoctorInstance.class.getSimpleName() + "_TimeStamp", 0);
        long current = Calendar.getInstance().getTimeInMillis();
        return current > (lastUpdate + TimeUnit.DAYS.toMillis(4));
    }

    private static void saveResponse(Activity context, String response) {
        SharedPreferences preferences = Util.get(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DoctorInstance.class.getSimpleName(), response);
        editor.putLong(DoctorInstance.class.getSimpleName() + "_TimeStamp", Calendar.getInstance().getTimeInMillis());
        editor.apply();
    }

    private static void sendRequest(final Activity context, final InstanceResult resultCall) {
        final PromptUtil prompt;
        if (context instanceof BaseActivity) {
            prompt = ((BaseActivity) context).prompt;
        } else {
            prompt = new PromptUtil(context);
        }
        new Rest(context, Api.DOCTOR_INFO).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                saveResponse(context, response);
                decompile(response, resultCall);
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
                sendRequest(context, resultCall);
            }
        });
    }

    private static void decompile(String response, InstanceResult resultCall) {
        Object[] objects = null;
        try {
            JSONArray array = new JSONArray(response);
            if (array.length() > 0) {
                JSONArray jsonArray = array.getJSONArray(0);
                String data = jsonArray.toString();
                objects = new Gson().fromJson(data, Doctor[].class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (objects != null) {
            resultCall.onResult(objects);
        }
    }
}
