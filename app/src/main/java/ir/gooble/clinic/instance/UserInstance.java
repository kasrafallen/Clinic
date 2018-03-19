package ir.gooble.clinic.instance;

import android.content.Context;

import com.google.gson.Gson;

import ir.gooble.clinic.model.User;
import ir.gooble.clinic.util.Util;

public class UserInstance {

    private final static UserInstance instance = new UserInstance();
    private SignResult signResult;

    public static UserInstance getInstance() {
        return instance;
    }

    public SignResult getSignResult() {
        return signResult;
    }

    public void setSignResult(SignResult signResult) {
        this.signResult = signResult;
    }

    public interface SignResult {
        void onDone();

        void onDismiss();
    }

    private UserInstance() {

    }

    public static User getUser(Context context) {
        String data = Util.get(context).getString(UserInstance.class.getSimpleName(), null);
        if (data == null) {
            return null;
        } else {
            return new Gson().fromJson(data, User.class);
        }
    }

    public static void setUser(Context context, User user) {
        if (user != null) {
            if (!isEmpty(user.getPhoneNumber()) && isEmpty(user.getMobile_number())) {
                user.setMobile_number(user.getPhoneNumber());
            }
            if (!isEmpty(user.getMobile_number()) && isEmpty(user.getPhoneNumber())) {
                user.setPhoneNumber(user.getMobile_number());
            }
            if (!isEmpty(user.getPID()) && isEmpty(user.getUID())) {
                user.setUID(user.getPID());
            }
            if (!isEmpty(user.getUID()) && isEmpty(user.getPID())) {
                user.setPID(user.getUID());
            }
        }
        Util.get(context).edit().putString(UserInstance.class.getSimpleName(), new Gson().toJson(user)).apply();
    }

    private static boolean isEmpty(String data) {
        return data == null || data.equals("");
    }

    public static boolean isEmpty(Context context) {
        return !Util.get(context).contains(UserInstance.class.getSimpleName());
    }
}
