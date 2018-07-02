package com.addit.drjainsoils.adapters.pairs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract public class ExpandablePairs extends Pairs {

    private int childLayoutResID;


    public ExpandablePairs(@LayoutRes int LayoutResID, @LayoutRes int childLayoutResID, String id) {
        super(LayoutResID, id);
        this.childLayoutResID = childLayoutResID;
    }

    public View getGroupView(Context context, int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return super.getView(context, groupPosition, convertView, parent);
    }

    public View getChildView(Context context, int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(childLayoutResID, parent, false);
            /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(childLayoutResID, null);*/
        }
        return convertView;
    }

    @Override
    public void bindView(Context context, int position, @LayoutRes int viewType, View convertView) throws Exception {
    }

    abstract public void bindGroupView(Context context, int groupPosition, boolean isExpanded, @LayoutRes int viewType, View convertView, ViewGroup parent) throws Exception;

    abstract public void bindChildView(Context context, int groupPosition, int childPosition, boolean isLastChild, @LayoutRes int viewType, View convertView, ViewGroup parent) throws Exception;
}
