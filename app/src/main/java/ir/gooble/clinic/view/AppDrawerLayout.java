package ir.gooble.clinic.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.MainActivity;

public class AppDrawerLayout extends DrawerLayout {

    public AppDrawerLayout(Context context) {
        super(context);

        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setScrimColor(context.getResources().getColor(R.color.scrim));
    }
}
