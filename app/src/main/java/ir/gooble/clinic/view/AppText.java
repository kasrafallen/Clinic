package ir.gooble.clinic.view;

import android.content.Context;
import android.widget.TextView;

import ir.gooble.clinic.util.Util;

public class AppText extends TextView {
    public AppText(Context context) {
        super(context);
        Util.setText(this, context);
    }
}
