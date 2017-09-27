package ir.gooble.clinic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.init.InitRegister;
import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.User;
import ir.gooble.clinic.oracle.Api;
import ir.gooble.clinic.oracle.CallBack;
import ir.gooble.clinic.oracle.Rest;
import ir.gooble.clinic.util.ImageUtil;
import ir.gooble.clinic.util.PermissionUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private InitRegister initRegister;
    public User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initRegister = (InitRegister) setContentView(this);
        if (UserInstance.isEmpty(this)) {
            SignActivity.start(new UserInstance.SignResult() {
                @Override
                public void onDone() {
                    user = UserInstance.getUser(RegisterActivity.this);
                    getRequest();
                }

                @Override
                public void onDismiss() {
                    finish();
                }
            }, this);
        } else {
            user = UserInstance.getUser(RegisterActivity.this);
            getRequest();
        }
    }

    private void fetch() {
        initRegister.fetch();
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            if (v.getTag().equals(String.valueOf(true))) {
                onBackPressed();
            } else {
                postRequest();
            }
        }
    }

    public void requestImage() {
        if (user == null) {
            return;
        }
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
                if (user != null) {
                    user.setImagePath(path);
                }
                User user = UserInstance.getUser(RegisterActivity.this);
                if (user == null) {
                    return;
                }
                user.setImagePath(path);
                UserInstance.setUser(RegisterActivity.this, user);
                initRegister.updateImage();
                LocalBroadcastManager.getInstance(RegisterActivity.this).sendBroadcast(new Intent(BaseActivity.UPDATE));
            }
        });
    }

    private void getRequest() {
        new Rest(this, Api.PROFILE_INFO).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                fetch();
            }

            @Override
            public void onError(String error) {
                fetch();
            }

            @Override
            public void onInternet() {
                fetch();
            }

            @Override
            public void onBefore() {
                prompt.progress();
            }

            @Override
            public void onClick() {
                getRequest();
            }
        });
    }

    private void postRequest() {
        if (user == null) {
            return;
        }
        new Rest(this, Api.PROFILE_POST).connect(new CallBack() {
            @Override
            public void onResponse(String response) {
                prompt.hide();
                UserInstance.setUser(RegisterActivity.this, user);
            }

            @Override
            public void onError(String error) {
                prompt.error(this, error);
            }

            @Override
            public void onInternet() {
                prompt.internet(this);
            }

            @Override
            public void onBefore() {
                prompt.progress();
            }

            @Override
            public void onClick() {
                postRequest();
            }
        }, user);
    }
}
