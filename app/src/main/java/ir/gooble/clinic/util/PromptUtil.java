package ir.gooble.clinic.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

import ir.gooble.clinic.activity.LaunchActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.oracle.CallBack;

public class PromptUtil {

    private ArrayList<ProgressDialog> dialogs = new ArrayList<>();
    private Activity context;
    private Dialog dialog;

    private Mode current_mode;

    public enum Mode {
        WAITING {
            @Override
            public String toString() {
                return "در حال برقراری ارتباط";
            }
        },
        INTERNET {
            @Override
            public String toString() {
                return "ارتباط شما برقرار نیست";
            }
        },
        ERROR {
            @Override
            public String toString() {
                return "اشکال در برقراری ارتباط, دوباره امتحان کنید";
            }
        }
    }

    public PromptUtil(Activity context) {
        this.context = context;
    }

    public void progress() {
        custom(Mode.WAITING, null, null, false);
    }

    public void internet(final CallBack callBack) {
        custom(Mode.INTERNET, callBack, null, true);
    }

    public void error(final CallBack callBack, String input) {
        custom(Mode.ERROR, callBack, input, true);
    }

    private void custom(Mode mode, final CallBack callBack, String input, boolean cancelable) {
        custom(mode, callBack, input, cancelable, null, null);
    }

    public void custom(Mode mode, final CallBack callBack, String input, boolean cancelable, String button
            , DialogInterface.OnClickListener listener) {
        hide(mode);
        boolean isWaiting = current_mode != null && mode != null && current_mode == mode && mode == Mode.WAITING && dialog != null && dialog.isShowing() && dialog instanceof ProgressDialog;
        current_mode = mode;
        if (current_mode == null) {
            return;
        }
        if (mode == Mode.WAITING) {
            ProgressDialog dialog = new ProgressDialog(context);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            if (input != null) {
                dialog.setMessage(input);
            } else {
                dialog.setMessage(mode.toString());
            }
            if (button != null && listener != null) {
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, button, listener);
            }
            if (isWaiting) {
                PromptUtil.this.dialogs.add((ProgressDialog) PromptUtil.this.dialog);
            }
            PromptUtil.this.dialog = dialog;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            if (input != null) {
                if (button != null && listener != null) {
                    builder.setMessage(input);
                } else {
                    builder.setMessage(input);
                }
            } else {
                builder.setMessage(mode.toString());
            }
            if (!(context instanceof LaunchActivity)) {
                if (button != null && listener != null) {
                    builder.setNegativeButton(button, listener);
                } else {
                    builder.setNegativeButton("بازگشت", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
            }
            builder.setPositiveButton("تلاش مجدد", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (callBack != null) {
                        callBack.onClick();
                    }
                }
            });
            PromptUtil.this.dialog = builder.create();
        }
        if (context instanceof LaunchActivity) {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        } else {
            dialog.setCancelable(cancelable);
            dialog.setCanceledOnTouchOutside(cancelable);
        }
        DialogUtil.show(dialog, context);
    }

    public boolean isOpen(Mode mode) {
        return current_mode != null
                && current_mode == mode
                && dialog != null
                && dialog.isShowing();
    }

    private void hide(Mode mode) {
        if (dialog != null && dialog.isShowing()) {
            if (current_mode != null && mode != null && current_mode == mode) {
                return;
            }
            dialog.dismiss();
        }
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        for (Dialog dialog : dialogs) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        dialogs.clear();
    }

    public static void show(String input, BaseActivity context) {
        show(input, context, null, null);
    }

    public static void show(String input, BaseActivity context
            , String button, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(input).setCancelable(true);
        builder.setNegativeButton("بازگشت", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if (button != null && listener != null) {
            builder.setPositiveButton(button, listener);
        }
        DialogUtil.show(builder.create(), context);
    }
}