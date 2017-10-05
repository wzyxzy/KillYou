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

import static com.wzy.killyou.Main.adapter2;
import static com.wzy.killyou.Main.adapter4;
import static com.wzy.killyou.Main.appInfoTwo;
import static com.wzy.killyou.Main.appInfoTwoS;
import static com.wzy.killyou.Main.initData;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {


    private ListView listtwo;
    private ListView listtwoS;
    public static TextView alarm_sys2;


    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        initView(layout);
        return layout;
    }


    private void initView(View layout) {
        listtwo = (ListView) layout.findViewById(R.id.listtwo);
        alarm_sys2 = (TextView) layout.findViewById(R.id.alarm_sys2);

        listtwo.setAdapter(adapter2);
        listtwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String enable = Manage.Enable(appInfoTwo.get(i).getPackageName());
                if (enable == null) {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.no_root), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), appInfoTwo.get(i).getAppName() + getActivity().getString(R.string.has_unlocked), Toast.LENGTH_SHORT).show();
                    initData(getContext());
                }
            }
        });
        listtwoS = (ListView) layout.findViewById(R.id.listtwoS);
        listtwoS.setAdapter(adapter4);
        listtwoS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String enable = Manage.Enable(appInfoTwoS.get(i).getPackageName());
                if (enable == null) {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.no_root), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), appInfoTwoS.get(i).getAppName() + getActivity().getString(R.string.has_unlocked), Toast.LENGTH_SHORT).show();
                    initData(getContext());
                }
            }
        });

    }


}
