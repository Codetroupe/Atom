package com.trevonn.molecule.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.trevonn.molecule.R;
import com.trevonn.molecule.R2;
import com.trevonn.molecule.delegates.AtomDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;


/**
 * Created by Jeffrey on 2018/3/14
 * @function 底部菜单初始化
 */


public abstract class BaseBottomDelegate extends AtomDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.WHITE;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int getIndexDelegate();

    public abstract void setIndexDelegate(int i);

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = getIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setBackgroundColor(getResources().getColor(R.color.app_main));
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final ImageView itemIcon = (ImageView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setBackground(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            itemTitle.setTextColor(mClickedColor);
            if (i == mIndexDelegate) {
//                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final ImageView itemIcon = (ImageView) item.getChildAt(0);
//            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;

        final ImageView itemIcon = (ImageView) item.getChildAt(0);
//        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }
}