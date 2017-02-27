package ir.gooble.clinic.adaptor;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.gooble.clinic.R;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.instance.Attributes;
import ir.gooble.clinic.util.Util;
import ir.gooble.clinic.view.AppText;

public class MainAdaptor extends RecyclerView.Adapter<MainAdaptor.Holder> implements View.OnClickListener {
    private static final int TEXT_ID = +124578;
    private static final int LOGO_ID = +326598;
    private static final int CLICKABLE_ID = +42874487;

    private BaseActivity context;

    private int height;
    private int margin;
    private int line;
    private int padding;
    private int text;

    public MainAdaptor(BaseActivity context, float[] dimen) {
        this.context = context;
        this.margin = Util.toPx(10, context);
        this.height = (int) (dimen[0] / 2);
        this.padding = (int) (dimen[0] / 30);
        this.line = Util.toPx(2, context);
        this.text = Util.toPx(50, context);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        View clickable;
        AppText text;
        View logo;

        public Holder(View itemView) {
            super(itemView);
            this.clickable = itemView.findViewById(CLICKABLE_ID);
            this.logo = itemView.findViewById(LOGO_ID);
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, height);

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
        cardView.addView(logo());
        cardView.addView(text());
        cardView.addView(clickable());
        return cardView;
    }

    private View logo() {
        int size = (int) (1f * (height - text) / 3f);

        FrameLayout layout = new FrameLayout(context);
        CardView.LayoutParams params = new CardView.LayoutParams(-1, height - text);
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        layout.setLayoutParams(params);

        View logo = new View(context);
        logo.setId(LOGO_ID);
        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(size, size);
        param.gravity = Gravity.CENTER;
        logo.setLayoutParams(param);

        layout.addView(logo);
        return layout;
    }

    private View text() {
        RelativeLayout layout = new RelativeLayout(context);
        CardView.LayoutParams params = new CardView.LayoutParams(-1, text);
        params.gravity = Gravity.BOTTOM;
        layout.setLayoutParams(params);

        AppText text = new AppText(context);
        text.setId(TEXT_ID);
        text.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        text.setGravity(Gravity.CENTER);
        text.setSingleLine();
        text.setTextSize(1, 14);
        text.setTextColor(Color.DKGRAY);

        View line = new View(context);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-1, this.line);
        param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        line.setLayoutParams(param);
        line.setBackgroundResource(R.color.base);

        layout.addView(text);
        layout.addView(line);
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
        holder.text.setText(Attributes.MAIN_FIELDS[position]);
        holder.logo.setBackgroundResource(Attributes.getResource(Attributes.MAIN_FIELDS[position]));
        holder.clickable.setTag(Attributes.MAIN_FIELDS[position]);
    }

    @Override
    public int getItemViewType(int position) {
        return position + 1;
    }

    @Override
    public int getItemCount() {
        return Attributes.MAIN_FIELDS.length;
    }

    @Override
    public void onClick(View view) {
        context.run((String) view.getTag(), context, view);
    }
}
