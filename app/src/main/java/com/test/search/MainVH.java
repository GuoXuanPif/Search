package com.test.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @auther : GuoXuan
 * @since : 2018/2/14 0014
 */
public class MainVH extends RecyclerView.ViewHolder {

    public MainVH(View itemView) {
        super(itemView);
    }

    public TextView getTv() {
        return itemView.findViewById(R.id.main_item_tv);
    }

}
