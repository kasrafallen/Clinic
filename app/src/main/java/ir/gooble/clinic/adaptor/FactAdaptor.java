package ir.gooble.clinic.adaptor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

import ir.gooble.clinic.R;
import ir.gooble.clinic.activity.DescriptionActivity;
import ir.gooble.clinic.activity.DetailActivity;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.model.FactModel;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppButton;
import ir.gooble.clinic.view.AppText;

public class FactAdaptor extends RecyclerView.Adapter<FactAdaptor.Holder> {
    private static final int NEXT_ID = +2658846;
    private static final int TEXT_ID = +2658843;
    private static final int DATE_ID = +2658841;
    private static final int IMAGE_ID = +2658845;
    private static final int SHARE_ID = +25489633;
    private static final int DESCRIPTION_ID = +32165848;

    private final ArrayList<FactModel> DEFAULT_LIST = new ArrayList<>();

    private BaseActivity context;
    private float[] dimen;

    private int radius;
    private int height;
    private int margin;
    private int image;
    private int shared;

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        AppText description;
        AppText text;
        AppText date;
        AppText next;
        View share;

        public Holder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(IMAGE_ID);
            description = (AppText) itemView.findViewById(DESCRIPTION_ID);
            text = (AppText) itemView.findViewById(TEXT_ID);
            date = (AppText) itemView.findViewById(DATE_ID);
            next = (AppText) itemView.findViewById(NEXT_ID);
            share = itemView.findViewById(SHARE_ID);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return DEFAULT_LIST.size();
    }

    public FactAdaptor(BaseActivity context, float[] dimen) {
        this.context = context;
        this.dimen = dimen;
        this.radius = Util.toPx(4, context);
        this.height = Util.toPx(260, context);
        this.margin = Util.toPx(15, context);

        this.image = Util.toPx(130, context);
        this.shared = Util.getToolbarSize(context);

        FactModel model = new FactModel();
        model.setResource(R.mipmap.test_fact);
        model.setDate("1 روز قبل");
        model.setTitle("دانستنی های جالب در مورد چشم");
        model.setDescription("آیا می دانید که اشک انسان از چه چیزی درست شده است؟ در حقیقت اشک انسان از ترکیب ۳ ماده آب، چربی و مخاط تشکیل شده است و همچنین دارای مواد ضد عفونی کننده نیز است. در صورتی که بدن انسان هر کدام از این ۳ ماده را کم داشته باشد، بدن ناچار است با فشار به سایر بخش ها این مواد را تولید کرده و اشک که محصول نهایی ترکیب آنها است را تولید کند. در غیر این صورت چشم انسان خشک شده و از بین می رود.");
        DEFAULT_LIST.add(model);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(createView(viewType));
    }

    private View createView(int viewType) {
        CardView cardView = new CardView(context);
        cardView.setCardElevation(3);
        cardView.setRadius(radius);
        cardView.setCardBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, height);
        if (viewType == 1) {
            params.topMargin = margin;
        }
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        cardView.setLayoutParams(params);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new CardView.LayoutParams(-1, -1));
        layout.addView(image());
        layout.addView(top());
        layout.addView(description());
        layout.addView(function());

        cardView.addView(layout);
        return cardView;
    }

    private View top() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));

        AppText date = new AppText(context);
        date.setId(DATE_ID);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-2, -2);
        param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        param.addRule(RelativeLayout.CENTER_VERTICAL);
        param.setMargins(margin / 2, margin / 2, margin / 2, margin / 2);
        date.setLayoutParams(param);
        date.setSingleLine();
        date.setTextSize(1, 10);
        date.setTextColor(Color.LTGRAY);

        AppText text = new AppText(context);
        text.setId(TEXT_ID);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        params.addRule(RelativeLayout.RIGHT_OF, DATE_ID);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.rightMargin = margin / 2;
        text.setLayoutParams(params);
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 13);
        text.setSingleLine();
        text.setEllipsize(TextUtils.TruncateAt.END);

        layout.addView(date);
        layout.addView(text);
        return layout;
    }

    private View function() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1f));

        AppButton share = new AppButton(context);
        share.setId(SHARE_ID);
        share.changeRipple();
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(shared, shared);
        param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        param.addRule(RelativeLayout.CENTER_VERTICAL);
        share.setLayoutParams(param);
        share.setBackgroundResource(R.drawable.ic_share_black_48dp);
        share.setScaleX(0.8f);
        share.setScaleY(0.8f);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() != null && v.getTag() instanceof FactModel) {
                    shareFact((FactModel) v.getTag());
                }
            }
        });

        AppText next = new AppText(context);
        next.setId(NEXT_ID);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.leftMargin = margin / 2;
        next.setLayoutParams(params);
        next.setSingleLine();
        next.setTextSize(1, 12);
        next.setGravity(Gravity.CENTER);
        next.setText("ادامه مطلب");
        next.setTextColor(Color.WHITE);
        next.setBackgroundResource(R.drawable.oval_background);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() != null && v.getTag() instanceof FactModel) {
                    openFact((FactModel) v.getTag());
                }
            }
        });

        layout.addView(next);
        layout.addView(share);
        return layout;
    }

    private View description() {
        AppText text = new AppText(context);
        text.setId(DESCRIPTION_ID);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(margin / 2, 0, margin / 2, 0);
        text.setLayoutParams(params);
        text.setMaxLines(2);
        text.setTextColor(Color.GRAY);
        text.setGravity(Gravity.RIGHT);
        text.setEllipsize(TextUtils.TruncateAt.END);
        return text;
    }

    private View image() {
        ImageView imageView = new ImageView(context);
        imageView.setId(IMAGE_ID);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, image));
        return imageView;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        FactModel model = DEFAULT_LIST.get(position);

        holder.share.setTag(model);
        holder.next.setTag(model);
        holder.text.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.date.setText(model.getDate());
        holder.imageView.setImageResource(model.getResource());
    }

    private void shareFact(FactModel tag) {
        context.share(tag);
    }

    private void openFact(FactModel tag) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setAction(new Gson().toJson(tag));
        context.startActivity(intent);
    }
}
