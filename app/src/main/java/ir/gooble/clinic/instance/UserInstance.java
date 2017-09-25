package ir.gooble.clinic.instance;

import android.content.Context;

import com.google.gson.Gson;

import ir.gooble.clinic.model.User;
import ir.gooble.clinic.util.Util;

public class UserInstance {

    public static final String USER = "USER_";

    public static User getUser(Context context) {
        String data = Util.get(context).getString(USER, null);
        if (data == null) {
            return null;
        } else {
            return new Gson().fromJson(data, User.class);
        }
    }

    public static void setUser(Context context, User user) {
        Util.get(context).edit().putString(USER, new Gson().toJson(user)).apply();
    }

    public static boolean isEmpty(Context context) {
        return !Util.get(context).contains(USER);
    }
}
