package ir.gooble.clinic.adaptor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.DetailActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.model.Doctor;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class DoctorAdaptor extends RecyclerView.Adapter<DoctorAdaptor.Holder> implements View.OnClickListener {
    private static final int TEXT_ID = +124578;
    private static final int IMAGE_ID = +326598;
    private static final int CLICKABLE_ID = +42874487;

    private BaseActivity context;
    private ArrayList<Doctor> doctors;

    private int height;
    private int margin;
    private int line;
    private int padding;
    private int text;

    private int small_margin;

    public DoctorAdaptor(BaseActivity context, float[] dimen, ArrayList<Doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
        this.margin = Util.toPx(10, context);
        this.height = (int) (dimen[0] / 2);
        this.padding = (int) (dimen[0] / 30);
        this.line = Util.toPx(2, context);
        this.text = Util.toPx(70, context);
        this.small_margin = Util.toPx(8, context);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        View clickable;
        AppText text;
        ImageView image;

        public Holder(View itemView) {
            super(itemView);
            this.clickable = itemView.findViewById(CLICKABLE_ID);
            this.image = (ImageView) itemView.findViewById(IMAGE_ID);
            this.text = (AppText) itemView.findViewById(TEXT_ID);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(createView(viewType));
    }

    private View createView(int viewType) {
        CardView cardView = new CardView(context);
        cardView.setCardElevation(3);
        cardView.setRadius(0);
        cardView.setCardBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, height + text);

        if (viewType != 2 && viewType != 1) {
            params.bottomMargin = margin;
        } else {
            params.bottomMargin = margin;
            params.topMargin = margin;
        }

        if (viewType % 2 == 0) {
            params.leftMargin = padding / 2;
            params.rightMargin = padding;
        } else {
            params.leftMargin = padding;
            params.rightMargin = padding / 2;
        }

        cardView.setLayoutParams(params);
        cardView.addView(image());
        cardView.addView(text());
        cardView.addView(clickable());
        return cardView;
    }

    private View image() {
        ImageView layout = new ImageView(context);
        layout.setId(IMAGE_ID);
        layout.setLayoutParams(new CardView.LayoutParams(-1, height));
        layout.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return layout;
    }

    private View text() {
        RelativeLayout layout = new RelativeLayout(context);
        CardView.LayoutParams params = new CardView.LayoutParams(-1, -2);
        params.topMargin = height;
        layout.setLayoutParams(params);

        AppText text = new AppText(context);
        text.setId(TEXT_ID);
        text.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        text.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        text.setTextSize(1, 10);
        text.setTextColor(Color.GRAY);
        text.setMaxLines(3);
        text.setEllipsize(TextUtils.TruncateAt.END);

        View line = new View(context);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-1, this.line);
        param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        line.setLayoutParams(param);
        line.setBackgroundResource(R.color.base);

        layout.addView(text);
        layout.addView(line);

        text.setPadding(small_margin, 0, small_margin, 0);
        return layout;
    }

    private View clickable() {
        View view = new View(context);
        view.setId(CLICKABLE_ID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(2);
        }
        view.setLayoutParams(new CardView.LayoutParams(-1, -1));
        view.setOnClickListener(this);
        Util.setBackground(view, context);
        return view;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.image.setImageResource(R.mipmap.y_def_doctor_profile_2);
        holder.clickable.setTag(doctors.get(position));
        SpannableString string = new SpannableString(getName(position) + "\n" + getDescription(position));
        string.setSpan(new AbsoluteSizeSpan(13, true), 0, string.toString().indexOf("\n"), 0);
        string.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, string.toString().indexOf("\n"), 0);
        holder.text.setText(string);
    }

    private String getDescription(int position) {
        return doctors.get(position).getExpertiseString();
    }

    private String getName(int position) {
        return doctors.get(position).getName();
    }

    @Override
    public int getItemViewType(int position) {
        return position + 1;
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    @Override
    public void onClick(View view) {
        context.redirect(DetailActivity.class, new Gson().toJson(view.getTag()));
    }
}
