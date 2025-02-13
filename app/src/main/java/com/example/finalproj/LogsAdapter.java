package com.example.finalproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LogsAdapter extends ArrayAdapter<Logs> {
    private int resourceLayout;
    private Context mContext;

    public LogsAdapter(Context context, int resource, List<Logs> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            view = vi.inflate(resourceLayout, null);
        }

        Logs log = getItem(position);

        if (log != null) {
            TextView accountView = view.findViewById(R.id.Logs_account);
            TextView reservationNumView = view.findViewById(R.id.Logs_reservationNum);
            TextView typeView = view.findViewById(R.id.Logs_type);

            if (accountView != null) {
                accountView.setText(log.getAccount());
            }
            if (reservationNumView != null) {
                reservationNumView.setText(log.getReservationNum());
            }
            if (typeView != null) {
                typeView.setText(log.getType());
            }
        }

        return view;
    }
}
