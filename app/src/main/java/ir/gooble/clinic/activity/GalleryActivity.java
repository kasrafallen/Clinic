package ir.gooble.clinic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitGallery;
import ir.gooble.clinic.instance.GalleryInstance;
import ir.gooble.clinic.instance.InstanceResult;
import ir.gooble.clinic.model.Gallery;

public class GalleryActivity extends BaseActivity {

    private InitGallery initGallery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGallery = (InitGallery) setContentView(this);
        GalleryInstance.getPictures(this, new InstanceResult() {
            @Override
            public void onResult(Object[] objects) {
                initGallery.setAdaptor((Gallery.Picture[]) objects);
            }
        });
    }
}
