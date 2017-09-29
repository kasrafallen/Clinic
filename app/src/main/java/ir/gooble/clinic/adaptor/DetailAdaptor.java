package ir.gooble.clinic.adaptor;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.DetailActivity;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class DetailAdaptor extends PagerAdapter {

    private DetailActivity context;
    private Doctor doctor;
    private float[] dimen;

    private String[] LIST = new String[]{
            "درباره دکتر"
            , "تخصص ها"
            , "مدارک علمی"
    };
    private int margin;
    private int small;
    private int line;

    public DetailAdaptor(DetailActivity context, float[] dimen, Doctor doctor) {
        this.context = context;
        this.dimen = dimen;
        this.doctor = doctor;
        this.margin = Util.toPx(20, context);
        this.small = Util.toPx(10, context);
        this.line = Util.toPx(1, context) / 2;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = createView(position);
        container.addView(view, 0);
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return LIST[position];
    }

    @Override
    public int getCount() {
        return LIST.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private View createView(int position) {
        ScrollView scrollView = new ScrollView(context);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        ArrayList<String> list = new ArrayList<>();
        switch (position) {
            case 0:
                list.add(doctor.getAbout());
                break;
            case 1:
                list.addAll(doctor.getExpertiseList());
                break;
            case 2:
                list.addAll(doctor.getDegreeList());
                break;
        }
        for (String data : list) {
            layout.addView(field(data));
            if (position != 0) {
                layout.addView(line());
            }
        }

        scrollView.addView(layout);
        return scrollView;
    }

    private View field(String data) {
        AppText text = new AppText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(margin, small, margin, small);
        text.setLayoutParams(params);
        text.setGravity(Gravity.RIGHT);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        text.setText(data);
        return text;
    }

    private View line() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, line);
        view.setLayoutParams(params);
        view.setBackgroundResource(R.color.toolbar);
        return view;
    }
}
