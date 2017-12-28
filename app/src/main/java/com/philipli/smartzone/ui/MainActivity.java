package com.philipli.smartzone.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.ui.home.HomeFragment;

import butterknife.BindView;


public class MainActivity extends RxBaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.design_navigation_view)
    NavigationView mDesignNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    Fragment[] fragments;

    HomeFragment mHomePageFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar() {


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initNavigationView() {
        View header = mDesignNavigationView.getHeaderView(0);
        mDesignNavigationView.setNavigationItemSelectedListener(this);


    }

    public void toggleDrawer() {
        Log.i("Test", "test");
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void initFragments() {

        mHomePageFragment = HomeFragment.getInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mHomePageFragment)
                .show(mHomePageFragment).commit();
    }



    @Override
    public void initViews(Bundle savedInstanceState) {
        initFragments();
        initNavigationView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_download:
                WebViewActivity.start(this, "Test", "https://github.com/login");
                break;
            case R.id.item_exit:
                finish();
                break;
            case R.id.item_author:
                AboutAuthorActivity.start(this);
                break;
            default:
                break;
        }


        return false;
    }
}