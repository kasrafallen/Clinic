package ir.gooble.clinic.instance;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Calendar;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.model.Gallery;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.PromptUtil;
import ir.gooble.clinic.util.Util;

public class GalleryInstance {

    public static void getPictures(Activity context, InstanceResult resultCall) {
        SharedPreferences preferences = Util.get(context);
        String data = preferences.getString(GalleryInstance.class.getSimpleName(), null);
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
        long lastUpdate = preferences.getLong(GalleryInstance.class.getSimpleName() + "_TimeStamp", 0);
        long current = Calendar.getInstance().getTimeInMillis();
        return current > (lastUpdate + BaseActivity.UPDATE_RATE);
    }

    private static void saveResponse(Activity context, String response) {
        SharedPreferences preferences = Util.get(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GalleryInstance.class.getSimpleName(), response);
        editor.putLong(GalleryInstance.class.getSimpleName() + "_TimeStamp", Calendar.getInstance().getTimeInMillis());
        editor.apply();
    }

    private static void sendRequest(final Activity context, final InstanceResult resultCall) {
        final PromptUtil prompt;
        if (context instanceof BaseActivity) {
            prompt = ((BaseActivity) context).prompt;
        } else {
            prompt = new PromptUtil(context);
        }
        new Rest(context, Api.GALLERY).connect(new CallBack() {
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
        try {
            Gallery gallery = new Gson().fromJson(response, Gallery.class);
            if (gallery == null) {
                return;
            }
            resultCall.onResult(gallery.getPictures());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
