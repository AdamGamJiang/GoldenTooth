package com.gan.goldentooth.goldentooth;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/16.
 */
public class ContentFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        TextView textView = new TextView(getActivity());
        if(getArguments()!=null){
            String title = getArguments().getString("title");
            textView.setText(title);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return textView;
    }
}
