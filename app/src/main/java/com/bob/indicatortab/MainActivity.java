package com.bob.indicatortab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.PageIndicator;


public class MainActivity extends FragmentActivity {

    public static final String titles[]= {"新增","摆渡","我的"};

    private ViewPager pager;
    private PageIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager= (ViewPager) findViewById(R.id.pager);

        FragmentPagerAdapter adapter= new TabPageIndicatorAdapter(getSupportFragmentManager());//创建数据适配器
        pager.setAdapter(adapter);

        indicator= (PageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);//将indicator和viewPager进行绑定

    /*两种设置页面切换监听的方法
        indicator.setOnPageChangeListener();
        pager.setOnPageChangeListener();*/
    }


    class TabPageIndicatorAdapter extends FragmentPagerAdapter{

        public TabPageIndicatorAdapter(FragmentManager fm){//构造父类
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment= new Tabs();
            Bundle args= new Bundle();
            args.putString("arg", titles[position]);
            fragment.setArguments(args);//为fragment添加bundle数据束args
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }//创建一个支持indicator的FragmentPagerAdapter适配器

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}
