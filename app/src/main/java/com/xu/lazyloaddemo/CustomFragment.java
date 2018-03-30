package com.xu.lazyloaddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CustomFragment extends AbsLazyFragment implements SwipeRefreshLayout.OnRefreshListener
{
    private SwipeRefreshLayout sl;
    private TextView tv;

    private int uid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        sl = (SwipeRefreshLayout)view.findViewById(R.id.fragment_sl);
        tv = (TextView)view.findViewById(R.id.fragment_tv);

        sl.setOnRefreshListener(this);
        sl.setRefreshing(false);

        return view;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }


    @Override
    public void onRefresh()
    {
        initData();
    }


    /**
     * 重写加载数据方法
     */
    @Override
    void initData()
    {
        tv.setText("");
        sl.setRefreshing(true);
        sl.postDelayed(new Runnable()   //模拟耗时操作
        {
            @Override
            public void run()
            {
                tv.setText("fragment" + getUid());
                sl.setRefreshing(false);
            }
        }, 2000);
    }

}
