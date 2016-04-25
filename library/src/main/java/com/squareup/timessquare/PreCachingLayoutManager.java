package com.squareup.timessquare;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 作者：wangdakuan
 * 主要功能:
 * 创建时间：2016/4/25 10:04
 */
public class PreCachingLayoutManager  extends LinearLayoutManager {
    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 1080;
    private int extraLayoutSpace = -1;
    private Context context;

    public PreCachingLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    public PreCachingLayoutManager(Context context, int extraLayoutSpace) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;
    }

    public PreCachingLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.context = context;
    }

    public void setExtraLayoutSpace(int extraLayoutSpace) {
        this.extraLayoutSpace = extraLayoutSpace;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE;
    }
}
