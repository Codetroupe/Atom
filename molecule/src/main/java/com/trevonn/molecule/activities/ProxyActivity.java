package com.trevonn.molecule.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;


import com.trevonn.molecule.R;
import com.trevonn.molecule.delegates.AtomDelegate;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Jeffrey on 2018/3/13
 */

public abstract class ProxyActivity extends SupportActivity {


    public abstract AtomDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState == null){
            loadRootFragment(R.id.delegate_container, (ISupportFragment) setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

