package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitBlog;
import ir.gooble.clinic.instance.BlogInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.model.Blog;

public class BlogActivity extends BaseActivity {

    private InitBlog initBlog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBlog = (InitBlog) setContentView(this);
        BlogInstance.getBlog(this, new InstanceResult() {
            @Override
            public void onResult(Object[] objects) {
                initBlog.update((Blog.Post[]) objects);
            }
        });
    }
}
