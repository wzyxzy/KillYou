package com.wzy.killyou;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.wzy.killyou.adapters.PackAdapter;
import com.wzy.killyou.fragments.FragmentOne;
import com.wzy.killyou.fragments.FragmentTwo;
import com.wzy.killyou.models.AppInfo;

import java.util.ArrayList;
import java.util.List;

import static com.wzy.killyou.fragments.FragmentOne.alarm_sys1;
import static com.wzy.killyou.fragments.FragmentTwo.alarm_sys2;

public class Main extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Fragment[] fragments;
    private RadioGroup main_radio_group;
    private static List<AppInfo> appInfos;
    public static List<AppInfo> appInfoOne;
    public static List<AppInfo> appInfoOneS;
    public static List<AppInfo> appInfoTwo;
    public static List<AppInfo> appInfoTwoS;
    public static PackAdapter adapter1;
    public static PackAdapter adapter2;
    public static PackAdapter adapter3;
    public static PackAdapter adapter4;
    private static boolean showsys = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        fragmentsManage(savedInstanceState);
        initView();
        initData(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_sys:
                if (showsys) {
                    showsys = false;
                    item.setTitle(R.string.show_sys);
                    alarm_sys1.setVisibility(View.GONE);
                    alarm_sys2.setVisibility(View.GONE);
                } else {
                    showsys = true;
                    item.setTitle(R.string.no_show_sys);
                    alarm_sys1.setVisibility(View.VISIBLE);
                    alarm_sys2.setVisibility(View.VISIBLE);
                }
                initData(this);

                break;
            case R.id.about_us:
                break;
        }
        return true;
    }

    private void fragmentsManage(Bundle savedInstanceState) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        fragments = new Fragment[]{
                new FragmentOne(),
                new FragmentTwo()
        };
        if (savedInstanceState != null) {
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = supportFragmentManager.findFragmentByTag("fragment" + i);

            }
        } else {
            for (int i = 0; i < fragments.length; i++) {
                transaction.add(R.id.main_fragment, fragments[i], "fragment" + i);
                transaction.hide(fragments[i]);
            }
            transaction.show(fragments[0]);
            transaction.commit();
        }
    }

    private void initView() {
        main_radio_group = (RadioGroup) findViewById(R.id.main_radio_group);
        main_radio_group.setOnCheckedChangeListener(this);
        adapter1 = new PackAdapter(null, this, R.layout.pack_item, "1");
        adapter3 = new PackAdapter(null, this, R.layout.pack_item, "1");
        adapter2 = new PackAdapter(null, this, R.layout.pack_item, "2");
        adapter4 = new PackAdapter(null, this, R.layout.pack_item, "2");
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int index = 0;
        switch (checkedId) {
            case R.id.main_radio_first:
                index = 0;
                break;
            case R.id.main_radio_second:
                index = 1;
                break;


        }
        for (int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
            if (i == index) {
                transaction.show(fragments[i]);
            }
        }
        transaction.commit();
    }

    public static void initData(Context context) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        appInfos = new ArrayList<>();
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo appInfo = new AppInfo();
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            appInfo.setPackageName(packageInfo.packageName);
            if (packageInfo.packageName.equalsIgnoreCase("com.wzy.killyou")) {
                continue;
            }
            appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
            appInfo.setEnabled(packageInfo.applicationInfo.enabled);
            appInfo.setFlag(packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM);
            appInfos.add(appInfo);

        }
        appInfoOne = new ArrayList<>();
        appInfoOneS = new ArrayList<>();
        appInfoTwo = new ArrayList<>();
        appInfoTwoS = new ArrayList<>();
        for (int i = 0; i < appInfos.size(); i++) {
            if (appInfos.get(i).getEnabled()) {
                if (appInfos.get(i).getFlag() == 0) {
                    appInfoOne.add(appInfos.get(i));
                } else if (showsys) {
                    appInfoOneS.add(appInfos.get(i));
                }

            } else {
                if (appInfos.get(i).getFlag() == 0) {
                    appInfoTwo.add(appInfos.get(i));
                } else if (showsys) {
                    appInfoTwoS.add(appInfos.get(i));
                }
            }

        }
        adapter1.updateRes(appInfoOne);
        adapter3.updateRes(appInfoOneS);
        adapter2.updateRes(appInfoTwo);
        adapter4.updateRes(appInfoTwoS);

    }
}
