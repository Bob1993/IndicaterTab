package com.bob.underlinesindicator;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.UnderlinePageIndicator;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private final int COLOR_NORMAL = Color.DKGRAY;
    private final int COLOR_SELECTED = Color.GREEN;
    private final String[] titles = {"新增文件", "摆渡文件", "我的文件"};

    private ViewPager pager;
    private UnderlinePageIndicator indicator;
    private TextView tv_add, tv_ferry, tv_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initEvent();
        FragmentPagerAdapter adapter = new MyAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);


        indicator.setViewPager(pager);
        indicator.setFades(false);//设置指示条不消失
        indicator.setSelectedColor(COLOR_SELECTED);//设置指示条颜色为绿色,和字体选中颜色一致

        // 对页面监听同样有两种方案，一种是对viewPager进行监听，另一种是对indicator进行监听.进行字体颜色改变的联动效果
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {//联动效果的实现
                resetTextColor();
                switch (position) {
                    case 0:
                        tv_add.setTextColor(COLOR_SELECTED);
                        break;
                    case 1:
                        tv_ferry.setTextColor(COLOR_SELECTED);
                        break;
                    case 2:
                        tv_mine.setTextColor(COLOR_SELECTED);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initWidget() {
        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_ferry = (TextView) findViewById(R.id.tv_ferry);
        tv_mine = (TextView) findViewById(R.id.tv_mine);
    }

    private void resetTextColor() {//重置所有的颜色
        tv_add.setTextColor(COLOR_NORMAL);
        tv_ferry.setTextColor(COLOR_NORMAL);
        tv_mine.setTextColor(COLOR_NORMAL);
    }

    private class MyAdapter extends FragmentPagerAdapter {//数据适配器的定义

        public MyAdapter(FragmentManager fm) {//构造父类
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Tabs();
            Bundle args = new Bundle();
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

    private void initEvent() {
        tv_add.setOnClickListener(this);
        tv_ferry.setOnClickListener(this);
        tv_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {//点击底部按钮对ViewPager进行切换
        int currentIndex = 0;
        switch (view.getId()) {//只需要选择被选中页，底部按钮会自动在onPageSelected中更新
            case R.id.tv_add:
                currentIndex = 0;
                break;
            case R.id.tv_ferry:
                currentIndex = 1;
                break;
            case R.id.tv_mine:
                currentIndex = 2;
        }
        indicator.setCurrentItem(currentIndex);//点击后切换当前选中page，或者说是fragment
    }
}
