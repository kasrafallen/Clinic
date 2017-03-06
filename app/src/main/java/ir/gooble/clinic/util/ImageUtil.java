package ir.gooble.clinic.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import ir.gooble.clinic.R;

public class ImageUtil {
    private static final int CAMERA_REQUEST_CODE = 259;
    private static final int GALLERY_REQUEST_CODE = 359;

    private static final String SUFFIX = ".jpg";
    private static String current_path;

    public interface ResultInterface {
        void onResult(String path);
    }

    public static void pickImage(final Activity context) {
        current_path = null;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("عکس پروفایل خود را انتخاب کنید.");
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "بازگشت", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "دوربین", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestFromCamera(context);
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "گالری", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestFromGallery(context);
            }
        });
        DialogUtil.show(dialog, context);
    }

    private static void requestFromGallery(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(intent, ImageUtil.GALLERY_REQUEST_CODE);
    }

    private static void requestFromCamera(Activity context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            File photoFile = createImageFile(context);
            if (photoFile != null) {
                current_path = photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                context.startActivityForResult(takePictureIntent, ImageUtil.CAMERA_REQUEST_CODE);
            } else {
                Toast.makeText(context, "اجازه دسترسی به دوربین ندارید", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static File createImageFile(Activity context) {
        File dir = new File(Environment.getExternalStorageDirectory(), context.getResources().getString(R.string.app_name));
        boolean isValid = true;
        if (!dir.exists()) {
            isValid = dir.mkdir();
        }
        if (isValid) {
            try {
                return File.createTempFile(String.valueOf(Calendar.getInstance().getTimeInMillis()), SUFFIX, dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String getPath(Uri uri, Activity context) {
        String result = null;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor == null) {
                result = uri.getPath();
            } else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return result;
    }

    public static void handle(Activity context, int requestCode, int resultCode, Intent data, ResultInterface result) {
        if (requestCode != GALLERY_REQUEST_CODE && requestCode != CAMERA_REQUEST_CODE) {
            return;
        }
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && current_path != null) {
                File image = new File(current_path);
                if (image.exists()) {
                    result.onResult(image.getAbsolutePath());
                }
            }
        } else {
            if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                String path = getPath(data.getData(), context);
                if (path != null) {
                    result.onResult(path);
                }
            }
        }
    }
}
