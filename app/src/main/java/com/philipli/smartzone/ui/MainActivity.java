package com.philipli.smartzone.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.philipli.smartzone.R;
import com.philipli.smartzone.app.SmartZoneApp;
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

    private long exitTime;

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
//                WebViewActivity.start(this, "扫码下载", "https://fir.im/SmartZone");
                QRCodeActivity.start(this);
                break;
            case R.id.item_exit:
                finish();
                break;
            case R.id.item_author:
                AboutAuthorActivity.start(this);
                break;
            case R.id.item_github:
                WebViewActivity.start(this, "Github主页", "https://github.com/philipli1995/SmartZone");
                break;
            default:
                break;
        }


        return false;
    }


    /**
     * 监听back键处理DrawerLayout和SearchView
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
                mDrawerLayout.closeDrawers();
            } else {
                exitApp();
            }
        }
        return true;
    }


    /**
     * 双击退出App
     */
    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(SmartZoneApp.getInstance(), "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}