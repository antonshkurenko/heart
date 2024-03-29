package me.cullycross.heart.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.cullycross.heart.R;

/**
 * Created by cullycross on 7/9/15.
 *
 * to represent name-value
 */
public class PasswordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOTER = 0;
    private static final int TYPE_ITEM = 1;

    private String [] mDataset;

    private Context mContext;

    public PasswordsAdapter(String [] dataset, Context ctx) {
        super();
        mDataset = dataset;
        mContext = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.name_pass_list_view_item, viewGroup, false);
            return new VHItem(v, mContext);
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.name_pass_list_view_footer, viewGroup, false);
            return new VHFooter(v);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof VHItem) {
            ((VHItem) viewHolder).mTextView.setText(mDataset[position] + " " + position);
            //cast holder to VHItem and set data
        } else if (viewHolder instanceof VHFooter) {
            //cast holder to VHHeader and set data for header.
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position))
            return TYPE_FOOTER;

        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        return mDataset.length + 1;
    }

    public static class VHItem extends RecyclerView.ViewHolder {

        @Bind(R.id.text_view)
        public TextView mTextView;

        public VHItem(View itemView, Context ctx) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class VHFooter extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public VHFooter(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView;
        }
    }
}
