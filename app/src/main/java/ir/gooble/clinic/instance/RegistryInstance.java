package ir.gooble.clinic.instance;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import ir.gooble.clinic.model.RegistryModel;

public class RegistryInstance {

    private static SharedPreferences get(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDocuments(Context context, RegistryModel model) {
        get(context).edit().putString(RegistryInstance.class.getSimpleName(), new Gson().toJson(model)).apply();
    }

    public static RegistryModel getDocuments(Context context) {
        String data = get(context).getString(RegistryInstance.class.getSimpleName(), null);
        if (data == null) {
            return new RegistryModel();
        } else {
            return new Gson().fromJson(data, RegistryModel.class);
        }
    }
}
