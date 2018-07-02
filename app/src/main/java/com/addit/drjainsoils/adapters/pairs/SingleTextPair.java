package com.addit.drjainsoils.adapters.pairs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

public class SingleTextPair extends ExpandablePairs implements Serializable {
    private String obj1;

    public SingleTextPair(@LayoutRes int LayoutResGroup, @LayoutRes int LayoutResChild, String id, String obj1) {
        super(LayoutResGroup, LayoutResChild, id);
        this.obj1 = obj1;
    }

    public SingleTextPair(String id, String obj1) {
        super(android.R.layout.simple_list_item_1, android.R.layout.simple_list_item_1, id);
        this.obj1 = obj1;
    }

    public String getObj1() {
        return obj1;
    }

    /*@Override
    public View getView(Context context, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        return convertView;
    }*/

    @Override
    public void bindView(Context context, int position, @LayoutRes int viewType, View convertView) throws Exception {
        TextView textView1 = (TextView) convertView;
        if (obj1 != null)
            textView1.setText(Html.fromHtml(obj1));
    }

    @Override
    public void bindGroupView(Context context, int groupPosition, boolean isExpanded, @LayoutRes int viewType, View convertView, ViewGroup parent) throws Exception {
        TextView textView1 = (TextView) convertView;
        if (obj1 != null)
            textView1.setText(Html.fromHtml(obj1));
        textView1.setPadding(70, 0, 0, 0);
    }

    @Override
    public void bindChildView(Context context, int groupPosition, int childPosition, boolean isLastChild, @LayoutRes int viewType, View convertView, ViewGroup parent) throws Exception {
        TextView textView1 = (TextView) convertView;
        if (obj1 != null)
            textView1.setText(Html.fromHtml(obj1));
        textView1.setPadding(140, 0, 0, 0);
    }
}
