package ir.gooble.clinic.instance;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.Util;

public class DoctorInstance {

    public static void getDoctors(BaseActivity context, InstanceResult resultCall) {
        SharedPreferences preferences = Util.get(context);
        String data = preferences.getString(DoctorInstance.class.getSimpleName(), null);
        if (data == null) {
            sendRequest(context, resultCall);
        } else {
            decompile(context, data, resultCall);
        }
    }

    private static void saveResponse(BaseActivity context, String response) {
        SharedPreferences preferences = Util.get(context);
        preferences.edit().putString(DoctorInstance.class.getSimpleName(), response).apply();
    }

    private static void sendRequest(final BaseActivity context, final InstanceResult resultCall) {
        new Rest(context, Api.DOCTOR_INFO).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                context.prompt.hide();
                decompile(context, response, resultCall);
            }

            @Override
            public void onError(String error) {
                context.prompt.error(this, error);
            }

            @Override
            public void onInternet() {
                context.prompt.internet(this);
            }

            @Override
            public void onBefore() {
                context.prompt.progress();
            }

            @Override
            public void onClick() {
                sendRequest(context, resultCall);
            }
        });
    }

    private static void decompile(BaseActivity context, String response, InstanceResult resultCall) {
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
            saveResponse(context, response);
            resultCall.onResult(objects);
        }
    }
}
