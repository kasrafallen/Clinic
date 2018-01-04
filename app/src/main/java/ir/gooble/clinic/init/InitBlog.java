package ir.gooble.clinic.init;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

import ir.gooble.clinic.activity.BlogActivity;
import ir.gooble.clinic.adaptor.FactAdaptor;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.application.BaseInit;
import ir.gooble.clinic.model.Blog;
import ir.gooble.clinic.view.AppRecyclerView;
import ir.gooble.clinic.view.AppToolbar;

public class InitBlog extends BaseInit {
    private static final int TOOLBAR_ID = +8248484;

    private ArrayList<Blog.Post> blogList;
    private FactAdaptor adaptor;
    private BlogActivity context;
    private AppToolbar toolbar;

    public InitBlog(BaseActivity context) {
        super(context);
        this.context = (BlogActivity) context;
        this.blogList = new ArrayList<>();
        this.adaptor = new FactAdaptor(context, dimen, blogList);
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
        AppRecyclerView recyclerView = new AppRecyclerView(context);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(-1, -2);
        params.topMargin = toolbar.getSize();
        recyclerView.setLayoutParams(params);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptor);
        recyclerView.setMinimumHeight((int) (dimen[1] - toolbar.getSize()));
        return recyclerView;
    }

    private View toolbar() {
        toolbar = new AppToolbar(context, true, "تازه های پزشکی", true);
        toolbar.setId(TOOLBAR_ID);
        return toolbar;
    }

    public void update(Blog.Post[] objects) {
        blogList.clear();
        Collections.addAll(blogList, objects);
        adaptor.notifyDataSetChanged();
    }
}
