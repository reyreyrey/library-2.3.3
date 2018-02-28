package com.android.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.library.R;
import com.android.library.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class TrendView extends HorizontalScrollView {
    private LinearLayout layout;
    private List<String> redData;

    private String blueData;

    public TrendView(Context context) {
        super(context);
        init();
    }

    public TrendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TrendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TrendView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        redData = new ArrayList<>();
        layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        addView(layout);
    }

    public void setData(String data) {
        if (TextUtils.isEmpty(data)) return;
        String redStr = data;
        if (data.contains("+")) {
            String[] strs = data.split("\\+");
            if (strs != null && strs.length >= 2)
                blueData = strs[1];
            redStr = strs[0];
        }
        if (redStr.contains(",")) {
            String[] strs = redStr.split(",");
            if (strs != null && strs.length != 0) {
                for (String s : strs) {
                    redData.add(s);
                }
            }
        }
        refreshView();
    }

    private void refreshView() {
        TextView textview;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtil.dp2px(getContext(),25), ScreenUtil.dp2px(getContext(),25));
        params.leftMargin = 8;
        params.rightMargin = 8;
        layout.removeAllViews();
        if (redData != null && redData.size() != 0) {
            for (String red : redData) {
                textview = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null, false);
                textview.setBackgroundResource(R.drawable.shape_red_circle);
                textview.setText(red);
                layout.addView(textview, params);
            }
        }
        if (blueData != null) {
            textview = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null, false);
            textview.setBackgroundResource(R.drawable.shape_blue_circle);
            textview.setText(blueData);
            layout.addView(textview, params);
        }
    }
}
