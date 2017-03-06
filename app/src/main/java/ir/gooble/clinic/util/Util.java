package ir.gooble.clinic.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.gooble.clinic.R;

public class Util {
    private static final String WIDTH = "WIDTH";
    private static final String HEIGHT = "HEIGHT";

    private static SharedPreferences get(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isDimen(Context context) {
        SharedPreferences preferences = get(context);
        return preferences.contains(WIDTH) && preferences.contains(HEIGHT);
    }

    public static void setDimen(float[] floats, Context context) {
        SharedPreferences.Editor editor = get(context).edit();
        editor.putFloat(WIDTH, floats[0]);
        editor.putFloat(HEIGHT, floats[1]);
        editor.apply();
    }

    public static float[] getDimen(Context context) {
        SharedPreferences preferences = get(context);
        return new float[]{preferences.getFloat(WIDTH, 0), preferences.getFloat(HEIGHT, 0)};
    }

    public static void setBackground(View view, Context context) {
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        view.setBackgroundResource(backgroundResource);
        typedArray.recycle();
    }

    public static void setText(TextView tv, Context context) {
        tv.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf"));
    }

    public static void setText(TextInputLayout tv, Context context) {
        tv.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf"));
    }

    public static int getToolbarSize(Context context) {
        return toPx(56, context);
    }

    public static int toPx(int size, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, context.getResources().getDisplayMetrics());
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setRipple(RelativeLayout mid, Context context, boolean isDefault) {
        int ripple_color;
        if (isDefault) {
            ripple_color = context.getResources().getColor(R.color.ripple_light);
        } else {
            ripple_color = context.getResources().getColor(R.color.ripple_dark);
        }
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());
        mid.setBackground(new RippleDrawable(new ColorStateList(
                new int[][]
                        {
                                new int[]{android.R.attr.state_pressed},
                                new int[]{}
                        },
                new int[]
                        {
                                ripple_color,
                                ripple_color
                        })
                , null, shape));
    }
}
