package ir.gooble.clinic.util;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import ir.gooble.clinic.R;
import ir.gooble.clinic.application.BaseActivity;

public class PermissionUtil {

    private static final int REQUEST_PERMISSION_CODE = 202;

    public interface PermissionCallBack {
        void onGranted();

        void onDenied();

        void onDismissed();
    }

    public static void requestPermission(String permissions[], BaseActivity context, PermissionCallBack callBack) {
        boolean flag = checkPermission(permissions, context);
        if (flag) {
            callBack.onGranted();
        } else {
            for (String perm : permissions) {
                if (perm.equals(Manifest.permission.WRITE_SETTINGS)) {
                    showPermissionDialog(permissions, context, callBack);
                    return;
                }
            }
            ActivityCompat.requestPermissions(context, permissions, REQUEST_PERMISSION_CODE);
        }
    }

    public static boolean checkPermission(String[] permissions, BaseActivity context) {
        boolean flag = true;
        if (Build.VERSION.SDK_INT >= 23) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static void showPermissionDialog(final String permission[], final BaseActivity context, final PermissionCallBack callBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher).setMessage("برای استفاده از این قسمت به این دسترسی اجازه دهید")
                .setTitle(context.getResources().getString(R.string.app_name)).setPositiveButton("دسترسی ها", null);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "دوباره", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestPermission(permission, context, callBack);
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "بازگشت", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (callBack != null) {
                    callBack.onDismissed();
                }
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redirect(context);
                    }
                });
            }
        });
        builder.show();
    }

    private static void redirect(BaseActivity context) {
        try {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, BaseActivity context, PermissionCallBack callBack) {
        if (callBack == null || requestCode != REQUEST_PERMISSION_CODE) {
            return;
        }
        if (checkPermission(permissions, context)) {
            callBack.onGranted();
        } else {
            callBack.onDenied();
        }
    }
}
