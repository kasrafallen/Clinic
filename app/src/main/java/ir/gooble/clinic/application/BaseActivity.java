package ir.gooble.clinic.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import java.util.concurrent.TimeUnit;

import ir.gooble.clinic.activity.ClinicActivity;
import ir.gooble.clinic.activity.DoctorActivity;
import ir.gooble.clinic.activity.FactActivity;
import ir.gooble.clinic.activity.GalleryActivity;
import ir.gooble.clinic.activity.LaunchActivity;
import ir.gooble.clinic.activity.RegisterActivity;
import ir.gooble.clinic.activity.ReserveActivity;
import ir.gooble.clinic.init.InitClinic;
import ir.gooble.clinic.init.InitDescription;
import ir.gooble.clinic.init.InitDetail;
import ir.gooble.clinic.init.InitDoctor;
import ir.gooble.clinic.init.InitDrawer;
import ir.gooble.clinic.init.InitFact;
import ir.gooble.clinic.init.InitGallery;
import ir.gooble.clinic.init.InitMain;
import ir.gooble.clinic.init.InitRegister;
import ir.gooble.clinic.init.InitReserve;
import ir.gooble.clinic.init.InitSign;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.instance.ClinicInstance;
import ir.gooble.clinic.instance.DoctorInstance;
import ir.gooble.clinic.instance.GalleryInstance;
import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.Address;
import ir.gooble.clinic.model.FactModel;
import ir.gooble.clinic.util.PromptUtil;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppDrawerLayout;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    public static final long UPDATE_RATE = TimeUnit.DAYS.toMillis(0);

    private static final String EXIT = "BaseActivity.EXIT";
    public static final String UPDATE = "BaseActivity.UPDATE";

    public AppDrawerLayout drawer;
    public PromptUtil prompt;
    private BaseInit init;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    private BroadcastReceiver user_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUser();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prompt = new PromptUtil(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(EXIT));
        LocalBroadcastManager.getInstance(this).registerReceiver(user_receiver, new IntentFilter(UPDATE));
    }

    public BaseInit setContentView(Object object) {
        if (object != null && object instanceof BaseActivity) {
            init = selectView(((BaseActivity) object).getClass().getSimpleName(), object);
            if (init != null) {
                super.setContentView(init.getView());
                return init;
            }
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(user_receiver);
        super.onDestroy();
    }

    private BaseInit selectView(String simpleName, Object object) {
        switch (simpleName) {
            case "MainActivity":
                return new InitMain((BaseActivity) object);
            case "ClinicActivity":
                return new InitClinic((BaseActivity) object);
            case "GalleryActivity":
                return new InitGallery((BaseActivity) object);
            case "DoctorActivity":
                return new InitDoctor((BaseActivity) object);
            case "DetailActivity":
                return new InitDetail((BaseActivity) object);
            case "FactActivity":
                return new InitFact((BaseActivity) object);
            case "DescriptionActivity":
                return new InitDescription((BaseActivity) object);
            case "RegisterActivity":
                return new InitRegister((BaseActivity) object);
            case "ReserveActivity":
                return new InitReserve((BaseActivity) object);
            case "SignActivity":
                return new InitSign((BaseActivity) object);
        }
        return null;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void openDrawer() {
        if (drawer != null && !drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.openDrawer(Gravity.RIGHT);
        }
    }

    public void redirect(Class className) {
        redirect(className, null);
    }

    public void redirect(Class className, String action) {
        redirect(className, action, 0);
    }

    public void redirect(Class className, String action, int requestCode) {
        Intent intent = new Intent(this, className);
        intent.setAction(action);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(intent, requestCode);
    }

    public void run(String tag, final BaseActivity context, View view, final String data) {
        Intent intent;
        switch (tag) {
            case Attributes.FIELD_ABOUT_CLINIC:
                if (context instanceof ClinicActivity) {
                    drawer.closeDrawer(Gravity.RIGHT);
                    return;
                }
                intent = new Intent(context, ClinicActivity.class);
                start(intent, view, data);
                break;
            case Attributes.FIELD_GALLERY:
                if (context instanceof GalleryActivity) {
                    drawer.closeDrawer(Gravity.RIGHT);
                    return;
                }
                intent = new Intent(context, GalleryActivity.class);
                start(intent, view, data);
                break;
            case Attributes.FIELD_ABOUT_DOCTORS:
                if (context instanceof DoctorActivity) {
                    drawer.closeDrawer(Gravity.RIGHT);
                    return;
                }
                intent = new Intent(context, DoctorActivity.class);
                start(intent, view, data);
                break;
            case Attributes.FIELD_NEW_FACTS:
                if (context instanceof FactActivity) {
                    drawer.closeDrawer(Gravity.RIGHT);
                    return;
                }
                intent = new Intent(context, FactActivity.class);
                start(intent, view, data);
                break;
            case Attributes.FIELD_REGISTER:
                if (context instanceof RegisterActivity) {
                    drawer.closeDrawer(Gravity.RIGHT);
                    return;
                }
                intent = new Intent(context, RegisterActivity.class);
                start(intent, view, data);
                break;
            case Attributes.FIELD_RESERVE:
                if (context instanceof ReserveActivity) {
                    drawer.closeDrawer(Gravity.RIGHT);
                    return;
                }
                intent = new Intent(context, ReserveActivity.class);
                start(intent, view, data);
                break;
            case InitDrawer.LOGOUT:
                logOut();
                return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (context != null && drawer != null) {
                    drawer.closeDrawer(Gravity.RIGHT);
                }
            }
        }, 500);
    }

    private void logOut() {
        Util.get(this)
                .edit()
                .remove(GalleryInstance.class.getSimpleName())
                .remove(DoctorInstance.class.getSimpleName())
                .remove(ClinicInstance.class.getSimpleName())
                .remove(UserInstance.class.getSimpleName())
                .apply();
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(EXIT));
        LaunchActivity.start(this);
    }

    private void start(Intent intent, View view, String data) {
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openDial(String number) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openLink(String url) {
        try {
            if (!url.startsWith("http")) {
                url = "http://" + url;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMail(String email) {
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
            startActivity(Intent.createChooser(emailIntent, "مکاتبه با ایمیل"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMap(Address address) {
        try {
            double lat = address.getLat();
            double lng = address.getLong();
            float zoom = 16f;
            String uriBegin = "geo:" + lat + "," + lng;
            String query = lat + "," + lng + "(" + address.getAddress() + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=" + zoom;
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void share(FactModel tag) {

    }

    public void share() {

    }

    private void updateUser() {
        if (drawer != null && init != null && init.drawer != null) {
            init.drawer.update();
        }
    }
}
