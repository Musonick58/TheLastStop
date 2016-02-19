package com.example.laststop.thelaststop;

import static com.example.laststop.thelaststop.constant.FIRST_COLUMN;
import static com.example.laststop.thelaststop.constant.SECOND_COLUMN;
import static com.example.laststop.thelaststop.constant.THIRD_COLUMN;
import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class twoColumn extends BaseAdapter{
    public ArrayList<HashMap> list;
    Activity activity;

    public twoColumn(Activity activity, ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.mylist, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);

        holder.txtFirst.setText((CharSequence) map.get(FIRST_COLUMN));
        holder.txtSecond.setText((CharSequence) map.get(SECOND_COLUMN));

        return convertView;
    }
}
