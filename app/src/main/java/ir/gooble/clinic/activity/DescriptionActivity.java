package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitDescription;
import ir.gooble.clinic.model.Blog;

public class DescriptionActivity extends BaseActivity {

    private InitDescription initDescription;
    public Blog.Post model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new Gson().fromJson(getIntent().getAction(), Blog.Post.class);
        initDescription = (InitDescription) setContentView(this);
    }
}
