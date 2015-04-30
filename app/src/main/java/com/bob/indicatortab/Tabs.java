package com.bob.indicatortab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bob on 15-4-21.
 */
public class Tabs extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tabs,container,false);//最后一项为是否有父布局，设为false
        TextView content= (TextView) view.findViewById(R.id.tv);
        //savedInstanceState是自己保存下来的数据
        Bundle bundle= getArguments();
        String title= bundle.getString("arg");//从bundle里获取来自Activity的数据
        content.setText(title);
        return view;
    }
}
