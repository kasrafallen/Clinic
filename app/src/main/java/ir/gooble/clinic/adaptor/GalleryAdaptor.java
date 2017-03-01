package ir.gooble.clinic.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.gooble.clinic.R;
import ir.gooble.clinic.application.BaseActivity;
import ir.gooble.clinic.util.Util;

public class GalleryAdaptor extends RecyclerView.Adapter<GalleryAdaptor.Holder> {
    private BaseActivity context;
    private int margin;

    private static final int IMAGE_ID = +84877744;
    private static final int[] DEFAULT_LIST = new int[]{
            R.mipmap.test_1
            , R.mipmap.test_2
            , R.mipmap.test_3
            , R.mipmap.test_4
    };

    public GalleryAdaptor(BaseActivity context, float[] dimen) {
        this.context = context;
        this.margin = Util.toPx(5, context);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(IMAGE_ID);
        }
    }

    @Override
    public GalleryAdaptor.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(createView(viewType));
    }

    private View createView(int position) {
        position++;

        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        if (position == 1 || position == 2) {
            params.topMargin = margin;
        }
        params.bottomMargin = margin;
        if (position % 2 == 0) {
            params.rightMargin = margin;
            params.leftMargin = margin / 2;
        } else {
            params.leftMargin = margin;
            params.rightMargin = margin / 2;
        }
        layout.setLayoutParams(params);

        ImageView imageView = new ImageView(context);
        imageView.setId(IMAGE_ID);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-1, -2);
        param.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(param);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        layout.addView(imageView);
        return layout;
    }

    @Override
    public void onBindViewHolder(GalleryAdaptor.Holder holder, int position) {
        holder.imageView.setImageResource(DEFAULT_LIST[position]);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return DEFAULT_LIST.length;
    }
}
