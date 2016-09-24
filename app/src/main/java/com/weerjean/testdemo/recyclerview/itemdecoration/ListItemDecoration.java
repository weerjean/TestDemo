package com.weerjean.testdemo.recyclerview.itemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.security.InvalidParameterException;

/**
 * Created by weerjean on 2016/9/24.
 * description：用于绘制RecyclerView 的 List模式的 分割线
 */
public class ListItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * Recyclerview 的方向，根据方向绘制分隔线
     */
    private int mOrientation;
    /**
     * 需要绘制的分隔线
     */
    private Drawable mDivider;
    /**
     * 横向
     */
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    /**
     * 竖向
     */
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    /**
     * 分隔线的属性
     */
    public static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public ListItemDecoration(Context context, int orientation) {


        setOrientation(orientation);

        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);

        mDivider = typedArray.getDrawable(0);

        typedArray.recycle();

    }

    /**
     * 设置需要绘制分隔线的方向
     *
     * @param orientation
     */
    public void setOrientation(int orientation) {
        if (orientation == HORIZONTAL_LIST || orientation == VERTICAL_LIST) {
            this.mOrientation = orientation;
        } else {
            throw new InvalidParameterException("parem error");
        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // 用于绘制recyclerView item 的 装饰
        if (mOrientation == HORIZONTAL_LIST) {
            drawHorizontal(c, parent, state);
        } else {
            drawVertical(c, parent, state);
        }

    }

    /**
     * 绘制纵向分隔线
     *
     * @param c      画布
     * @param parent 当前的RecyclerView
     * @param state  RecyclerView当前的状态
     */
    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int left = parent.getPaddingLeft();
        int right = parent.getRight() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom();
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }


    }

    /**
     * 绘制纵向分隔线
     *
     * @param c      画布
     * @param parent 当前的RecyclerView
     * @param state  RecyclerView当前的状态
     */
    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // 顶部位置
        int top = parent.getPaddingTop();
        // 底部位置
        int bottom = parent.getBottom() - parent.getPaddingBottom();
        // item 数量
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }

    }
}
