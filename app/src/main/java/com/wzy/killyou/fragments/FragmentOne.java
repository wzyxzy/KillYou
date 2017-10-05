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
                String disable = Manage.Disable(appInfoOne.get(i).getPackageName());
                if (disable == null) {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.no_root), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), appInfoOne.get(i).getAppName() + getActivity().getString(R.string.has_locked), Toast.LENGTH_SHORT).show();
                    initData(getContext());
                }
            }
        });
        listoneS = (ListView) layout.findViewById(R.id.listoneS);
        listoneS.setAdapter(adapter3);
        listoneS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String disable = Manage.Disable(appInfoOneS.get(i).getPackageName());
                if (disable == null) {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.no_root), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), appInfoOneS.get(i).getAppName() + getActivity().getString(R.string.has_locked), Toast.LENGTH_SHORT).show();
                    initData(getContext());
                }
            }
        });
    }


}
