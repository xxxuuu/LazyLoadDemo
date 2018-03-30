package com.xu.lazyloaddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 抽象封装的懒加载Fragment
 */
public abstract class AbsLazyFragment extends Fragment
{
    private boolean isPrepareView = false;  //用来确定View是否创建完成
    private boolean isInitData = false;     //数据是否加载完成
    private boolean isVisibleToUser = false;//Fragment是否可见

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        isPrepareView = true;
    }

    /**
     * 由子类实现具体的加载数据方法
     */
    abstract void initData();

    /**
     * 懒加载
     */
    private void lazyInitData()
    {
        //全部符合条件才进行加载
        if(!isInitData && isPrepareView && isVisibleToUser)
        {
            isInitData = true;
            initData();
        }
    }

    /**
     * Fragment可见状态变化是该方法被调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyInitData();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        lazyInitData();
    }

    @Override
    public void onDestroy()
    {
        //Fragment给回收后需重新加载
        isInitData = false;
        isPrepareView = false;
        super.onDestroy();
    }

    @Override
    public void onDestroyView()
    {
        //Fragment给回收后需重新加载
        isInitData = false;
        isPrepareView = false;
        super.onDestroyView();
    }
}
