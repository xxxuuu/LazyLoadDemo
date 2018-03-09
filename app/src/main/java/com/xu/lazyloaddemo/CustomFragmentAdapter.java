package com.xu.lazyloaddemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class CustomFragmentAdapter extends FragmentPagerAdapter
{
    private List<CustomFragment> fragments;

    public CustomFragmentAdapter(FragmentManager fm, List<CustomFragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return "f" + fragments.get(position).getUid();
    }
}
