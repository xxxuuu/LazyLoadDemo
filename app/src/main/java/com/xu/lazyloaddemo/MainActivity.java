package com.xu.lazyloaddemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    List<CustomFragment> fragmentList;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        viewPager = (ViewPager)findViewById(R.id.main_vp);
        tabLayout = (TabLayout) findViewById(R.id.main_tl);

        viewPager.setAdapter(new CustomFragmentAdapter(getSupportFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);
    }


    private void initList()
    {
        fragmentList = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            CustomFragment fragment = new CustomFragment();
            fragment.setUid(i);
            fragmentList.add(fragment);
        }
    }
}
