package ir.gooble.clinic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitRegister;
import ir.gooble.clinic.instance.RegistryInstance;
import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.RegistryModel;
import ir.gooble.clinic.util.ImageUtil;
import ir.gooble.clinic.util.PermissionUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private InitRegister initRegister;
    public RegistryModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = RegistryInstance.getDocuments(this);
        this.initRegister = (InitRegister) setContentView(this);

        if (UserInstance.isEmpty(this)) {
            SignActivity.start(new UserInstance.SignResult() {
                @Override
                public void onDone() {
                    show();
                }

                @Override
                public void onDismiss() {
                    finish();
                }
            }, this);
        } else {
            show();
        }
    }

    private void show() {
        initRegister.layout.setVisibility(View.VISIBLE);
        initRegister.function.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            if (v.getTag().equals(String.valueOf(true))) {
                onBackPressed();
            } else {
                save(true);
            }
        }
    }

    private void save(boolean withAll) {
        if (withAll) {
            if (initRegister.group.getCheckedRadioButtonId() == InitRegister.FEMALE_ID) {
                model.setUser_sex(RegistryModel.SEX_FEMALE);
            } else {
                model.setUser_sex(RegistryModel.SEX_MALE);
            }
            Toast.makeText(this, "پرونده به روز رسانی شد", Toast.LENGTH_SHORT).show();
        }
        RegistryInstance.setDocuments(this, model);
    }

    public void requestImage() {
        PermissionUtil.requestPermission(new String[]{android.Manifest.permission.CAMERA
                , android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, this, new PermissionUtil.PermissionCallBack() {
            @Override
            public void onGranted() {
                ImageUtil.pickImage(RegisterActivity.this);
            }

            @Override
            public void onDenied() {
                PermissionUtil.showPermissionDialog(new String[]{android.Manifest.permission.CAMERA
                        , android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, RegisterActivity.this, this);
            }

            @Override
            public void onDismissed() {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SignActivity.handle(requestCode, resultCode);
        ImageUtil.handle(this, requestCode, resultCode, data, new ImageUtil.ResultInterface() {
            @Override
            public void onResult(String path) {
                model.setUser_image_path(path);
                save(false);
                initRegister.updateImage();
            }
        });
    }
}
