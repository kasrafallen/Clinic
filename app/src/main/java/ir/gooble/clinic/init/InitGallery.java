package ir.gooble.clinic.init;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import ir.gooble.clinic.activity.GalleryActivity;
import ir.gooble.clinic.adaptor.GalleryAdaptor;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.view.AppRecyclerView;
import ir.gooble.clinic.view.AppToolbar;

public class InitGallery extends BaseInit {
    private static final int TOOLBAR_ID = +8248484;

    private AppRecyclerView recyclerView;
    private GalleryActivity context;
    private AppToolbar toolbar;

    public InitGallery(BaseActivity context) {
        super(context);
        this.context = (GalleryActivity) context;
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
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setMinimumHeight((int) (dimen[1] - toolbar.getSize()));
        return recyclerView;
    }

    public void setAdaptor(String[] list){
        GalleryAdaptor adaptor = new GalleryAdaptor(context, list);
        recyclerView.setAdapter(adaptor);
    }

    private View toolbar() {
        toolbar = new AppToolbar(context, true, "گالری تصاویر", true);
        toolbar.setId(TOOLBAR_ID);
        return toolbar;
    }
}
