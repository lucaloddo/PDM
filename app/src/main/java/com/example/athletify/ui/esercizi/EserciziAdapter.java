package com.example.athletify.ui.esercizi;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.athletify.models.Esercizio;
import com.example.athletify.R;

import java.util.List;

public class EserciziAdapter extends BaseAdapter {

    private List<Esercizio> esercizioList;
    private Activity activity;

    public EserciziAdapter(List<Esercizio> esercizioList, Activity activity) {
        this.esercizioList = esercizioList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return esercizioList.size();
    }

    @Override
    public Esercizio getItem(int position) {
        return esercizioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.esercizi_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position).getName());

        return convertView;
    }
}
