package ir.gooble.clinic.init;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import ir.gooble.clinic.activity.FactActivity;
import ir.gooble.clinic.adaptor.FactAdaptor;
import ir.gooble.clinic.adaptor.MainAdaptor;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.view.AppRecyclerView;
import ir.gooble.clinic.view.AppToolbar;

public class InitFact extends BaseInit {
    private static final int TOOLBAR_ID = +8248484;

    private final FactAdaptor adaptor;
    private FactActivity context;
    private AppToolbar toolbar;

    public AppRecyclerView recyclerView;

    public InitFact(BaseActivity context) {
        super(context);
        this.context = (FactActivity) context;
        this.adaptor = new FactAdaptor(context, dimen);
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
        LinearLayoutManager manager = new LinearLayoutManager(context);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 100;
//            }
//        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptor);
        return recyclerView;
    }

    private View toolbar() {
        toolbar = new AppToolbar(context, true, "تازه های پزشکی", true);
        toolbar.setId(TOOLBAR_ID);
        return toolbar;
    }
}
