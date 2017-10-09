package com.wzy.killyou.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.killyou.R;
import com.wzy.killyou.methods.Manage;

import static com.wzy.killyou.Main.adapter1;
import static com.wzy.killyou.Main.adapter3;
import static com.wzy.killyou.Main.appInfoOne;
import static com.wzy.killyou.Main.appInfoOneS;
import static com.wzy.killyou.Main.initData;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    private ListView listone;
    private ListView listoneS;
    public static TextView alarm_sys1;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        initView(layout);

        return layout;
    }

//    public void startFunction1() {
//        getActivity().runOnUiThread(new Runnable() {
//            public void run() {
//                if (appInfoOneS != null && appInfoOneS.size() > 0) {
//                    alarm_sys1.setVisibility(View.VISIBLE);
//                } else {
//                    alarm_sys1.setVisibility(View.GONE);
//
//                }
//            }
//        });
//    }

    private void initView(View layout) {
        listone = (ListView) layout.findViewById(R.id.listone);
        alarm_sys1 = (TextView) layout.findViewById(R.id.alarm_sys1);


//        if (appInfoOneS != null && appInfoOneS.size() > 0) {
//            alarm_sys1.setVisibility(View.VISIBLE);
//        } else {
//            alarm_sys1.setVisibility(View.GONE);
//
//        }
        listone.setAdapter(adapter1);
        listone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toastFrag(i, 0);
            }
        });
        listoneS = (ListView) layout.findViewById(R.id.listoneS);
        listoneS.setAdapter(adapter3);
        listoneS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toastFrag(i, 1);
            }
        });


    }

    private void toastFrag(int i, int j) {
        String appName = null;
        String disable = null;
        if (j == 0) {
            appName =appInfoOne.get(i).getAppName();
            disable = Manage.Disable(appInfoOne.get(i).getPackageName());
        } else {
            appName = appInfoOneS.get(i).getAppName();
            disable = Manage.Disable(appInfoOneS.get(i).getPackageName());
        }

        if (disable != null && disable.equalsIgnoreCase(getActivity().getString(R.string.system_package_name))) {
            Toast.makeText(getActivity(), getActivity().getString(R.string.cannot_disable), Toast.LENGTH_SHORT).show();
        } else {
            if (disable == null) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.no_root), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), appName + getActivity().getString(R.string.has_locked), Toast.LENGTH_SHORT).show();
                initData(getContext());
            }
        }

    }

}
