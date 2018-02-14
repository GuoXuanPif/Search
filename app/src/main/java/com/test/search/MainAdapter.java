package com.test.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @auther : GuoXuan
 * @since : 2018/2/14 0014
 */
public class MainAdapter extends RecyclerView.Adapter<MainVH> {

    private int mCount;

    public void setCount(int count) {
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.layout_main_item, null);
        return new MainVH(view);
    }

    @Override
    public void onBindViewHolder(MainVH holder, int position) {
        holder.getTv().setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
