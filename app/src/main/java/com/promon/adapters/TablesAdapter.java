package com.promon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.promon.app.R;
import com.promon.models.Tables;

/**
 * Created by Diego on 7/07/14.
 */
public class TablesAdapter extends ArrayAdapter<Tables> {

    Context context;

    public TablesAdapter(Context context) {
        super(context, 0);

        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder vh;

        if(view == null) {
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.row_tables, parent, false);
            vh.txtTable = (TextView) view.findViewById(R.id.textView);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.txtTable.setText(getItem(position).getName());

        return view;
    }

    static class ViewHolder {
        public TextView txtTable;
    }
}
