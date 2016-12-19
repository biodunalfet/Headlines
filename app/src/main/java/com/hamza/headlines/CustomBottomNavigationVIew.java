package com.hamza.headlines;

import android.content.Context;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Hamza Fetuga on 12/18/2016.
 */

public class CustomBottomNavigationView extends BottomNavigationView {

    Context context;
    private BottomNavigationMenuView mMenuView;

    public CustomBottomNavigationView(Context context) {
        super(context);
        this.context = context;
    }

    private final BottomNavigationPresenter mPresenter = new BottomNavigationPresenter();
    private MenuBuilder mMenu;


    public CustomBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void inflateMenu(Menu menu) {
        mMenu = new BottomNavigationMenu(context);
        mMenuView = new BottomNavigationMenuView(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mMenuView.setLayoutParams(params);

        mPresenter.setBottomNavigationMenuView(mMenuView);
        mMenu.addMenuPresenter(mPresenter);
        mPresenter.initForMenu(getContext(), mMenu);
        mPresenter.updateMenuView(true);
    }
}
