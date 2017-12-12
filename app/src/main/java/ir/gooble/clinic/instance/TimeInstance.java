package ir.gooble.clinic.instance;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.util.Util;

public class TimeInstance {

    public static boolean isEmpty(Context context) {
        SharedPreferences pref = Util.get(context);
        long date = pref.getLong(TimeInstance.class.getSimpleName(), 0);
        return date == 0 || shouldUpdate(context);
    }

    private static boolean shouldUpdate(Context context) {
        SharedPreferences preferences = Util.get(context);
        long lastUpdate = preferences.getLong(TimeInstance.class.getSimpleName() + "_TimeStamp", 0);
        long current = Calendar.getInstance().getTimeInMillis();
        return current > (lastUpdate + BaseActivity.UPDATE_RATE);
    }

    public static void setTime(Context context, long timeStamp) {
        SharedPreferences pref = Util.get(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(TimeInstance.class.getSimpleName(), timeStamp);
        editor.putLong(TimeInstance.class.getSimpleName() + "_TimeStamp", Calendar.getInstance().getTimeInMillis());
        editor.apply();
    }

    private static long getTime(Context context) {
        SharedPreferences pref = Util.get(context);
        return pref.getLong(TimeInstance.class.getSimpleName(), 0);
    }

    public static Calendar getCalendar(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getTime(context));
        return calendar;
    }
}
