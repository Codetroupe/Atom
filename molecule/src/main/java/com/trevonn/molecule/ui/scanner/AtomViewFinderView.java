package com.trevonn.molecule.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by Jeffrey on 2018/3/15
 */

public class AtomViewFinderView extends ViewFinderView {

    public AtomViewFinderView(Context context) {
        this(context, null);
    }

    public AtomViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}