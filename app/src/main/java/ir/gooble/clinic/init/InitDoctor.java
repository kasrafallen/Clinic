package ir.gooble.clinic.init;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

import ir.gooble.clinic.activity.DoctorActivity;
import ir.gooble.clinic.adaptor.DoctorAdaptor;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.view.AppRecyclerView;
import ir.gooble.clinic.view.AppToolbar;

public class InitDoctor extends BaseInit {
    private static final int TOOLBAR_ID = +8248484;

    private ArrayList<Doctor> list = new ArrayList<>();
    private final DoctorAdaptor adaptor;
    private DoctorActivity context;
    private AppToolbar toolbar;

    public AppRecyclerView recyclerView;

    public InitDoctor(BaseActivity context) {
        super(context);
        this.context = (DoctorActivity) context;
        this.adaptor = new DoctorAdaptor(context, dimen, list);
    }

    @Override
    protected View create() {
        CoordinatorLayout layout = new CoordinatorLayout(context);
        layout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        layout.addView(toolbar());
        layout.addView(recycler());
        return layout;
    }

    private View recycler() {
        recyclerView = new AppRecyclerView(context);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-1, -2);
        params.topMargin = toolbar.getSize();
        recyclerView.setLayoutParams(params);
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptor);
        recyclerView.setMinimumHeight((int) (dimen[1] - toolbar.getSize()));
        return recyclerView;
    }

    private View toolbar() {
        toolbar = new AppToolbar(context, true, "معرفی پزشکان", true);
        toolbar.setId(TOOLBAR_ID);
        return toolbar;
    }

    public void update(Doctor[] doctors) {
        list.clear();
        Collections.addAll(list, doctors);
        adaptor.notifyDataSetChanged();
    }
}
