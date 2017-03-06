package ir.gooble.clinic.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.Calendar;

public class DialogUtil {

    public static boolean show(Dialog dialog, Activity context) {
        if (context != null && !context.isFinishing() && dialog != null) {
            try {
                dialog.show();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void pickDate(Activity context, DatePickerDialog.OnDateSetListener listener) {
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context, listener, mYear, mMonth, mDay);
        DialogUtil.show(dialog, context);
    }
}
