package ir.gooble.clinic.application;

import android.view.View;

import ir.gooble.clinic.R;
import ir.gooble.clinic.init.InitDrawer;
import ir.gooble.clinic.init.InitSign;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppDrawerLayout;

public abstract class BaseInit {
    public float[] dimen;
    public InitDrawer drawer;
    private BaseActivity context;

    public BaseInit(BaseActivity context) {
        this.context = context;
        this.dimen = Util.getDimen(context);
    }

    public View getView() {
        if(this instanceof InitSign){
            return create();
        }
        context.drawer = new AppDrawerLayout(context);
        context.drawer.setBackgroundResource(R.color.base);
        context.drawer.setScrimColor(context.getResources().getColor(R.color.scrim));
        context.drawer.setDrawerElevation(30);
        context.drawer.addView(create());
        context.drawer.addView(drawer());
        return context.drawer;
    }

    private View drawer() {
        drawer = new InitDrawer(context, dimen);
        return drawer.create();
    }

    protected abstract View create();
}
