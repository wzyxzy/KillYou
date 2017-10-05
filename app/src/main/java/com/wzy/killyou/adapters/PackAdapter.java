package com.wzy.killyou.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.killyou.R;
import com.wzy.killyou.models.AppInfo;

import java.util.List;

/**
 * Created by zy on 2017/3/15.
 */

public class PackAdapter extends WZYBaseAdapter<AppInfo> {
    private String one;
    private Context thisContext;

    public PackAdapter(List<AppInfo> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        thisContext = context;
    }

    public PackAdapter(List<AppInfo> data, Context context, int layoutRes, String tag) {
        super(data, context, layoutRes);
        one = tag;
        thisContext = context;

    }

    @Override
    public void bindData(ViewHolder holder, AppInfo appInfo) {
        TextView title = (TextView) holder.getView(R.id.appName);
        title.setText(appInfo.getAppName());
        ImageView imageView = (ImageView) holder.getView(R.id.imageIcon);
        imageView.setImageDrawable(appInfo.getAppIcon());
        if (one.equalsIgnoreCase("1")) {
            title.setTextColor(thisContext.getResources().getColor(R.color.colorGirl));
        } else {
            title.setTextColor(thisContext.getResources().getColor(R.color.colorBoy));

        }
    }
}
