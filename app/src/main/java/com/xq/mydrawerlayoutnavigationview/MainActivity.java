package com.xq.mydrawerlayoutnavigationview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xq.mydrawerlayoutnavigationview.drawer.DrawerLayout_BelowToolbarActivity;
import com.xq.mydrawerlayoutnavigationview.drawer.DrawerLayout_OneActivity;
import com.xq.mydrawerlayoutnavigationview.drawer.DrawerLayout_OtherActivity;
import com.xq.mydrawerlayoutnavigationview.drawer.SimpleDrawerLayoutActivity;
import com.xq.mydrawerlayoutnavigationview.drawer.SimpleNavigationActivity;
import com.xq.mydrawerlayoutnavigationview.fab.BottomSheetActivity;
import com.xq.mydrawerlayoutnavigationview.fab.FabActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }

    public void doclick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.drawer_layout_simple:
                intent = new Intent(this, DrawerLayout_OneActivity.class);
                startActivity(intent);
                break;
            case R.id.drawer_layout_below:
                intent = new Intent(this, DrawerLayout_BelowToolbarActivity.class);
                startActivity(intent);
                break;
            case R.id.drawer_layout_layout:
                intent = new Intent(this, DrawerLayout_OtherActivity.class);
                startActivity(intent);
                break;

            case R.id.simple_drawer_layout_layout:
                intent = new Intent(this, SimpleDrawerLayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.simple_navi_layout_layout:
                intent = new Intent(this, SimpleNavigationActivity.class);
                startActivity(intent);
                break;

            case R.id.fab_layout_below:
                intent = new Intent(this, FabActivity.class);
                startActivity(intent);
                break;

            case R.id.bottom_layout_layout:
                intent = new Intent(this, BottomSheetActivity.class);
                startActivity(intent);
                break;
        }
    }
}
