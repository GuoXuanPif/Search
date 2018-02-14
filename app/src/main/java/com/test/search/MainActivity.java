package com.test.search;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private RecyclerView mRv;
    private MainAdapter mAdapter;
    private RecyclerView.AdapterDataObserver mObserver;
    private View mV;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        mRv = findViewById(R.id.main_rv);
        mV = findViewById(R.id.main_v);

        findViewById(R.id.main_bt).setOnClickListener(this);

        mToast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);

        mAdapter = new MainAdapter();
        mObserver = new DataObserverImpl();
        mAdapter.registerAdapterDataObserver(mObserver);
        mRv.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRv.setLayoutManager(manager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_bt: {
                int count = mAdapter.getCount();
                if (count < 10) {
                    count++;
                } else {
                    mToast.setText("重置");
                    mToast.show();
                    count = 0;
                }
                mAdapter.setCount(count);
                mAdapter.notifyDataSetChanged();
            }
            break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mAdapter.unregisterAdapterDataObserver(mObserver);
    }

    private class DataObserverImpl extends RecyclerView.AdapterDataObserver {

        @Override
        public void onChanged() {
            mRv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    ViewGroup.LayoutParams params = mV.getLayoutParams();
                    params.width = mRv.getMeasuredWidth();
                    mV.setLayoutParams(params);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mRv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        mRv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }

            });

        }

    }
}
