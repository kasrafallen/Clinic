package ir.gooble.clinic.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitRegister;
import ir.gooble.clinic.instance.RegistryInstance;
import ir.gooble.clinic.model.RegistryModel;
import ir.gooble.clinic.util.DialogUtil;
import ir.gooble.clinic.util.ImageUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private InitRegister initRegister;
    public RegistryModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = RegistryInstance.getDocuments(this);
        initRegister = (InitRegister) setContentView(this);
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
        ImageUtil.pickImage(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageUtil.handle(this, requestCode, resultCode, data, new ImageUtil.ResultInterface() {
            @Override
            public void onResult(String path) {
                Log.d("TEST_IMAGE", "onResult() returned: " + path);
                model.setUser_image_path(path);
                save(false);
                initRegister.updateImage();
            }
        });
    }
}
