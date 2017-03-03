package ir.gooble.clinic.adaptor;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ir.gooble.clinic.activity.DetailActivity;

public class DetailAdaptor extends PagerAdapter {
    private DetailActivity context;
    private float[] dimen;

    private String[] LIST = new String[]{
            "درباره دکتر"
            , "تخصص ها"
            , "مدارک علمی"
    };

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

    public DetailAdaptor(DetailActivity context, float[] dimen) {
        this.context = context;
        this.dimen = dimen;
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
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(Color.WHITE);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1,-1));
        return layout;
    }
}
