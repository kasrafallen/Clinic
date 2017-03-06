package ir.gooble.clinic.adaptor;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import ir.gooble.clinic.activity.RegisterActivity;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class SpinAdaptor extends ArrayAdapter<String> {
    private RegisterActivity context;
    private String[] objects;
    private int margin;
    private int width;

    public SpinAdaptor(RegisterActivity context, String[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
        this.margin = Util.toPx(8, context);
        this.width = Util.toPx(150, context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, true);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, false);
    }

    private View getView(int position, boolean fromDrop) {
        AppText text = new AppText(context);
        text.setLayoutParams(new LinearLayout.LayoutParams(width, -2));
        if (fromDrop) {
            text.setGravity(Gravity.CENTER);
        } else {
            text.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        }
        text.setSingleLine();
        text.setText(objects[position]);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        text.setPadding(0, margin, 0, margin);
        return text;
    }
}
