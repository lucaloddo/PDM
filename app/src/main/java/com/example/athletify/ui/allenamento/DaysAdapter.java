package com.example.athletify.ui.allenamento;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.athletify.R;
import com.example.athletify.models.wkDays;

import java.util.List;

public class DaysAdapter extends BaseAdapter {

    private List<wkDays> wkDaysList;
    private Activity activity;

    public DaysAdapter(List<wkDays> wkDaysList, Activity activity) {
        this.wkDaysList = wkDaysList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return wkDaysList.size();
    }

    @Override
    public wkDays getItem(int i) {
        return wkDaysList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.days_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.dayName)).setText(getItem(position).getDayName());

        return convertView;
    }

}
